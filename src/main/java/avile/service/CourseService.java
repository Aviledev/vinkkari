package avile.service;

import avile.domain.BookRecommendation;
import avile.domain.Course;
import avile.domain.Recommendation;
import avile.repository.CourseRepository;
import avile.repository.RecommendationRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RecommendationRepository recommendationRepository;

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
        return addCourse(course);
    }

    @Transactional
    public void deleteCourse(Course course) {
        for (Recommendation recommendation : recommendationRepository.findAll()) {
            if (recommendation.getPrerequisiteCourses().contains(course)) {
                recommendation.getPrerequisiteCourses().remove(course);
            }
            if (recommendation.getRelatedCourses().contains(course)) {
                recommendation.getRelatedCourses().remove(course);
            }
            recommendationRepository.save(recommendation);
        }

        courseRepository.delete(course.getId());
    }

}
