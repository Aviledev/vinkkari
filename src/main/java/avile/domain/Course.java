package avile.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Course extends AbstractPersistable<Long> {

    @Size(max = 100)
    private String name;

    @ManyToMany
    private List<Recommendation> recommendations;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
