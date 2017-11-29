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
    public void setUp() {
        BookRecommendation b1 = new BookRecommendation();
        b1.getRecommendation().setTitle("eka kirja");
        b1.setAuthor("Kari Ojala");
        b1.setIsbn("3435323");
        bookRecommendationService.addBookRecommendation(b1);


        BookRecommendation b2 = new BookRecommendation();
        b2.getRecommendation().setTitle("toka kirja");
        b2.setAuthor("Jaakko Peltola");
        b2.setIsbn("5235235");
        bookRecommendationService.addBookRecommendation(b2);

        VideoRecommendation v1 = new VideoRecommendation();
        v1.getRecommendation().setTitle("Kissavideo");
        v1.setAuthor("Kissa Fani");
        v1.setUrl("http://google.fi");
        v1.getRecommendation().setDescription("Kissa-aiheinen video.");
        videoRecommendationService.addVideoRecommendation(v1);
    }

    @GetMapping("/recommendations")
    public String getAll(Model model) {
        model.addAttribute("recommendations", recommendationService.getRecommendations());
        model.addAttribute("bookRecommendation", new BookRecommendation());
        model.addAttribute("videoRecommendation", new VideoRecommendation());
        model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
        model.addAttribute("podcastRecommendation", new PodcastRecommendation());
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
    public String searchRecommendationByTitle(Model model, @RequestParam(required = false) String title) {
        model.addAttribute("recommendations", recommendationService.getRecommendationsWithTitleLike(title));
        model.addAttribute("searchTerm", title);
        return "search_results";
    }

    // Used for parsing the typed recommendation item from the "super" recommendation and adding it to model
    private String getRecommendationFromRecommendationId(Model model, Long id) {
        Recommendation recommendation = recommendationService.getRecommendation(id);

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