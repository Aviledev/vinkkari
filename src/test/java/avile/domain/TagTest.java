package avile.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TagTest {
    private Tag tag;
    
    public TagTest() {
    }
      
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
   
}
