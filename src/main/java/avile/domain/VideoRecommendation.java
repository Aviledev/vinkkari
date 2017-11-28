package avile.domain;

import avile.enums.RecommendationType;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class VideoRecommendation extends AbstractPersistable<Long>
{
    @NotNull
    @Size(min = 1, max = 255)
    private String url;

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
}
