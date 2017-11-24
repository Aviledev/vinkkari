package avile.repository;

import avile.domain.VideoRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRecommendationRepository extends JpaRepository<VideoRecommendation, Long> {
    VideoRecommendation findByRecommendationId(Long id);
}
