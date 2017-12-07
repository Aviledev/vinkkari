package avile.domain;

import avile.enums.RecommendationType;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class VideoRecommendation extends AbstractPersistable<Long> {
    @NotEmpty
    @URL
    private String url;

    @Size(max = 100)
    @NotEmpty
    private String author;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private Recommendation recommendation;

    public VideoRecommendation() {
        recommendation = new Recommendation();
        recommendation.setDate(new Date());
        recommendation.setRecommendationType(RecommendationType.VIDEO);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

}
