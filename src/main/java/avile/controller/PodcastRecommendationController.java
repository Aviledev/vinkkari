package avile.controller;

import avile.domain.*;
import avile.service.CourseService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private CourseService courseService;

    @PostMapping("/podcasts")
    public String createOne(@Valid PodcastRecommendation podcastRecommendation, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(podcastRecommendation.getRecommendation(), podcastRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            return "recommendations";
        } else {
            podcastRecommendationService.addPodcastRecommendation(podcastRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "New podcast recommendation was created successfully.");
            return "redirect:/recommendations";
        }

    }

    @PostMapping("/podcasts/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        podcastRecommendationService.deletePodcastRecommendationById(id);
        return "redirect:/recommendations";
    }


    @PostMapping("/podcasts/edit")
    public String updateOne(@Valid PodcastRecommendation podcastRecommendation, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(podcastRecommendation.getRecommendation(), podcastRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            return "recommendation_podcast_edit";
        } else {
            podcastRecommendationService.updatePodcastRecommendation(podcastRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Podcast recommendation was updated successfully.");
            return "redirect:/recommendations/"+ podcastRecommendation.getRecommendation().getId();
        }
    }

}
