package avile.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BlogpostRecommendationTest {

    private BlogpostRecommendation blogpostRecommendation;

    @Before
    public void setUp() throws Exception {
        blogpostRecommendation = new BlogpostRecommendation();
    }

    @Test
    public void getSetAuthor() throws Exception {
        String author = "Test author";
        blogpostRecommendation.setAuthor(author);
        assertEquals(author, blogpostRecommendation.getAuthor());
    }

    @Test
    public void getSetUrl() throws Exception {
        String url = "http://www.youtube.com";
        blogpostRecommendation.setAuthor(url);
        assertEquals(url, blogpostRecommendation.getUrl());
    }

    @Test
    public void getSetReleaseDate() throws Exception {
        Date date = new Date();
        blogpostRecommendation.setReleaseDate(date);
        assertEquals(date, blogpostRecommendation.getReleaseDate());
    }

    @Test
    public void getSetRecommendation() throws Exception {
        Recommendation rec = new Recommendation();
        blogpostRecommendation.setRecommendation(rec);
        assertEquals(rec, blogpostRecommendation.getRecommendation());
    }

}
