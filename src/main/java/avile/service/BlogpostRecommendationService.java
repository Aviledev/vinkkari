package avile.service;

import avile.domain.BlogpostRecommendation;
import avile.repository.BlogpostRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogpostRecommendationService {

    @Autowired
    private BlogpostRecommendationRepository blogpostRecommendationRepository;

    public BlogpostRecommendation getBlogpostRecommendationByRecommendationId(Long id) {
        return blogpostRecommendationRepository.findByRecommendationId(id);
    }
}
