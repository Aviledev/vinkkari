package avile.domain;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import avile.enums.RecommendationType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PodcastRecommendation extends AbstractPersistable<Long> {

    @Max(40)
    private String author;

    @NotNull
    private String url;

    //private List<String> tags;
    //private List<String> prerequisiteCourses;
    //private List<String> relatedCourses;

    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Recommendation recommendation;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public PodcastRecommendation() {
    }

    public String getTitle() {
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
        if(this.recommendation == null) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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




}
