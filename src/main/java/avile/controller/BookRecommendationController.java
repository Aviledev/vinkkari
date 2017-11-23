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
        return "redirect:/recommendations";

    }

    @PostMapping("/books/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        bookRecommendationService.deleteBookRecommendationById(id);
        return "redirect:/recommendations";
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
        return "redirect:/recommendations/" + id;
    }

}
