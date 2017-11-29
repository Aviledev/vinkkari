package avile.domain;

import avile.enums.RecommendationType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoRecommendationTest {

    private VideoRecommendation videoRecommendation;

    @Before
    public void setUp() throws Exception {
        videoRecommendation = new VideoRecommendation();
    }


    @Test
    public void getSetUrl() {
        String url = "http://google.fi";
        videoRecommendation.setUrl(url);
        assertEquals(url, videoRecommendation.getUrl());
    }

    @Test
    public void getSetAuthor() {
        String author = "Donald Duck";
        videoRecommendation.setAuthor(author);
        assertEquals(author, videoRecommendation.getAuthor());
    }


    @Test
    public void getSetTitle() {
        String title = "Lorem Ipsum";
        videoRecommendation.getRecommendation().setTitle(title);
        assertEquals(title, videoRecommendation.getRecommendation().getTitle());
    }

    @Test
    public void getSetDescription() {
        String desc = "Lorem ipsum dolor sit amet";
        videoRecommendation.getRecommendation().setDescription(desc);
        assertEquals(desc, videoRecommendation.getRecommendation().getDescription());
    }

    @Test
    public void typeSetRight() {
        RecommendationType type = RecommendationType.VIDEO;
        assertEquals(type, videoRecommendation.getRecommendation().getRecommendationType());
    }
}