package avile;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Stepdefs {

    WebDriver driver;

    public Stepdefs() {
        File file;
        if (System.getProperty("os.name").matches("Mac OS X")) {
            file = new File("lib/macchromedriver");
        } else {
            file = new File("lib/geckodriver");
        }
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", absolutePath);

        if (System.getProperty("os.name").matches("Mac OS X")) {
            this.driver = new ChromeDriver();
        } else {
            this.driver = new FirefoxDriver();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^user is at the \"([^\"]*)\" page$")
    public void userIsAtThePage(String arg0) throws Throwable {
        driver.get("http://localhost:" + 8080 + "/" + arg0);
    }

    @Then("^\"([^\"]*)\" is shown$")
    public void is_shown(String arg1) throws Throwable {
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains(arg1));
    }

    @When("^user navigates to \"([^\"]*)\"$")
    public void user_navigates_to(String arg1) throws Throwable {
        clickLinkWithText(arg1);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs("VINKKARI | " + arg1));
    }

    @Then("^Recommendations are shown$")
    public void recommendationsAreShown() throws Throwable {
        assertTrue(driver.getPageSource().contains("RECOMMENDATIONS"));
    }

    @Then("^the form \"([^\"]*)\" is opened$")
    public void theFormIsOpened(String arg0) throws Throwable {
        assertFalse(driver.findElement(By.id(arg0)).getCssValue("display").equals("none"));
    }

    @When("^user clicks Create button$")
    public void userClicksCreateButton() throws Throwable {
        clickButtonWithId("openCreateFormBtn");
    }

    @When("^the entry \"([^\"]*)\" is entered into the field \"([^\"]*)\"$")
    public void theEntryIsEnteredIntoTheField(String arg0, String arg1) throws Throwable {
        enterInputToField(arg0, arg1);
    }

    @When("^the form \"([^\"]*)\" is submitted$")
    public void theIsSubmitted(String arg0) throws Throwable {
        clickButtonWithId("submitBtn");
    }

    @When("^user clicks \"([^\"]*)\" link$")
    public void user_clicks_link(String arg1) throws Throwable {
        clickLinkWithText(arg1);
    }

    @Then("^the entry with title \"([^\"]*)\" is added$")
    public void theEntryWithTitleIsAdded(String arg0) throws Throwable {
        assertTrue(driver.getPageSource().contains(arg0));
    }

    @Then("^user is at the page which title is \"([^\"]*)\"$")
    public void user_is_at_the_page_which_title_is(String arg1) throws Throwable {
        assertTrue(driver.findElement(By.id("pageTitle"))
                .getText().contains(arg1));
    }

    @Then("^user is at the page which title is \"([^\"]*)\" and contain Delete button$")
    public void user_is_at_the_page_which_title_is_and_contain_Delete_button(String arg1) throws Throwable {
        user_is_at_the_page_which_title_is(arg1);
        assertTrue(driver.findElement(By.id("deleteBtn")).isDisplayed());
    }

    @When("^user clicks Delete button$")
    public void user_clicks_Delete_button() throws Throwable {
        clickButtonWithId("deleteBtn");
    }

    @Then("^the entry with title \"([^\"]*)\" is deleted$")
    public void the_entry_with_title_is_deleted(String arg1) throws Throwable {
        assertTrue(!driver.getPageSource().contains(arg1));
    }

    @Then("^user is at the page which title is \"([^\"]*)\" and contain Edit button$")
    public void user_is_at_the_page_which_title_is_and_contain_Edit_button(String arg1) throws Throwable {
        user_is_at_the_page_which_title_is(arg1);
        assertTrue(driver.findElement(By.id("editBtn")).isDisplayed());
    }

    @When("^user clicks Edit button$")
    public void user_clicks_Edit_button() throws Throwable {
        clickLinkWithText("Edit");
    }

    @When("^user clicks Save button$")
    public void user_clicks_Save_button() throws Throwable {
        clickButtonWithId("saveBtn");
    }

    @Then("^the entry with title \"([^\"]*)\", author \"([^\"]*)\" and isbn \"([^\"]*)\" is saved$")
    public void the_entry_with_title_author_and_isbn_is_saved(String arg1, String arg2, String arg3) throws Throwable {

        assertTrue(driver.getPageSource().contains(arg1) && driver.getPageSource().contains(arg2) && driver.getPageSource().contains(arg3));

    }

    @Then("^the entry with title \"([^\"]*)\", author \"([^\"]*)\" and url \"([^\"]*)\" is saved$")
    public void theEntryWithTitleAuthorAndUrlIsSaved(String arg0, String arg1, String arg2) throws Throwable {
        assertTrue(driver.getPageSource().contains(arg0) && driver.getPageSource().contains(arg1) && driver.getPageSource().contains(arg2));
    }

    private void clickButtonWithId(String id) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.click();
    }

    private void clickLinkWithText(String text) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
        element.click();
    }

    private void enterInputToField(String input, String fieldId) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(fieldId)));
        element.click();
        element.sendKeys(input);
    }

    @Then("^user searches for \"([^\"]*)\" and submits the search form$")
    public void userSearchesForAndSubmitsTheSearchForm(String arg0) throws Throwable {
        this.enterInputToField(arg0, "titleInput");
        this.clickButtonWithId("searchBtn");
    }

    @And("^book with title \"([^\"]*)\" is found$")
    public void bookWithTitleIsFound(String arg0) throws Throwable {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs("VINKKARI | Search results"));
        assertTrue(driver.getPageSource().contains(arg0));
    }

    @And("^user is at the Search results page after searching \"([^\"]*)\"$")
    public void userIsAtTheSearchResultsPageAfterSearching(String arg0) throws Throwable {
        assertTrue(driver.getPageSource().contains("SEARCH RESULTS"));
    }

    @When("^user clicks Blogpost button$")
    public void user_clicks_Blogpost_button() throws Throwable {
        clickButtonWithId("pills-blogpost-tab");
    }

    @Then("^Courses are shown$")
    public void courses_are_shown() throws Throwable {
        assertTrue(driver.getPageSource().contains("COURSES"));
    }

}
