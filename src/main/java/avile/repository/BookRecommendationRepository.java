package avile.repository;

import avile.domain.BookRecommendation;
import java.io.Serializable;
import java.util.List;

import avile.domain.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRecommendationRepository extends JpaRepository<BookRecommendation, Long>{
    BookRecommendation findByRecommendationId(Long id);
    List<BookRecommendation> findByAuthorIsLikeIgnoreCase(String author);
    List<BookRecommendation> findByIsbn(String isbn);
}
