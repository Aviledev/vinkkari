package avile.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class BookRecommendation extends AbstractPersistable<Long>{
    @NotNull
    @Size(min=2, max=50)
    private String title;
    
    @Size(max=40)
    private String author;
    
    @NotNull
    private String type;
    
    // ISBN validaatio erikseen
    @NotNull
    private String isbn;
    
    @Size(max=300)
    private String description;
    //private List<String> tags;
    //private List<String> prerequisiteCourses;
    //private List<String> relatedCourses;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
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
