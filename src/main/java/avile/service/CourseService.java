package avile.service;

import avile.domain.BookRecommendation;
import avile.domain.Course;
import avile.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course getCourse(Long id) {
        return courseRepository.findOne(id);
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Long addCourse(Course course) {
        return courseRepository.save(course).getId();
    }

    public Long updateCourse(Course course) {
        return this.addCourse(course);
    }

    public void deleteCourse(Course course) {
        this.deleteCourseById(course.getId());
    }

    public void deleteCourseById(Long id) {
        this.courseRepository.delete(id);
    }
}
