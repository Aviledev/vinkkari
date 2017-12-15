package avile.service;

import avile.domain.Account;
import avile.domain.Recommendation;
import avile.enums.RecommendationType;
import avile.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecommendationService {

    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    BookRecommendationService bookRecommendationService;

    @Autowired
    VideoRecommendationService videoRecommendationService;

    @Autowired
    BlogpostRecommendationService blogpostRecommendationService;

    @Autowired
    PodcastRecommendationService podcastRecommendationService;

    @Autowired
    TagService tagService;

    @Autowired
    CourseService courseService;

    public Recommendation getRecommendation(Long id) {
        return recommendationRepository.findOne(id);
    }


    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll();
    }

    public List<Recommendation> getRecommendationsByType(RecommendationType type) {
        return recommendationRepository.findRecommendationsByRecommendationType(type);
    }

    public Set<Recommendation> findRecommendationsBy(String key) {
        Set<Recommendation> recommendations = new HashSet<>();
        recommendations.addAll(getRecommendationsWithTitleLike(key));
        recommendations.addAll(tagService.findRecommendationsWithTag(key));
        recommendations.addAll(getRecommendationsWithAuthorLike(key));
        recommendations.addAll(getRecommendationsWithNameLike(key));
        recommendations.addAll(bookRecommendationService.getRecommendationsWithIsbn(key));
        recommendations.addAll(getRecommendationsWithPrerequisiteCourses(key));
        recommendations.addAll(getRecommendationsWithRelatedCourses(key));
        return recommendations;
    }

    private Set<Recommendation> getRecommendationsWithPrerequisiteCourses(String key) {
        return courseService.getRecommendationsWithCourseAsPrerequisite(key);
    }

    private Set<Recommendation> getRecommendationsWithRelatedCourses(String key) {
        return courseService.getRecommendationsWithCourseAsRelation(key);
    }

    private List<Recommendation> getRecommendationsWithTitleLike(String title) {
        return recommendationRepository.findRecommendationsByTitleIsLikeIgnoreCase("%" + title + "%");
    }

    private List<Recommendation> getRecommendationsWithAuthorLike(String key) {
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.addAll(bookRecommendationService.getRecommendationsWithAuthorLike(key));
        recommendations.addAll(videoRecommendationService.getRecommendationsWithAuthorLike(key));
        recommendations.addAll(blogpostRecommendationService.getRecommendationsWithAuthorLike(key));
        recommendations.addAll(podcastRecommendationService.getRecommendationsWithAuthorLike(key));
        return recommendations;
    }

    private List<Recommendation> getRecommendationsWithNameLike(String key) {
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.addAll(podcastRecommendationService.getRecommendationsWithNameLike(key));
        recommendations.addAll(blogpostRecommendationService.getRecommendationsWithNameLike(key));
        return recommendations;
    }

    public List<Recommendation> getRecommendationsForAccount(Account account) {
        return recommendationRepository.findRecommendationsByCheckersNotContaining(account);
    }

    public List<Recommendation> getRecommendationsForAccountAndFilter(Account account, RecommendationType recommendationType) {
        return recommendationRepository.findRecommendationsByRecommendationTypeAndCheckersNotContaining(recommendationType, account);
    }
}
