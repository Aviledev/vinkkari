package avile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookRecommendationController {

    @PostMapping()
    public void createOne(@RequestParam Long id) {

    }

    @PostMapping()
    public void deleteOne(@RequestParam Long id) {

    }

    @PostMapping()
    public void updateOne(@RequestParam Long id) {

    }

}
