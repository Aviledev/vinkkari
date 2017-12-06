package avile.domain;

import avile.enums.RecommendationType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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


    @ManyToMany
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTagsAsString() {
        if (tags == null || tags.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tags.get(0).getName());
        for (Tag tag : tags) {
            sb.append(",");
            sb.append(tag.getName());
        }
        return sb.toString();
    }

    public List<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public void setPrerequisiteCourses(List<Course> prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }

    public List<Course> getRelatedCourses() {
        return relatedCourses;
    }

    public void setRelatedCourses(List<Course> relatedCourses) {
        this.relatedCourses = relatedCourses;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }
}
