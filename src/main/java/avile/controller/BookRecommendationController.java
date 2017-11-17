package avile.controller;

import avile.domain.BookRecommendation;
import avile.repository.BookRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;

@Controller
public class BookRecommendationController {

    @Autowired
    BookRecommendationRepository bookRecomRepo;

    @Autowired
    public void setUp() {
        BookRecommendation b1 = new BookRecommendation();
        b1.setTitle("eka kirja");
        b1.setAuthor("Kari Ojala");
        b1.setType("Kirja");
        b1.setIsbn("3435323");
        b1.setDate(new Date());
        bookRecomRepo.save(b1);

        BookRecommendation b2 = new BookRecommendation();
        b2.setTitle("toka kirja");
        b2.setAuthor("Jaakko Peltola");
        b2.setType("Kirja");
        b2.setIsbn("5235235");
        b2.setDate(new Date());
        bookRecomRepo.save(b2);
    }

    @GetMapping("/books/")
    public String getAll(Model model) {
        model.addAttribute("recommendations", bookRecomRepo.findAll());
        return "recommendations";
    }

    @GetMapping("/books/{id}")
    public String getOneById(Long Id) {

        return "";
    }

    @PostMapping("/books/")
    public String createOne() {

        return "redirect:/books";
    }

    @PutMapping("/books/{id}")
    public String updateOne(Long id) {

        return "";
    }

}
