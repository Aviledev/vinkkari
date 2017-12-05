package avile.controller;

import avile.domain.BlogpostRecommendation;
import avile.domain.BookRecommendation;
import avile.domain.Course;
import avile.domain.PodcastRecommendation;
import avile.domain.VideoRecommendation;
import avile.enums.RecommendationType;
import avile.service.BookRecommendationService;
import avile.service.CourseService;
import avile.service.RecommendationService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class BookRecommendationController {

    @Autowired
    BookRecommendationService bookRecommendationService;

    @Autowired
    RecommendationService recommendationService;
    
    @Autowired
    CourseService courseService;

    @PostMapping("/books")
    public String createOne(@Valid BookRecommendation bookRecommendation, BindingResult bs, Model model, final RedirectAttributes redirectAttributes) {        
        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("recommendations", recommendationService.getRecommendations());
            model.addAttribute("videoRecommendation", new VideoRecommendation());
            model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
            model.addAttribute("podcastRecommendation", new PodcastRecommendation());
            return "recommendations";
        } else {
            
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
            return "redirect:/recommendations/"+ bookRecommendation.getRecommendation().getId();
        }
    }

}
