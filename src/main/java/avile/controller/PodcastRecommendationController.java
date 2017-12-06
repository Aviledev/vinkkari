package avile.controller;

import avile.domain.*;
import avile.repository.PodcastRecommendationRepository;
import avile.service.PodcastRecommendationService;
import avile.service.RecommendationService;
import avile.service.TagService;
import avile.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PodcastRecommendationController {

    @Autowired
    private PodcastRecommendationService podcastRecommendationService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private TagService tagService;

    @PostMapping("/podcasts")
    public String createOne(@Valid PodcastRecommendation podcastRecommendation, @RequestParam String tags, BindingResult bs, Model model) {

        TagValidator tv = new TagValidator();

        if (tags != null && tags.split(",").length > 1) {
            List<Tag> tagsList = tagService.parseTagsFromString(tags);

            for (Tag tag :
                    tagsList) {
                tv.validate(tag, bs);
            }
        }

        if (bs.hasErrors()) {
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            return "recommendations";
        } else {
            tagService.assignTagsToRecommendation(podcastRecommendation.getRecommendation(), tags);
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
