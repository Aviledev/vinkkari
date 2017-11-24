package avile.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.*;

import avile.enums.RecommendationType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class BlogpostRecommendation extends AbstractPersistable<Long> {

    @Size(max = 40)
    private String author;

    @NotNull
    private String url;

    private Date releaseDate;

    //private List<String> tags;
    //private List<String> prerequisiteCourses;
    //private List<String> relatedCourses;

    @OneToOne(cascade = CascadeType.ALL)
    private Recommendation recommendation;

    public BlogpostRecommendation() {

    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }


    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

}
