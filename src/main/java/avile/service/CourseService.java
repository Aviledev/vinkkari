package avile.service;

import avile.domain.BookRecommendation;
import avile.domain.Course;
import avile.domain.Recommendation;
import avile.repository.CourseRepository;
import avile.repository.RecommendationRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public List<Course> getByNameLike(String name) {
        List<Course> courses = courseRepository.findByNameIsLike("%"+name+"%");
        if(courses == null || courses.size() == 0) {
            return new ArrayList<>();
        }
        return courses;
    }

    public Set<Recommendation> getRecommendationsWithCourseAsPrerequisite(String key) {

        Set<Recommendation> recommendations = new HashSet<>();
        getByNameLike(key).forEach(course -> recommendations.addAll(course.getRecommendationsPrerequisite()));
        return recommendations;
    }
    public Set<Recommendation> getRecommendationsWithCourseAsRelation(String key) {
        Set<Recommendation> recommendations = new HashSet<>();
        getByNameLike(key).forEach(course -> recommendations.addAll(course.getRecommendationsRelated()));
        return recommendations;
    }
}
