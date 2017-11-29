package avile.domain;

import avile.enums.RecommendationType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Recommendation extends AbstractPersistable<Long> {

    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Size(max = 600)
    private String description;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return super.getId();
    }
    public void setId(Long id) {
        super.setId(id);
    }
}
