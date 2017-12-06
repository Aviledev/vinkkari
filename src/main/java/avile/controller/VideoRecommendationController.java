package avile.controller;

import avile.domain.*;
import avile.service.RecommendationService;
import avile.service.TagService;
import avile.service.VideoRecommendationService;
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
public class VideoRecommendationController {

    @Autowired
    private VideoRecommendationService videoRecommendationService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private TagService tagService;

    @PostMapping("/videos")
    public String createOne(@Valid VideoRecommendation videoRecommendation, @RequestParam String tags, BindingResult bs, Model model) {

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
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendations";
        } else {
            tagService.assignTagsToRecommendation(videoRecommendation.getRecommendation(), tags);
            videoRecommendationService.addVideoRecommendation(videoRecommendation);
            return "redirect:/recommendations";
        }

    }

    @PostMapping("/videos/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        videoRecommendationService.deleteVideoRecommendationById(id);
        return "redirect:/recommendations";
    }


    @PostMapping("/videos/edit")
    public String updateOne(@Valid VideoRecommendation videoRecommendation, BindingResult bs, Model model) {
        if (bs.hasErrors()) {
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendation_video_edit";
        } else {
            videoRecommendationService.updateVideoRecommendation(videoRecommendation);
            return "redirect:/recommendations/" + videoRecommendation.getRecommendation().getId();
        }
    }

}
