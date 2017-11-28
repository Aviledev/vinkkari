package avile.service;

import avile.domain.VideoRecommendation;
import avile.repository.VideoRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoRecommendationService {

    @Autowired
    private VideoRecommendationRepository videoRecommendationRepository;

    public List<VideoRecommendation> getVideoRecommendations() {
        return videoRecommendationRepository.findAll();
    }

    public Long addVideoRecommendation(VideoRecommendation videoRecommendation) {
        return videoRecommendationRepository.save(videoRecommendation).getId();
    }


    public VideoRecommendation getVideoRecommendationByRecommendationId(Long id) {
        return videoRecommendationRepository.findByRecommendationId(id);
    }
}
