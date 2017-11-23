package avile.repository;

import avile.domain.PodcastRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PodcastRecommendationRepository extends JpaRepository<PodcastRecommendation, Long>{
    
}
