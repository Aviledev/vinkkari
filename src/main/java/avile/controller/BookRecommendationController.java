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
    public String createOne(@Valid BookRecommendation bookRecommendation, @RequestParam String tags, BindingResult bs, Model model, final RedirectAttributes redirectAttributes) {

        TagValidator tv = new TagValidator();

        List<Tag> tagsList = tagService.parseTagsFromString(tags);

        for (Tag tag :
                tagsList) {
            tv.validate(tag, bs);
        }

        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendations";
        } else {
            tagService.assignTagsToRecommendation(bookRecommendation.getRecommendation(), tags);
            bookRecommendationService.addBookRecommendation(bookRecommendation);
            //success or warning
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
    public String updateOne(@Valid BookRecommendation bookRecommendation, BindingResult bs, Model model) {
        if (bs.hasErrors()) {
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendation_book_edit";
        } else {
            bookRecommendationService.updateBookRecommendation(bookRecommendation);
            return "redirect:/recommendations/" + bookRecommendation.getRecommendation().getId();
        }
    }

}
