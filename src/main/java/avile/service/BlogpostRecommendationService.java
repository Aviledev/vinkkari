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

    public void deleteBlogpostRecommendationById(Long id) { this.blogpostRecommendationRepository.delete(id);
    }

    public Long addBlogpostRecommendation(BlogpostRecommendation blogpostRecommendation) {
        return this.blogpostRecommendationRepository.save(blogpostRecommendation).getId();
    }

    public Long updateBlogpostRecommendation(BlogpostRecommendation blogpostRecommendation) {
        return this.addBlogpostRecommendation(blogpostRecommendation);
    }
}
