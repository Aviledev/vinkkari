package avile.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CourseTest {
    private Course course;
    
    @Before
    public void setUp() throws Exception {
        course = new Course();
    }

    @Test
    public void getSetName() {
        String name = "Tietokantojen perusteet";
        course.setName(name);
        assertEquals(name, course.getName());
    }

    @Test
    public void getSetCode() {
        String code = "TKTL22091";
        course.setCode(code);
        assertEquals(code, course.getCode());
    }

    @Test
    public void getSetDescription() {
        String desc = "Lorem ipsum dolor sit amet";
        course.setDescription(desc);
        assertEquals(desc, course.getDescription());
    }

}
