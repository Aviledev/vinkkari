package avile.service;

import avile.domain.VideoRecommendation;
import avile.repository.VideoRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoRecommendationService {

    @Autowired
    private VideoRecommendationRepository videoRecommendationRepository;

    public VideoRecommendation getVideoRecommendationByRecommendationId(Long id) {
        return videoRecommendationRepository.findByRecommendationId(id);
    }
}
