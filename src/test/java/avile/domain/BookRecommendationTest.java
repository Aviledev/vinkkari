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
    public void getSetTitle() throws Exception {
        String title = "Test title";
        bookRecommendation.setTitle(title);
        assertEquals(title, bookRecommendation.getTitle());
    }

    @Test
    public void getSetAuthor() throws Exception {
        String author = "Test author";
        bookRecommendation.setAuthor(author);
        assertEquals(author, bookRecommendation.getAuthor());
    }

    @Test
    public void getType() throws Exception {
        RecommendationType type = RecommendationType.BOOK;
        bookRecommendation.setType(RecommendationType.BOOK);
        assertEquals(type.toString(), bookRecommendation.getType());
    }


    @Test
    public void getIsbn() throws Exception {
        String isbn = "123456789";
        bookRecommendation.setIsbn(isbn);
        assertEquals(isbn, bookRecommendation.getIsbn());
    }

    @Test
    public void getSetDate() throws Exception {
        Date date = new Date();
        bookRecommendation.setDate(date);
        assertEquals(date.getTime(), bookRecommendation.getDate().getTime());
        assertEquals(date.toString(), bookRecommendation.getDate().toString());
    }

}