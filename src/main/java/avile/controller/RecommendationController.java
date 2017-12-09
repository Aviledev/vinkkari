package avile.controller;

import avile.domain.*;
import avile.enums.RecommendationType;
import avile.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RecommendationController {

    @Autowired
    BookRecommendationService bookRecommendationService;

    @Autowired
    VideoRecommendationService videoRecommendationService;

    @Autowired
    PodcastRecommendationService podcastRecommendationService;

    @Autowired
    BlogpostRecommendationService blogpostRecommendationService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    CourseService courseService;

    @Autowired
    AccountService accountService;


    @GetMapping(path = {"", "/", "/home", "/recommendations"})
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("bookRecommendation", new BookRecommendation());
        model.addAttribute("videoRecommendation", new VideoRecommendation());
        model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
        model.addAttribute("podcastRecommendation", new PodcastRecommendation());

        if(accountService.getAuthenticatedAccount() != null)  {
            model.addAttribute("recommendations", recommendationService.getRecommendationsForAccount(accountService.getAuthenticatedAccount()));
        } else {
            model.addAttribute("recommendations", recommendationService.getRecommendations());
        }

        return "recommendations";
    }

    @GetMapping("/recommendations/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        return getRecommendationFromRecommendationId(model, id);
    }

    @GetMapping("/recommendations/{id}/edit")
    public String getEditOne(Model model, @PathVariable Long id) {
        return getRecommendationFromRecommendationId(model, id) + "_edit";
    }

    @PostMapping("/recommendations/search")
    public String searchRecommendationByTitle(Model model, @RequestParam(required = false) String key) {
        model.addAttribute("recommendations", recommendationService.findRecommendationsBy(key));
        model.addAttribute("searchTerm", key);
        return "search_results";
    }

    @GetMapping("/recommendations/search")
    public String searchRecommendationByTitleGet(Model model, @RequestParam(required = false) String key) {
        return searchRecommendationByTitle(model, key);
    }

    private String getRecommendationFromRecommendationId(Model model, Long id) {
        Recommendation recommendation = recommendationService.getRecommendation(id);
        model.addAttribute("courses", courseService.getCourses());

        if (recommendation.getRecommendationType() == RecommendationType.BOOK) {
            BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendationByRecommendationId(id);
            bookRecommendation.getRecommendation().setRawTags(bookRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("bookRecommendation", bookRecommendation);
        } else if (recommendation.getRecommendationType() == RecommendationType.VIDEO) {
            VideoRecommendation videoRecommendation = videoRecommendationService.getVideoRecommendationByRecommendationId(id);
            videoRecommendation.getRecommendation().setRawTags(videoRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("videoRecommendation", videoRecommendation);
        } else if (recommendation.getRecommendationType() == RecommendationType.PODCAST) {
            PodcastRecommendation podcastRecommendation = podcastRecommendationService.getPodcastRecommendationByRecommendationId(id);
            podcastRecommendation.getRecommendation().setRawTags(podcastRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("podcastRecommendation", podcastRecommendation);
        } else if (recommendation.getRecommendationType() == RecommendationType.BLOGPOST) {
            BlogpostRecommendation blogpostRecommendation = blogpostRecommendationService.getBlogpostRecommendationByRecommendationId(id);
            blogpostRecommendation.getRecommendation().setRawTags(blogpostRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("blogpostRecommendation", blogpostRecommendation);
        }

        return "recommendation_" + recommendation.getRecommendationType();
    }

}
