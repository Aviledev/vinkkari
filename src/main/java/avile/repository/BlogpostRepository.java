package avile.repository;

import avile.domain.BookRecommendation;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogpostRepository extends JpaRepository<BookRecommendation, Long>{
 
}
