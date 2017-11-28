package avile.controller;

import avile.domain.BlogpostRecommendation;
import avile.domain.BookRecommendation;
import avile.domain.PodcastRecommendation;
import avile.domain.VideoRecommendation;
import avile.service.RecommendationService;
import avile.service.VideoRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class VideoRecommendationController {

    @Autowired
    private VideoRecommendationService videoRecommendationService;

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/videos")
    public String createOne(@Valid VideoRecommendation videoRecommendation, BindingResult bs, Model model) {

        if (bs.hasErrors()) {

            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendations";
        } else {
            videoRecommendationService.addVideoRecommendation(videoRecommendation);
            return "redirect:/recommendations";
        }
    }

    //@PostMapping()
    public void deleteOne(@RequestParam Long id) {

    }

    //@PostMapping()
    public void updateOne(@RequestParam Long id) {

    }

}
