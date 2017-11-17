package avile.repository;

import avile.domain.BookRecommendation;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRecommendationRepository extends JpaRepository<BookRecommendation, Long>{
    
}
