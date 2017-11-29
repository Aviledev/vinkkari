package avile.controller;

import avile.domain.BlogpostRecommendation;
import avile.domain.BookRecommendation;
import avile.domain.PodcastRecommendation;
import avile.domain.VideoRecommendation;
import avile.repository.PodcastRecommendationRepository;
import avile.service.PodcastRecommendationService;
import avile.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PodcastRecommendationController {

    @Autowired
    PodcastRecommendationService podcastRecommendationService;

    @Autowired
    RecommendationService recommendationService;

    @PostMapping("/podcasts")
    public String createOne(@Valid PodcastRecommendation podcastRecommendation, BindingResult bs, Model model) {

        if (bs.hasErrors()) {
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            return "recommendations";
        } else {
            podcastRecommendationService.addPodcastRecommendation(podcastRecommendation);
            return "redirect:/recommendations";
        }

    }

    @PostMapping("/podcasts/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        podcastRecommendationService.deletePodcastRecommendationById(id);
        return "redirect:/recommendations";
    }


    @PostMapping("/podcasts/edit")
    public String updateOne(@Valid PodcastRecommendation podcastRecommendation, BindingResult bs, Model model) {
        if (bs.hasErrors()) {
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            return "recommendation_podcast_edit";
        } else {
            podcastRecommendationService.updatePodcastRecommendation(podcastRecommendation);
            return "redirect:/recommendations/"+ podcastRecommendation.getRecommendation().getId();
        }
    }

}
