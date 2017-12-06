package avile.controller;

import avile.domain.*;
import avile.enums.RecommendationType;
import avile.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecommendationController {

    @Autowired
    BookRecommendationService bookRecommendationService;

    @Autowired
    VideoRecommendationService videoRecommendationService;

    @Autowired
    PodcastRecommendationService podcastRecommendationService;

    @Autowired
    BlogpostRecommendationService blogpostRecommendationService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    CourseService courseService;

    @Autowired
    TagService tagService;

    @Autowired
    public void setUp() {
        Course c1 = new Course();
        c1.setName("Aineopintojen harjoitustyö: Tietoliikenne");
        c1.setCode("TKT20012");
        courseService.addCourse(c1);

        Course c2 = new Course();
        c2.setName("Tietoturvan perusteet");
        c2.setCode("TKT20009");
        courseService.addCourse(c2);

        BookRecommendation b1 = new BookRecommendation();
        b1.getRecommendation().setTitle("Introduction to Algorithms, 3rd Edition");
        b1.setAuthor("Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein");
        b1.setIsbn("9780262033848");
        List<Course> rCourses = new ArrayList<>();
        rCourses.add(courseService.getCourse((long) 1));
        rCourses.add(courseService.getCourse((long) 2));
        b1.getRecommendation().setRelatedCourses(rCourses);
        List<Course> pCourses = new ArrayList<>();
        pCourses.add(courseService.getCourse((long) 1));
        b1.getRecommendation().setPrerequisiteCourses(pCourses);
        tagService.assignTagsToRecommendation(b1.getRecommendation(), "Algorithms,Programming");
        bookRecommendationService.addBookRecommendation(b1);

        BookRecommendation b2 = new BookRecommendation();
        b2.getRecommendation().setTitle("C Programming Language, 2nd Edition");
        b2.setAuthor("Brian W. Kernighan, Dennis M. Ritchie");
        b2.setIsbn("9780131103627");
        List<Course> courses2 = new ArrayList<>();
        courses2.add(courseService.getCourse((long) 2));
        b2.getRecommendation().setRelatedCourses(courses2);

        tagService.assignTagsToRecommendation(b2.getRecommendation(), "Programming");
        bookRecommendationService.addBookRecommendation(b2);

        VideoRecommendation v1 = new VideoRecommendation();
        v1.getRecommendation().setTitle("Java Programming Tutorial - 2 - Running a Java Program");
        v1.setAuthor("thenewboston");
        v1.setUrl("https://www.youtube.com/watch?v=5u8rFbpdvds");
        v1.getRecommendation().setDescription("Very interesting tutorial about running Java program");
        tagService.assignTagsToRecommendation(v1.getRecommendation(), "Programming,Java,Novice level");
        videoRecommendationService.addVideoRecommendation(v1);
    }

    @GetMapping(path = {"", "/", "/home", "/recommendations"})
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("recommendations", recommendationService.getRecommendations());
        model.addAttribute("bookRecommendation", new BookRecommendation());
        model.addAttribute("videoRecommendation", new VideoRecommendation());
        model.addAttribute("blogpostRecommendation", new BlogpostRecommendation());
        model.addAttribute("podcastRecommendation", new PodcastRecommendation());
        return "recommendations";
    }

    @GetMapping("/recommendations/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        return getRecommendationFromRecommendationId(model, id);
    }

    @GetMapping("/recommendations/{id}/edit")
    public String getEditOne(Model model, @PathVariable Long id) {
        return getRecommendationFromRecommendationId(model, id) + "_edit";
    }

    @PostMapping("/recommendations/search")
    public String searchRecommendationByTitle(Model model, @RequestParam(required = false) String title) {
        model.addAttribute("recommendations", recommendationService.getRecommendationsWithTitleLike(title));
        model.addAttribute("searchTerm", title);
        return "search_results";
    }

    // Used for parsing the typed recommendation item from the "super" recommendation and adding it to model
    private String getRecommendationFromRecommendationId(Model model, Long id) {
        Recommendation recommendation = recommendationService.getRecommendation(id);
        model.addAttribute("courses", courseService.getCourses());

        if (recommendation.getRecommendationType() == RecommendationType.BOOK) {
            model.addAttribute("bookRecommendation", bookRecommendationService.getBookRecommendationByRecommendationId(id));
        } else if (recommendation.getRecommendationType() == RecommendationType.VIDEO) {
            model.addAttribute("videoRecommendation", videoRecommendationService.getVideoRecommendationByRecommendationId(id));
        } else if (recommendation.getRecommendationType() == RecommendationType.PODCAST) {
            model.addAttribute("podcastRecommendation", podcastRecommendationService.getPodcastRecommendationByRecommendationId(id));
        } else if (recommendation.getRecommendationType() == RecommendationType.BLOGPOST) {
            model.addAttribute("blogpostRecommendation", blogpostRecommendationService.getBlogpostRecommendationByRecommendationId(id));
        }

        return "recommendation_" + recommendation.getRecommendationType();
    }

}
