package avile.service;

import avile.domain.BookRecommendation;
import avile.domain.Course;
import avile.repository.BookRecommendationRepository;
import avile.repository.RecommendationRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRecommendationService {

    @Autowired
    private BookRecommendationRepository bookRecommendationRepository;
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private RecommendationRepository recommendationRepository;

    public List<BookRecommendation> getBookRecommendations() {
        return bookRecommendationRepository.findAll();
    }

    public Long addBookRecommendation(BookRecommendation bookRecommendation) {
        return bookRecommendationRepository.save(bookRecommendation).getId();
    }

    public Long updateBookRecommendation(BookRecommendation bookRecommendation) {
        return this.addBookRecommendation(bookRecommendation);
    }

    public void deleteBookRecommendation(BookRecommendation bookRecommendation) {
        this.deleteBookRecommendationById(bookRecommendation.getId());
    }

    public void deleteBookRecommendationById(Long id) {
        this.bookRecommendationRepository.delete(id);
    }

    public BookRecommendation getBookRecommendation(Long id) {
        return bookRecommendationRepository.findOne(id);
    }

    public BookRecommendation getBookRecommendationByRecommendationId(Long id) {
        return bookRecommendationRepository.findByRecommendationId(id);
    }
}
