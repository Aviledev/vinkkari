package avile.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TagTest {
    private Tag tag;

      
    @Before
    public void setUp() {
        tag = new Tag();
    }
    
    @Test
    public void getSetName() {
        String tagName = "easy";
        tag.setName(tagName);
        assertEquals("easy", tag.getName());
    }

    @Test
    public void getSetDescription() {
        String tagDesc = "easy";
        tag.setDescription(tagDesc);
        assertEquals("easy", tag.getDescription());
    }

    @Test
    public void getSetRecommendations() {
        tag.setRecommendations(new ArrayList<>());
        assertEquals(0, tag.getRecommendations().size());
        Recommendation recommendation = new Recommendation();
        tag.getRecommendations().add(recommendation);
        assertEquals(1, tag.getRecommendations().size());
        tag.getRecommendations().remove(recommendation);
        assertEquals(0, tag.getRecommendations().size());

    }
   
}
