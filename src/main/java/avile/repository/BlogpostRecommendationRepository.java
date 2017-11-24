package avile.repository;

import avile.domain.BlogpostRecommendation;
import avile.domain.BookRecommendation;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogpostRecommendationRepository extends JpaRepository<BookRecommendation, Long>{

    BlogpostRecommendation findByRecommendationId(Long id);
}
