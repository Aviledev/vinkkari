package avile.service;

import avile.domain.Recommendation;
import avile.domain.VideoRecommendation;
import avile.repository.VideoRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void deleteVideoRecommendationById(Long id) {
        this.videoRecommendationRepository.delete(id);
    }

    public Long updateVideoRecommendation(VideoRecommendation videoRecommendation) {
        return this.addVideoRecommendation(videoRecommendation);
    }
    public List<Recommendation> getRecommendationsWithAuthorLike(String author) {
        List<Recommendation> recommendations = new ArrayList<>();
        videoRecommendationRepository.findByAuthorIsLike("%"+author+"%").forEach(bookRecommendation -> recommendations.add(bookRecommendation.getRecommendation()));
        return recommendations;
    }

}
