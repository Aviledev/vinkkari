package avile.controller;

import avile.domain.BookRecommendation;
import avile.domain.Recommendation;
import avile.domain.VideoRecommendation;
import avile.enums.RecommendationType;

import avile.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
        b1.setTitle("eka kirja");
        b1.setAuthor("Kari Ojala");
        b1.setType(RecommendationType.BOOK);
        b1.setIsbn("3435323");
        b1.setDate(new Date());

        bookRecommendationService.addBookRecommendation(b1);


        BookRecommendation b2 = new BookRecommendation();
        b2.setTitle("toka kirja");
        b2.setAuthor("Jaakko Peltola");
        b2.setType(RecommendationType.BOOK);
        b2.setIsbn("5235235");
        b2.setDate(new Date());
        bookRecommendationService.addBookRecommendation(b2);
    }

    @GetMapping("/recommendations")
    public String getAll(Model model) {
        model.addAttribute("recommendations", recommendationService.getRecommendations());
        // Kytke kiinni videoRecommendation validointia varten
        model.addAttribute("videoRecommendation", new VideoRecommendation());
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
        switch (recommendation.getRecommendationType()) {
            case BOOK:
                model.addAttribute("recommendation", bookRecommendationService.getBookRecommendationByRecommendationId(id));
            case VIDEO:
                model.addAttribute("recommendation", videoRecommendationService.getVideoRecommendationByRecommendationId(id));
            case PODCAST:
                model.addAttribute("recommendation", podcastRecommendationService.getPodcastRecommendationByRecommendationId(id));
            case BLOGPOST:
                model.addAttribute("recommendation", blogpostRecommendationService.getBlogpostRecommendationByRecommendationId(id));
        }
        return "recommendation_" + recommendation.getRecommendationType();
    }

}