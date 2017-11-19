package avile.controller;

import avile.domain.BookRecommendation;
import avile.repository.BookRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class BookRecommendationController {

    @Autowired
    BookRecommendationRepository bookRecommendationRepository;

    @Autowired
    public void setUp() {
        BookRecommendation b1 = new BookRecommendation();
        b1.setTitle("eka kirja");
        b1.setAuthor("Kari Ojala");
        b1.setType("Book");
        b1.setIsbn("3435323");
        b1.setDate(new Date());
        bookRecommendationRepository.save(b1);

        BookRecommendation b2 = new BookRecommendation();
        b2.setTitle("toka kirja");
        b2.setAuthor("Jaakko Peltola");
        b2.setType("Book");
        b2.setIsbn("5235235");
        b2.setDate(new Date());
        bookRecommendationRepository.save(b2);
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        model.addAttribute("recommendations", bookRecommendationRepository.findAll());
        return "recommendations";
    }

    @GetMapping("/books/{id}")
    public String getOneById(Long Id) {

        return "";
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
        bookRecommendation.setType("Book");
        bookRecommendation.setDate(new Date());
        bookRecommendationRepository.save(bookRecommendation);
        return "redirect:/books";
    }

    @PutMapping("/books/{id}")
    public String updateOne(Long id) {

        return "";
    }

}
