package avile.controller;

import avile.domain.VideoRecommendation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class VideoRecommendationController {

    @PostMapping("/video")
    public String createOne(@Valid VideoRecommendation videoRecommendation, BindingResult bs) {
        if (bs.hasErrors()) {
            System.out.println("FORM VALIDATION ERRORS");
            System.exit(1);
        } else {
            System.out.println(videoRecommendation.getUrl());
            System.out.println(videoRecommendation.getRecommendation().getDate().toString());
            System.out.println(videoRecommendation.getRecommendation().getTitle());
            System.out.println(videoRecommendation.getRecommendation().getDescription());
        }
        return "redirect:/recommendations";
    }

    //@PostMapping()
    public void deleteOne(@RequestParam Long id) {

    }

    //@PostMapping()
    public void updateOne(@RequestParam Long id) {

    }

}
