package avile.controller;

import avile.domain.BlogpostRecommendation;
import avile.domain.BookRecommendation;
import avile.domain.Course;
import avile.domain.PodcastRecommendation;
import avile.domain.VideoRecommendation;
import avile.service.AccountService;
import avile.service.CourseService;
import avile.service.RecommendationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    AccountService accountService;

    @Autowired
    RecommendationService recommendationService;

    @PostMapping("/course")
    public String createOne(@Valid Course course, BindingResult bs, Model model, final RedirectAttributes redirectAttributes) {
        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            return "courses";
        } else {

            courseService.addCourse(course);

            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "New course was created successfully.");
            return "redirect:/courses";
        }
    }

    @PostMapping("/courses/{id}/delete")
    public String deleteOne(@PathVariable Long id) {
        courseService.deleteCourse(courseService.getCourse(id));
        return "redirect:/courses";
    }

    @PostMapping("/courses/edit")
    public String updateOne(@Valid Course course, BindingResult bs, Model model, RedirectAttributes redirectAttributes) {
        if (bs.hasErrors()) {
            model.addAttribute("courses", courseService.getCourses());
            return "course_item_edit";
        } else {
            courseService.updateCourse(course);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Course was successfully updated.");
            return "redirect:/courses/" + course.getId();
        }
    }

    @GetMapping(path = {"/courses"})
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("course", new Course());
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        Course course = courseService.getCourse(id);
        model.addAttribute("course", course);
        return "course_item";
    }

    @GetMapping("/courses/{id}/edit")
    public String getEditOne(Model model, @PathVariable Long id) {
        Course course = courseService.getCourse(id);
        model.addAttribute("course", course);
        return "course_item_edit";
    }

    @GetMapping("/courses/{id}/related")
    public String getRecommendationsForCourse(Model model, @PathVariable Long id) {
        Course course = courseService.getCourse(id);
        model.addAttribute("recommendations", courseService.getRecommendationsWithCourseAsRelation(course.getName()));
        model.addAttribute("course" , course);
        
        if (accountService.getAuthenticatedAccount() != null) {
            model.addAttribute("userRecommendations", recommendationService.getRecommendationsForAccount(accountService.getAuthenticatedAccount()));
        }

        return "recommendations_for_course";
    }

}
