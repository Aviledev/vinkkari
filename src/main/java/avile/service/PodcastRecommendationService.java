package avile.service;

import avile.domain.PodcastRecommendation;
import avile.repository.PodcastRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
