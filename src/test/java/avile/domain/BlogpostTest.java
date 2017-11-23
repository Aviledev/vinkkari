package avile.domain;

import avile.enums.RecommendationType;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BlogpostTest {

    private Blogpost blogpost;

    @Before
    public void setUp() throws Exception {
        blogpost = new Blogpost();
    }

    @Test
    public void getSetAuthor() throws Exception {
        String author = "Test author";
        blogpost.setAuthor(author);
        assertEquals(author, blogpost.getAuthor());
    }

    @Test
    public void getSetUrl() throws Exception {
        String url = "http://www.youtube.com";
        blogpost.setAuthor(url);
        assertEquals(url, blogpost.getUrl());
    }

    @Test
    public void getSetReleaseDate() throws Exception {
        Date date = new Date();
        blogpost.setReleaseDate(date);
        assertEquals(date, blogpost.getReleaseDate());
    }

    @Test
    public void getSetRecommendation() throws Exception {
        Recommendation rec = new Recommendation();
        blogpost.setRecommendation(rec);
        assertEquals(rec, blogpost.getRecommendation());
    }

}
