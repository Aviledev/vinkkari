package avile.controller;

import avile.domain.BookRecommendation;
import avile.domain.Recommendation;
import avile.enums.RecommendationType;
import avile.repository.BookRecommendationRepository;
import avile.repository.RecommendationRepository;
import avile.service.BookRecommendationService;
import avile.service.RecommendationService;
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
    RecommendationService recommendationService;

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

    @GetMapping("/recommendations")
    public String getAll(Model model) {
        model.addAttribute("recommendations", recommendationService.getRecommendations());
        return "recommendations";
    }

    @GetMapping("/recommendations/{id}")

    @PostMapping("/recommendation/search")
    public String searchRecommendationByTitle(Model model, @RequestParam(required = false) String title) {
        List<BookRecommendation> books = bookRecommendationService.getBookRecommendationsWithTitleLike("%"+title+"%");
        model.addAttribute("books", books);
        model.addAttribute("searchTerm", title);
        return "search_results";
    }

}