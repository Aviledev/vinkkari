package avile.controller;

import avile.domain.*;
import avile.service.BookRecommendationService;
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
public class BookRecommendationController {

    @Autowired
    BookRecommendationService bookRecommendationService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    CourseService courseService;

    @Autowired
    TagService tagService;

    @PostMapping("/books")
    public String createOne(@Valid BookRecommendation bookRecommendation, BindingResult bs, Model model, final RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(bookRecommendation.getRecommendation(), bookRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendations";
        } else {
            bookRecommendationService.addBookRecommendation(bookRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "New book recommendation was created successfully.");
            return "redirect:/recommendations";
        }

    }

    @PostMapping("/books/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        bookRecommendationService.deleteBookRecommendationById(id);
        return "redirect:/recommendations";
    }


    @PostMapping("/books/edit")
    public String updateOne(@Valid BookRecommendation bookRecommendation, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {

        tagService.assignTagsToRecommendation(bookRecommendation.getRecommendation(), bookRecommendation.getRecommendation().getRawTags(), bs);

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendation_book_edit";
        } else {
            bookRecommendationService.updateBookRecommendation(bookRecommendation);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Book recommendation was updated successfully.");
            return "redirect:/recommendations/" + bookRecommendation.getRecommendation().getId();
        }
    }

}
