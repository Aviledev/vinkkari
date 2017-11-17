package avile.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class BookRecommendation extends AbstractPersistable<Long>{
    // @Max(20)
    private String title;
    private String author;
    private String type;
    private String isbn;
    //private List<String> tags;
    //private List<String> prerequisiteCourses;
    //private List<String> relatedCourses;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

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
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
