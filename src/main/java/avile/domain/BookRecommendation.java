package avile.domain;

import avile.enums.RecommendationType;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class BookRecommendation extends AbstractPersistable<Long> {


    @Size(max = 40)
    private String author;

    private String isbn;

    //private List<String> tags;
    //private List<String> prerequisiteCourses;
    //private List<String> relatedCourses;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Recommendation recommendation;

    public BookRecommendation() {

    }

    public String getDescription() {
        if (this.recommendation == null) {
            return "N/A";
        }
        return this.recommendation.getDescription();
    }

    public void setDescription(String description) {
        if (this.recommendation == null) {
            this.recommendation = new Recommendation();
        }
        this.recommendation.setDescription(description);
    }

    public String getTitle() {
        if (this.recommendation == null) {
            return "N/A";
        }
        return this.recommendation.getTitle();
    }

    public void setTitle(String title) {
        if (this.recommendation == null) {
            this.recommendation = new Recommendation();
        }
        this.recommendation.setTitle(title);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        if (this.recommendation == null) {
            return "N/A";
        }
        return this.recommendation.getRecommendationType().toString();
    }

    public void setType(RecommendationType type) {
        if (this.recommendation == null) {
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
        if (this.recommendation == null) {
            return null;
        }
        return this.recommendation.getDate();
    }

    public void setDate(Date date) {
        if (this.recommendation == null) {
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
