package avile.repository;

import avile.domain.PodcastRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PodcastRecommendationRepository extends JpaRepository<PodcastRecommendation, Long>{

    PodcastRecommendation findByRecommendationId(Long id);
    List<PodcastRecommendation> findByAuthorIsLike(String author);
    List<PodcastRecommendation> findByNameIsLike(String name);

}
