package avile.domain;

import avile.enums.RecommendationType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class RecommendationTest {

    private Recommendation recommendation;

    @Before
    public void setUp() throws Exception {
        recommendation = new Recommendation();
    }

    @Test
    public void getSetRawTags() throws Exception {
        String rawtags = "hello,world";
        recommendation.setRawTags(rawtags);
        assertEquals(rawtags, recommendation.getRawTags());
    }

    @Test
    public void getSetTags() throws Exception {
        Tag tag1 = new Tag();
        tag1.setName("Tag1");
        Tag tag2 = new Tag();
        tag2.setName("Tag2");
        recommendation.setTags(new ArrayList<>());
        assertEquals(0, recommendation.getTags().size());
        recommendation.getTags().add(tag1);
        assertEquals(1, recommendation.getTags().size());
        recommendation.getTags().add(tag2);
        assertEquals(2, recommendation.getTags().size());
        recommendation.setTags(new ArrayList<>());
        assertEquals(0, recommendation.getTags().size());
    }

    @Test
    public void getSetTagsAsString() throws Exception {
        Tag tag1 = new Tag();
        tag1.setName("Tag1");
        Tag tag2 = new Tag();
        tag2.setName("Tag2");
        recommendation.setTags(new ArrayList<>());
        recommendation.getTags().add(tag1);
        assertEquals("Tag1", recommendation.getTagsAsString());
        recommendation.getTags().add(tag2);
        assertEquals("Tag1,Tag2", recommendation.getTagsAsString());
        recommendation.setTags(new ArrayList<>());
        assertEquals("", recommendation.getTagsAsString());
    }

    @Test
    public void getSetPrerequisiteCourses() throws Exception {
        Course pre1 = new Course();
        Course pre2 = new Course();
        recommendation.setPrerequisiteCourses(new ArrayList<>());
        assertEquals(0, recommendation.getPrerequisiteCourses().size());
        recommendation.getPrerequisiteCourses().add(pre1);
        assertEquals(1, recommendation.getPrerequisiteCourses().size());
        recommendation.getPrerequisiteCourses().add(pre2);
        assertEquals(2, recommendation.getPrerequisiteCourses().size());
        recommendation.setPrerequisiteCourses(new ArrayList<>());
        assertEquals(0, recommendation.getPrerequisiteCourses().size());

    }

    @Test
    public void getSetRelatedCourses() throws Exception {
        Course pre1 = new Course();
        Course pre2 = new Course();
        recommendation.setRelatedCourses(new ArrayList<>());
        assertEquals(0, recommendation.getRelatedCourses().size());
        recommendation.getRelatedCourses().add(pre1);
        assertEquals(1, recommendation.getRelatedCourses().size());
        recommendation.getRelatedCourses().add(pre2);
        assertEquals(2, recommendation.getRelatedCourses().size());
        recommendation.setRelatedCourses(new ArrayList<>());
        assertEquals(0, recommendation.getRelatedCourses().size());
    }

    @Test
    public void getSetDate() throws Exception {
        Date date = new Date();
        recommendation.setDate(date);
        assertEquals(date, recommendation.getDate());
    }

    @Test
    public void getSetRecommendationType() throws Exception {
        recommendation.setRecommendationType(RecommendationType.BOOK);
        assertEquals(RecommendationType.BOOK, recommendation.getRecommendationType());
    }

    @Test
    public void getSetTitle() throws Exception {
        String title = "Test title";
        recommendation.setTitle(title);
        assertEquals(title, recommendation.getTitle());
    }

    @Test
    public void getSetDescription() throws Exception {
        String desc = "Test desc";
        recommendation.setDescription(desc);
        assertEquals(desc, recommendation.getDescription());
    }

    @Test
    public void getSetId() throws Exception {
        Long id = 123456789L;
        recommendation.setId(id);
        assertEquals(id, recommendation.getId());
    }

}