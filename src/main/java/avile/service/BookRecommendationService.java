package avile.service;

import avile.domain.BookRecommendation;
import avile.repository.BookRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRecommendationService {
    @Autowired
    private BookRecommendationRepository bookRepo;

    public List<BookRecommendation> getBookRecommendationsWithTitleLike(String title) {
        return bookRepo.findBookRecommendationsByTitleIsLike(title);
    }

    public List<BookRecommendation> getBookRecommendations() {
        return bookRepo.findAll();
    }

    public Long addBookRecommendation(BookRecommendation book) {
        return bookRepo.save(book).getId();
    }

    public Long updateBookRecommendation(BookRecommendation book) {
        return this.addBookRecommendation(book);
    }

    public void deleteBookRecommendation(BookRecommendation book) {
        this.deleteBookRecommendationById(book.getId());
    }

    public void deleteBookRecommendationById(Long bookId) {
        bookRepo.delete(bookId);
    }
}
