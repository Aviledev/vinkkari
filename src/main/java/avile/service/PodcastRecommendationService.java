package avile.service;

import avile.domain.PodcastRecommendation;
import avile.domain.Recommendation;
import avile.repository.PodcastRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PodcastRecommendationService {

    @Autowired
    private PodcastRecommendationRepository podcastRecommendationRepository;

    public PodcastRecommendation getPodcastRecommendationByRecommendationId(Long id) {
        return podcastRecommendationRepository.findByRecommendationId(id);
    }

    public Long addPodcastRecommendation(PodcastRecommendation podcastRecommendation) {
        return this.podcastRecommendationRepository.save(podcastRecommendation).getId();
    }

    public void deletePodcastRecommendationById(Long id) {
        this.podcastRecommendationRepository.delete(id);
    }

    public Long updatePodcastRecommendation(PodcastRecommendation podcastRecommendation) {
        return this.addPodcastRecommendation(podcastRecommendation);
    }

    public List<Recommendation> getRecommendationsWithAuthorLike(String author) {
        List<Recommendation> recommendations = new ArrayList<>();
        podcastRecommendationRepository.findByAuthorIsLike("%"+author+"%").forEach(bookRecommendation -> recommendations.add(bookRecommendation.getRecommendation()));
        return recommendations;
    }

    public List<Recommendation> getRecommendationsWithNameLike(String key) {
        List<Recommendation> recommendations = new ArrayList<>();
        podcastRecommendationRepository.findByNameIsLike("%"+key+"%").forEach(podcastRecommendation -> recommendations.add(podcastRecommendation.getRecommendation()));;
        return recommendations;
    }
}
