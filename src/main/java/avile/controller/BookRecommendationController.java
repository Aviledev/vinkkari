package avile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BookRecommendationController {
    
    @GetMapping("/books/")
    @ResponseBody
    public String getAll() {
        
        return "all";
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
