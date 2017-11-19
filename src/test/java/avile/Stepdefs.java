package avile;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Stepdefs {

    WebDriver driver;

    public Stepdefs() {
        File file;
        if (System.getProperty("os.name").matches("Mac OS X")) {
            file = new File("lib/macgeckodriver");
        } else {
            file = new File("lib/geckodriver");
        }
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", absolutePath);

        this.driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^user is at the \"([^\"]*)\" page$")
    public void userIsAtThePage(String arg0) throws Throwable {
        driver.get("http://localhost:" + 8080 + "/"+arg0);
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
        assertTrue(driver.getPageSource().contains("Here you can find all recommendations"));
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
        driver.findElement(By.id("submitBtn")).click();
    }

    @Then("^the entry with title \"([^\"]*)\" is added$")
    public void theEntryWithTitleIsAdded(String arg0) throws Throwable {
        driver.getPageSource().contains(arg0);
    }

    private void clickButtonWithId(String id) {
        System.out.println(driver.getPageSource());
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.click();
    }

    private void clickLinkWithText(String text) {
        System.out.println(driver.getPageSource());
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
        element.click();
    }

    private void enterInputToField(String input, String field) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.name(field)));
        element.sendKeys(input);
    }



}
