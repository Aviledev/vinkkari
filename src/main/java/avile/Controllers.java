package avile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controllers {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/other")
    @ResponseBody
    public String other() {
        return "Hei Maailma!";
    }
}