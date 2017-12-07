package avile.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookRecommendationTest {

    private BookRecommendation bookRecommendation;

    @Before
    public void setUp() throws Exception {
        bookRecommendation = new BookRecommendation();
    }


    @Test
    public void getSetAuthor() throws Exception {
        String author = "Test author";
        bookRecommendation.setAuthor(author);
        assertEquals(author, bookRecommendation.getAuthor());
    }


    @Test
    public void getIsbn() throws Exception {
        String isbn = "123456789";
        bookRecommendation.setIsbn(isbn);
        assertEquals(isbn, bookRecommendation.getIsbn());
    }

    @Test
    public void getSetRecommendation() throws Exception {
        Recommendation recommendation = new Recommendation();
        bookRecommendation.setRecommendation(recommendation);
        assertEquals(recommendation, bookRecommendation.getRecommendation());
    }

    @Test
    public void getSetId() throws Exception {
        Long id = 123456789L;
        bookRecommendation.setId(id);
        assertEquals(id, bookRecommendation.getId());
    }

}