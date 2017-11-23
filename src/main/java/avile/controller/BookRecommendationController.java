package avile.controller;

import avile.domain.BookRecommendation;
import avile.enums.RecommendationType;
import avile.service.BookRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class BookRecommendationController {

    @Autowired
    BookRecommendationService bookRecommendationService;

    @GetMapping("/books/{id}")
    public String getOneById(Model model, @PathVariable Long id) {
        BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendation(id);
        model.addAttribute("recommendation", bookRecommendation);
        return "recommendation_" + bookRecommendation.getType();
    }

    @PostMapping("/books")
    public String createOne(@RequestParam String title,
                            @RequestParam String author,
                            @RequestParam String isbn) {
        BookRecommendation bookRecommendation = new BookRecommendation();
        bookRecommendation.setTitle(title);
        bookRecommendation.setAuthor(author);
        bookRecommendation.setIsbn(isbn);
        bookRecommendation.setType(RecommendationType.BOOK);
        bookRecommendation.setDate(new Date());
        bookRecommendationService.addBookRecommendation(bookRecommendation);
        return "redirect:/books";

    }

    @PostMapping("/books/{id}/delete")
    public String deleteOne(@RequestParam Long id) {
        bookRecommendationService.deleteBookRecommendationById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String getUpdateOne(Model model, @PathVariable Long id) {
        BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendation(id);
        model.addAttribute("recommendation", bookRecommendation);
        return "recommendation_" + bookRecommendation.getType() + "_edit";
    }

    @PostMapping("/books/{id}/edit")
    public String updateOne(@PathVariable Long id,
                            @RequestParam String title,
                            @RequestParam String author,
                            @RequestParam String isbn) {
        BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendation(id);
        bookRecommendation.setTitle(title);
        bookRecommendation.setAuthor(author);
        bookRecommendation.setIsbn(isbn);
        bookRecommendation.setType(RecommendationType.BOOK);
        bookRecommendation.setDate(new Date());
        bookRecommendationService.updateBookRecommendation(bookRecommendation);
        return "redirect:/books/" + id;
    }

}
