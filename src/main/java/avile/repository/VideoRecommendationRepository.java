package avile.repository;

import avile.domain.VideoRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRecommendationRepository extends JpaRepository<VideoRecommendation, Long> {
    VideoRecommendation findByRecommendationId(Long id);
    List<VideoRecommendation> findByAuthorIsLike(String author);

}
