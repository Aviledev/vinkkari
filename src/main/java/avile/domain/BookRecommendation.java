package avile.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import avile.enums.RecommendationType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class BookRecommendation extends AbstractPersistable<Long>{
    // @Max(20)
    private String title;
    private String author;
    private String isbn;
    //private List<String> tags;
    //private List<String> prerequisiteCourses;
    //private List<String> relatedCourses;

    @OneToOne(cascade= CascadeType.ALL)
    private Recommendation recommendation;

    public BookRecommendation() {
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        if(this.recommendation == null) {
            return "N/A";
        }
        return this.recommendation.getRecommendationType().toString();
    }

    public void setType(RecommendationType type) {
        if(this.recommendation == null) {
            this.recommendation = new Recommendation();
        }
        this.recommendation.setRecommendationType(type);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDate() {
        if(this.recommendation == null) {
            return null;
        }
        return this.recommendation.getDate();
    }

    public void setDate(Date date) {
        if(this.recommendation == null) {
            this.recommendation = new Recommendation();
        }
        this.recommendation.setDate(date);
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }
}
