package avile.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.*;

import avile.enums.RecommendationType;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class BlogpostRecommendation extends AbstractPersistable<Long> {

    @Size(max = 40)
    private String author;

    @NotNull
    @URL
    private String url;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private Recommendation recommendation;

    public BlogpostRecommendation() {
        recommendation = new Recommendation();
        recommendation.setDate(new Date());
        recommendation.setRecommendationType(RecommendationType.BLOGPOST);
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

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

}
