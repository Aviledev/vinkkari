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

import java.util.*;

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

        Course c3 = new Course();
        c3.setName("Johdatus tietojenkäsittelytieteeseen");
        c3.setCode("TKT10001");
        courseService.addCourse(c3);

        Course c4 = new Course();
        c4.setName("Ohjelmoinnin perusteet");
        c4.setCode("TKT10002");
        courseService.addCourse(c4);

        Course c5 = new Course();
        c5.setName("Ohjelmoinnin jatkokurssi");
        c5.setCode("TKT10003");
        courseService.addCourse(c5);

        Course c6 = new Course();
        c6.setName("Tietokantojen perusteet");
        c6.setCode("TKT10004");
        courseService.addCourse(c6);

        Course c7 = new Course();
        c7.setName("Tietokoneen toiminta");
        c7.setCode("TKT10005");
        courseService.addCourse(c7);

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
        tagService.assignTagsToRecommendation(b1.getRecommendation(), "Algorithms,Programming", null);
        bookRecommendationService.addBookRecommendation(b1);

        BookRecommendation b2 = new BookRecommendation();
        b2.getRecommendation().setTitle("C Programming Language, 2nd Edition");
        b2.setAuthor("Brian W. Kernighan, Dennis M. Ritchie");
        b2.setIsbn("9780131103627");
        List<Course> courses2 = new ArrayList<>();
        courses2.add(courseService.getCourse((long) 2));
        b2.getRecommendation().setRelatedCourses(courses2);

        tagService.assignTagsToRecommendation(b2.getRecommendation(), "Programming", null);
        bookRecommendationService.addBookRecommendation(b2);

        VideoRecommendation v1 = new VideoRecommendation();
        v1.getRecommendation().setTitle("Java Programming Tutorial - 2 - Running a Java Program");
        v1.setAuthor("thenewboston");
        v1.setUrl("https://www.youtube.com/watch?v=5u8rFbpdvds");
        v1.getRecommendation().setDescription("Very interesting tutorial about running Java program");
        tagService.assignTagsToRecommendation(v1.getRecommendation(), "Programming,Java,Novice level", null);
        videoRecommendationService.addVideoRecommendation(v1);

        BlogpostRecommendation bp1 = new BlogpostRecommendation();
        bp1.getRecommendation().setTitle("Welcoming Android 8.1 Oreo and Android Oreo (Go edition)");
        bp1.setName("Android Developers Blog");
        bp1.setUrl("https://android-developers.googleblog.com/2017/12/welcoming-android-81-oreo-and-android.html");
        bp1.setAuthor("Dave Burke");
        bp1.getRecommendation().setDescription("Erittäin mukava postaus uusista jutuista.");
        bp1.getRecommendation().setRelatedCourses(Arrays.asList(c7));
        bp1.getRecommendation().setPrerequisiteCourses(Arrays.asList(c3, c4, c5));
        tagService.assignTagsToRecommendation(bp1.getRecommendation(), "Java,Android,Oreo,Go", null);
        blogpostRecommendationService.addBlogpostRecommendation(bp1);

        PodcastRecommendation p1 = new PodcastRecommendation();
        p1.getRecommendation().setTitle("Linux Bracket Elimination | Ask Noah 38");
        p1.setName("Jupiter Broadcasting ");
        p1.setUrl("http://www.jupiterbroadcasting.com/120427/linux-bracket-elimination-ask-noah-38/");
        p1.getRecommendation().setDescription("Tässä aika monimutkaista settiä");
        p1.getRecommendation().setRelatedCourses(Arrays.asList(c2, c7));
        p1.getRecommendation().setPrerequisiteCourses(Arrays.asList(c3, c4, c5, c7));
        tagService.assignTagsToRecommendation(p1.getRecommendation(), "Linux,Tech,OS,Hack", null);
        podcastRecommendationService.addPodcastRecommendation(p1);
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
        Set<Recommendation> results = new HashSet<>();
        results.addAll(recommendationService.getRecommendationsWithTitleLike(title));
        results.addAll(tagService.getByName(title).getRecommendations());
        model.addAttribute("recommendations", results);
        model.addAttribute("searchTerm", title);
        return "search_results";
    }

    @GetMapping("/recommendations/search")
    public String searchRecommendationByTitleGet(Model model, @RequestParam(required = false) String title) {
        return searchRecommendationByTitle(model, title);
    }

    private String getRecommendationFromRecommendationId(Model model, Long id) {
        Recommendation recommendation = recommendationService.getRecommendation(id);
        model.addAttribute("courses", courseService.getCourses());

        if (recommendation.getRecommendationType() == RecommendationType.BOOK) {
            BookRecommendation bookRecommendation = bookRecommendationService.getBookRecommendationByRecommendationId(id);
            bookRecommendation.getRecommendation().setRawTags(bookRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("bookRecommendation", bookRecommendation);
        } else if (recommendation.getRecommendationType() == RecommendationType.VIDEO) {
            VideoRecommendation videoRecommendation = videoRecommendationService.getVideoRecommendationByRecommendationId(id);
            videoRecommendation.getRecommendation().setRawTags(videoRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("videoRecommendation", videoRecommendation);
        } else if (recommendation.getRecommendationType() == RecommendationType.PODCAST) {
            PodcastRecommendation podcastRecommendation = podcastRecommendationService.getPodcastRecommendationByRecommendationId(id);
            podcastRecommendation.getRecommendation().setRawTags(podcastRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("podcastRecommendation", podcastRecommendation);
        } else if (recommendation.getRecommendationType() == RecommendationType.BLOGPOST) {
            BlogpostRecommendation blogpostRecommendation = blogpostRecommendationService.getBlogpostRecommendationByRecommendationId(id);
            blogpostRecommendation.getRecommendation().setRawTags(blogpostRecommendation.getRecommendation().getTagsAsString());
            model.addAttribute("blogpostRecommendation", blogpostRecommendation);
        }

        return "recommendation_" + recommendation.getRecommendationType();
    }

}
