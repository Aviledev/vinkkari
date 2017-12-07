package avile.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PodcastRecommendationTest {

    private PodcastRecommendation podcastRecommendation;

    @Before
    public void setUp() throws Exception {
        podcastRecommendation = new PodcastRecommendation();
    }

    @Test
    public void getSetAuthor() throws Exception {
        String author = "Test author";
        podcastRecommendation.setAuthor(author);
        assertEquals(author, podcastRecommendation.getAuthor());
    }

    @Test
    public void getSetName() throws Exception {
        String name = "Test name";
        podcastRecommendation.setName(name);
        assertEquals(name, podcastRecommendation.getName());
    }

    @Test
    public void getSetRecommendation() throws Exception {
        Recommendation recommendation = new Recommendation();
        podcastRecommendation.setRecommendation(recommendation);
        assertEquals(recommendation, podcastRecommendation.getRecommendation());
    }

    @Test
    public void getSetReleaseDate() throws Exception {
        Date date = new Date();
        podcastRecommendation.setReleaseDate(date);
        assertEquals(date, podcastRecommendation.getReleaseDate());
    }
    
    @Test
    public void getSetUrl() {
        String url = "www.helsinki.fi";
        podcastRecommendation.setUrl(url);
        assertEquals(url, podcastRecommendation.getUrl());
    }

    @Test
    public void getSetId() throws Exception {
        Long id = 123456789L;
        podcastRecommendation.setId(id);
        assertEquals(id, podcastRecommendation.getId());
    }

}
