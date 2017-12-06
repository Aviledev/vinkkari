package avile.domain;

import avile.enums.RecommendationType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Recommendation extends AbstractPersistable<Long> {

    @NotNull
    @Size(max = 100)
    @NotEmpty
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Size(max = 600)
    private String description;

    private RecommendationType recommendationType;

    @ManyToMany
    private List<Course> relatedCourses;
    
    @ManyToMany
    private List<Course> prerequisiteCourses;

    public void setPrerequisiteCourses(List<Course> prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }

    public List<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public void setRelatedCourses(List<Course> relatedCourses) {
        this.relatedCourses = relatedCourses;
    }

    public List<Course> getRelatedCourses() {
        return relatedCourses;
    }
    
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
