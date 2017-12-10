package avile.repository;

import avile.domain.BlogpostRecommendation;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogpostRecommendationRepository extends JpaRepository<BlogpostRecommendation, Long>{

    BlogpostRecommendation findByRecommendationId(Long id);
    List<BlogpostRecommendation> findByAuthorIsLikeIgnoreCase(String author);
    List<BlogpostRecommendation> findByNameIsLikeIgnoreCase(String name);
}
