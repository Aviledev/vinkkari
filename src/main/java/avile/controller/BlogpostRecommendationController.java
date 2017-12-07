package avile.controller;

import avile.domain.*;
import avile.service.BlogpostRecommendationService;
import avile.service.CourseService;
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
public class BlogpostRecommendationController {

    @Autowired
    BlogpostRecommendationService blogpostRecommendationService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/blogposts")
    public String createOne(@Valid BlogpostRecommendation blogpostRecommendation, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(blogpostRecommendation.getRecommendation(), blogpostRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendations";
        } else {
            blogpostRecommendationService.addBlogpostRecommendation(blogpostRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Blogpost recommendation was updated successfully.");
            return "redirect:/recommendations";
        }

    }

    @PostMapping("/blogposts/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        blogpostRecommendationService.deleteBlogpostRecommendationById(id);
        return "redirect:/recommendations";
    }


    @PostMapping("/blogposts/edit")
    public String updateOne(@Valid BlogpostRecommendation blogpostRecommendation, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(blogpostRecommendation.getRecommendation(), blogpostRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("bookRecommendation", new BookRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendation_blogpost_edit";
        } else {
            blogpostRecommendationService.updateBlogpostRecommendation(blogpostRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Blogpost recommendation was updated successfully.");
            return "redirect:/recommendations/" + blogpostRecommendation.getRecommendation().getId();
        }
    }

}
