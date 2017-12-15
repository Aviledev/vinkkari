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
    public String getAll(Model model, @RequestParam(value = "filter", required = false) String filter) {
        model.addAttribute("courses", courseService.getCourses());


        model.addAttribute("bookRecommendation", new BookRecommendation());
        model.addAttribute("videoRecommendation", new VideoRecommendation());
        model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
        model.addAttribute("podcastRecommendation", new PodcastRecommendation());

        RecommendationType type = null;

        if (filter == null) {
            model.addAttribute("recommendations", recommendationService.getRecommendations());
        } else {
            switch (filter) {
                case "books":
                    type = RecommendationType.BOOK;
                    break;
                case "videos":
                    type = RecommendationType.VIDEO;
                    break;
                case "blogposts":
                    type = RecommendationType.BLOGPOST;
                    break;
                case "podcasts":
                    type = RecommendationType.PODCAST;
                    break;
                default:
                    type = null;
                    break;
            }

            if (type == null) {
                model.addAttribute("recommendations", recommendationService.getRecommendations());
            } else {
                model.addAttribute("recommendations", recommendationService.getRecommendationsByType(type));
            }

        }
        if (accountService.getAuthenticatedAccount() != null) {
            if (filter == null || type == null) {
                model.addAttribute("userRecommendations", recommendationService.getRecommendationsForAccount(accountService.getAuthenticatedAccount()));
            } else {
                model.addAttribute("userRecommendations", recommendationService.getRecommendationsForAccountAndFilter(accountService.getAuthenticatedAccount(), type));
            }
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

    @PostMapping("/recommendations/{id}/check")
    public String toggleCheckForAccount(Model model, @PathVariable Long id) {
        if (accountService.getAuthenticatedAccount() != null) {
            accountService.toggleChecked(accountService.getAuthenticatedAccount(), recommendationService.getRecommendation(id));
        }
        return getRecommendationFromRecommendationId(model, id);
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

        if (accountService.getAuthenticatedAccount() != null) {
            if (recommendation.getCheckers().contains(accountService.getAuthenticatedAccount())) {
                model.addAttribute("checked", true);
            } else {
                model.addAttribute("checked", false);
            }
        }

        if (recommendation.getRecommendationType() == RecommendationType.BOOK) {
            model.addAttribute("bookRecommendation", bookRecommendationService.getBookRecommendationByRecommendationId(id));
        } else if (recommendation.getRecommendationType() == RecommendationType.VIDEO) {
            model.addAttribute("videoRecommendation", videoRecommendationService.getVideoRecommendationByRecommendationId(id));
        } else if (recommendation.getRecommendationType() == RecommendationType.PODCAST) {
            model.addAttribute("podcastRecommendation", podcastRecommendationService.getPodcastRecommendationByRecommendationId(id));
        } else if (recommendation.getRecommendationType() == RecommendationType.BLOGPOST) {
            model.addAttribute("blogpostRecommendation", blogpostRecommendationService.getBlogpostRecommendationByRecommendationId(id));
        }

        return "recommendation_" + recommendation.getRecommendationType();
    }

}
