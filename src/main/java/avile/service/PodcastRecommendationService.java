package avile.service;

import avile.domain.PodcastRecommendation;
import avile.domain.Recommendation;
import avile.repository.PodcastRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PodcastRecommendationService {

    @Autowired
    private PodcastRecommendationRepository podcastRecommendationRepository;

    @Autowired
    private AccountService accountService;

    public List<PodcastRecommendation> getPodcastRecommendations() {
        return podcastRecommendationRepository.findAll();
    }

    public PodcastRecommendation getPodcastRecommendationByRecommendationId(Long id) {
        PodcastRecommendation podcastRecommendation = podcastRecommendationRepository.findByRecommendationId(id);
        podcastRecommendation.getRecommendation().setRawTags(podcastRecommendation.getRecommendation().getTagsAsString());
        return podcastRecommendation;

    }

    public Long addPodcastRecommendation(PodcastRecommendation podcastRecommendation) {
        if (accountService.getAuthenticatedAccount() != null) {
            podcastRecommendation.getRecommendation().setCreator(accountService.getAuthenticatedAccount());
        }
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
        podcastRecommendationRepository.findByAuthorIsLikeIgnoreCase("%" + author + "%").forEach(bookRecommendation -> recommendations.add(bookRecommendation.getRecommendation()));
        return recommendations;
    }

    public List<Recommendation> getRecommendationsWithNameLike(String key) {
        List<Recommendation> recommendations = new ArrayList<>();
        podcastRecommendationRepository.findByNameIsLikeIgnoreCase("%" + key + "%").forEach(podcastRecommendation -> recommendations.add(podcastRecommendation.getRecommendation()));
        ;
        return recommendations;
    }
}
