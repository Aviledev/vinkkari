package avile.domain;

import avile.enums.RecommendationType;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PodcastRecommendationTest {

    private PodcastRecommendation podcastRecommendation;

    @Before
    public void setUp() throws Exception {
        podcastRecommendation = new PodcastRecommendation();
    }

    @Test
    public void getSetTitle() {
        String title = "podcast";
        podcastRecommendation.setTitle(title);
        assertEquals(title, podcastRecommendation.getTitle());
    }

    @Test
    public void getSetAuthor() {
        String author = "author";
        podcastRecommendation.setAuthor(author);
        assertEquals(author, podcastRecommendation.getAuthor());
    }

    
/*  HUOM!!!!!!!!!! Date testit korjattava TODO
    @Test
    public void getSetDate() {
        Date date = new Date();
        podcastRecommendation.setDate(date);
        assertEquals(date.getTime(), podcastRecommendation.getDate().getTime());
        assertEquals(date.toString(), podcastRecommendation.getDate().getTime());
    }
    
    
    @Test
    public void getSetReleaseDate() {
        Date date = new Date();
        podcastRecommendation.setReleaseDate(date);
        assertEquals(date.getTime(), podcastRecommendation.getReleaseDate().getTime());
        assertEquals(date.toString(), podcastRecommendation.getReleaseDate().getTime());
    } */

    @Test
    public void getSetDescription() {
        String description = "kipsterin theoreema";
        podcastRecommendation.setDescription(description);
        assertEquals(description, podcastRecommendation.getDescription());
    }
    
    @Test
    public void getSetUrl() {
        String url = "www.helsinki.fi";
        podcastRecommendation.setUrl(url);
        assertEquals(url, podcastRecommendation.getUrl());
    }
    
     @Test
    public void getType() throws Exception {
        RecommendationType type = RecommendationType.PODCAST;
        podcastRecommendation.setType(RecommendationType.PODCAST);
        assertEquals(type.toString(), podcastRecommendation.getType());
    }

}
