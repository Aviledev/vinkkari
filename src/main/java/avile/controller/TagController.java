package avile.controller;


import avile.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Propably not needed, unless admin features added
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag/{id}")
    public String getOne(@PathVariable Long id) {

        return "redirect:/recommendations/search?key="+tagService.getTag(id).getName();
    }
    
}
