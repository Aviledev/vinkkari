package avile.controller;

import avile.domain.BookRecommendation;
import avile.domain.Recommendation;
import avile.enums.RecommendationType;
import avile.repository.BookRecommendationRepository;
import avile.repository.RecommendationRepository;
import avile.service.BookRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class RecommendationController {

    @Autowired
    BookRecommendationService bookRecommendationService;


    @Autowired
    public void setUp() {
        BookRecommendation b1 = new BookRecommendation();
        b1.setTitle("eka kirja");
        b1.setAuthor("Kari Ojala");
        b1.setType(RecommendationType.BOOK);
        b1.setIsbn("3435323");
        b1.setDate(new Date());

        bookRecommendationService.addBookRecommendation(b1);


        BookRecommendation b2 = new BookRecommendation();
        b2.setTitle("toka kirja");
        b2.setAuthor("Jaakko Peltola");
        b2.setType(RecommendationType.BOOK);
        b2.setIsbn("5235235");
        b2.setDate(new Date());
        bookRecommendationService.addBookRecommendation(b2);
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        model.addAttribute("recommendations", bookRecommendationService.getBookRecommendations());
        return "recommendations";
    }

    @GetMapping("/books/{id}")
    public String getOneById(Model model, @PathVariable Long id) {
        BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendation(id);
        model.addAttribute("recommendation", bookRecommendation);
        return "recommendation_" + bookRecommendation.getType();
    }

    @PostMapping("/books")
    public String createOne(@RequestParam String title,
                            @RequestParam String author,
                            @RequestParam String isbn
                           /* @RequestParam String tags,*/
                           /* @RequestParam String prerequisiteCourses, */
                           /* @RequestParam String relatedCourses */) {
        BookRecommendation bookRecommendation = new BookRecommendation();

        bookRecommendation.setTitle(title);
        bookRecommendation.setAuthor(author);
        bookRecommendation.setIsbn(isbn);
        bookRecommendation.setType(RecommendationType.BOOK);
        bookRecommendation.setDate(new Date());

        bookRecommendationService.addBookRecommendation(bookRecommendation);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String getUpdateOne(Model model, @PathVariable Long id) {
        BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendation(id);
        model.addAttribute("recommendation", bookRecommendation);
        return "recommendation_" + bookRecommendation.getType() + "_edit";
    }

    @PostMapping("/books/{id}/edit")
    public String saveUpdateOne(@PathVariable Long id,
                                @RequestParam String title,
                                @RequestParam String author,
                                @RequestParam String isbn
                           /* @RequestParam String tags,*/
                           /* @RequestParam String prerequisiteCourses, */
                           /* @RequestParam String relatedCourses */) {
        BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendation(id);
        bookRecommendation.setTitle(title);
        bookRecommendation.setAuthor(author);
        bookRecommendation.setIsbn(isbn);
        bookRecommendation.setType(RecommendationType.BOOK);
        bookRecommendation.setDate(new Date());

        bookRecommendationService.updateBookRecommendation(bookRecommendation);

        return "redirect:/books/" + id;
    }

    @PostMapping("/books/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        bookRecommendationService.deleteBookRecommendationById(id);
        return "redirect:/books";
    }

    @PostMapping("/books/search")
    public String searchBookByTitle(Model model, @RequestParam(required = false) String bookTitle) {
        List<BookRecommendation> books = bookRecommendationService.getBookRecommendationsWithTitleLike("%"+bookTitle+"%");
        model.addAttribute("books", books);
        model.addAttribute("searchTerm", bookTitle);
        return "search_results";
    }

}