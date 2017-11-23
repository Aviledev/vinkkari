package avile.controller;

import avile.repository.BookRecommendationRepository;
import avile.service.BookRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookRecommendationController {

    @Autowired
    BookRecommendationRepository bookRecommendationRepository;

    @Autowired
    BookRecommendationService bookRecommendationService;

    @PostMapping()
    public void createOne(@RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String isbn) {

    }

    @PostMapping()
    public void deleteOne(@RequestParam Long id) {

    }

    @PostMapping()
    public void updateOne(@RequestParam Long id) {

    }

}
