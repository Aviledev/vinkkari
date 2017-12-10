package avile.service;

import avile.domain.BlogpostRecommendation;
import avile.domain.Recommendation;
import avile.repository.BlogpostRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BlogpostRecommendationService {

    @Autowired
    private BlogpostRecommendationRepository blogpostRecommendationRepository;

    @Autowired
    AccountService accountService;

    public BlogpostRecommendation getBlogpostRecommendationByRecommendationId(Long id) {
        BlogpostRecommendation blogpostRecommendation = blogpostRecommendationRepository.findByRecommendationId(id);
        blogpostRecommendation.getRecommendation().setRawTags(blogpostRecommendation.getRecommendation().getTagsAsString());
        return blogpostRecommendation;
    }

    public void deleteBlogpostRecommendationById(Long id) { this.blogpostRecommendationRepository.delete(id);
    }

    public Long addBlogpostRecommendation(BlogpostRecommendation blogpostRecommendation) {
        if(accountService.getAuthenticatedAccount() != null) {
            blogpostRecommendation.getRecommendation().setCreator(accountService.getAuthenticatedAccount());
        }
        return this.blogpostRecommendationRepository.save(blogpostRecommendation).getId();
    }

    public Long updateBlogpostRecommendation(BlogpostRecommendation blogpostRecommendation) {
        return this.addBlogpostRecommendation(blogpostRecommendation);
    }

    public List<Recommendation> getRecommendationsWithAuthorLike(String author) {
        List<Recommendation> recommendations = new ArrayList<>();
        blogpostRecommendationRepository.findByAuthorIsLikeIgnoreCase("%"+author+"%").forEach(bookRecommendation -> recommendations.add(bookRecommendation.getRecommendation()));
        return recommendations;
    }

    public List<Recommendation> getRecommendationsWithNameLike(String key) {
        List<Recommendation> recommendations = new ArrayList<>();
        blogpostRecommendationRepository.findByNameIsLikeIgnoreCase("%"+key+"%").forEach(blogpostRecommendation -> recommendations.add(blogpostRecommendation.getRecommendation()));
        return recommendations;
    }
}
