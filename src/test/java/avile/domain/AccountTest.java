package avile.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    
    private Account account;
    
    public AccountTest() {
    }

    
    @Before
    public void setUp() {
        this.account = new Account();
    }
    
    @Test
    public void getSetEmail() {
        String email = "www.helsinki.fi";
        account.setEmail(email);
        assertEquals(account.getEmail(), "www.helsinki.fi");
    }
    
    @Test
    public void getSetFirstname() {
        String firstname = "Lasse";
        account.setFirstname(firstname);
        assertEquals(account.getFirstname(), "Lasse");
    }
    
    @Test
    public void getSetLastname() {
        String lastname = "Kukkonen";
        account.setLastname(lastname);
        assertEquals(account.getLastname(), "Kukkonen");
    }
    
    @Test
    public void getSetPassword() {
        String password = "pass123";
        account.setPassword(password);
        assertEquals(account.getPassword(), "pass123");
    }
    
    @Test
    public void getSetCheckedRecommendations() {
        List<Recommendation> checkedRecommendations = new ArrayList<>();
        Recommendation recommendation = new Recommendation();
        checkedRecommendations.add(recommendation);
        account.setCheckedRecommendations(checkedRecommendations);
        assertEquals(account.getCheckedRecommendations().get(0), checkedRecommendations.get(0));
    }
    
    @Test
    public void getSetCreatedRecommendations() {
        List<Recommendation> createdRecommendations = new ArrayList<>();
        Recommendation recommendation = new Recommendation();
        createdRecommendations.add(recommendation);
        account.setCreatedRecommendations(createdRecommendations);
        assertEquals(account.getCreatedRecommendations().get(0), createdRecommendations.get(0));
    }

}
