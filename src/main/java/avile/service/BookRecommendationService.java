package avile.service;

import avile.domain.BookRecommendation;
import avile.domain.Course;
import avile.domain.Recommendation;
import avile.repository.BookRecommendationRepository;
import avile.repository.RecommendationRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookRecommendationService {

    @Autowired
    private BookRecommendationRepository bookRecommendationRepository;

    @Autowired
    private AccountService accountService;
    

    public List<BookRecommendation> getBookRecommendations() {
        return bookRecommendationRepository.findAll();
    }

    public Long addBookRecommendation(BookRecommendation bookRecommendation) {
        if(accountService.getAuthenticatedAccount() != null) {
            bookRecommendation.getRecommendation().setCreator(accountService.getAuthenticatedAccount());
        }
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
        BookRecommendation bookRecommendation = bookRecommendationRepository.findByRecommendationId(id);
        bookRecommendation.getRecommendation().setRawTags(bookRecommendation.getRecommendation().getTagsAsString());
        return bookRecommendation;
    }

    public List<Recommendation> getRecommendationsWithAuthorLike(String author) {
        List<Recommendation> recommendations = new ArrayList<>();
        bookRecommendationRepository.findByAuthorIsLike("%"+author+"%").forEach(bookRecommendation -> recommendations.add(bookRecommendation.getRecommendation()));
        return recommendations;
    }

    public List<Recommendation> getRecommendationsWithIsbn(String isbn) {
        List<Recommendation> recommendations = new ArrayList<>();
        bookRecommendationRepository.findByIsbn(isbn).forEach(bookRecommendation -> recommendations.add(bookRecommendation.getRecommendation()));
        return recommendations;
    }
}
