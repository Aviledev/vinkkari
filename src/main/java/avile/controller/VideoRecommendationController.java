package avile.controller;

import avile.domain.*;
import avile.service.CourseService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private CourseService courseService;

    @PostMapping("/videos")
    public String createOne(@Valid VideoRecommendation videoRecommendation, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(videoRecommendation.getRecommendation(), videoRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendations";
        } else {
            videoRecommendationService.addVideoRecommendation(videoRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "New video recommendation was created successfully.");
            return "redirect:/recommendations";
        }

    }

    @PostMapping("/videos/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        videoRecommendationService.deleteVideoRecommendationById(id);
        return "redirect:/recommendations";
    }


    @PostMapping("/videos/edit")
    public String updateOne(@Valid VideoRecommendation videoRecommendation, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(videoRecommendation.getRecommendation(), videoRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendation_video_edit";
        } else {
            videoRecommendationService.updateVideoRecommendation(videoRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Video recommendation was updated successfully.");
            return "redirect:/recommendations/" + videoRecommendation.getRecommendation().getId();
        }
    }

}
