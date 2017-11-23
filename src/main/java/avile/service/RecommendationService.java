package avile.service;

import avile.domain.BookRecommendation;
import avile.domain.Recommendation;
import avile.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    RecommendationRepository recommendationRepository;

    public List<Recommendation> getRecommendationsWithTitleLike(String title) {
        return recommendationRepository.findRecommendationsByTitleIsLike(title);
    }

    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll();
    }
}
