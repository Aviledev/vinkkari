package avile.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Course extends AbstractPersistable<Long> {

    @NotNull
    @Size(max = 100)
    @NotEmpty
    private String name;

    private String code;

    private String description;

    @ManyToMany(mappedBy = "relatedCourses")
    private List<Recommendation> recommendationsRelated;

    @ManyToMany(mappedBy = "prerequisiteCourses")
    private List<Recommendation> recommendationsPrerequisite;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
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

    public List<Recommendation> getRecommendationsRelated() {
        return recommendationsRelated;
    }

    public void setRecommendationsRelated(List<Recommendation> recommendationsRelated) {
        this.recommendationsRelated = recommendationsRelated;
    }

    public List<Recommendation> getRecommendationsPrerequisite() {
        return recommendationsPrerequisite;
    }

    public void setRecommendationsPrerequisite(List<Recommendation> recommendationsPrerequisite) {
        this.recommendationsPrerequisite = recommendationsPrerequisite;
    }
}
