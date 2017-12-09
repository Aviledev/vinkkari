package avile.repository;

import avile.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>{
    List<Course> findByNameIsLike(String name);
}
