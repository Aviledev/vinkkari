package avile;

import avile.domain.*;
import avile.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Main implements ApplicationRunner{

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
    AccountService accountService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account admin = new Account();
        admin.setEmail("admin@admin");
        admin.setPassword("123456");
        admin.setFirstname("Admin");
        admin.setLastname("Admin");
        accountService.save(admin);

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

        accountService.toggleChecked(accountService.getAccount(admin.getId()), recommendationService.getRecommendation(p1.getRecommendation().getId()));
        accountService.toggleChecked(accountService.getAccount(admin.getId()), recommendationService.getRecommendation(v1.getRecommendation().getId()));
    }
}