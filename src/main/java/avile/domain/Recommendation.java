package avile.domain;

import avile.enums.RecommendationType;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Recommendation extends AbstractPersistable<Long> {
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private RecommendationType recommendationType;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RecommendationType getRecommendationType() {
        return recommendationType;
    }

    public void setRecommendationType(RecommendationType recommendationType) {
        this.recommendationType = recommendationType;
    }
}
