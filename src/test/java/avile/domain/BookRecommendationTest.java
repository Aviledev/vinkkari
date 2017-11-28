package avile.domain;

import avile.enums.RecommendationType;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

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

}