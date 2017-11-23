package avile.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PodcastRecommendation extends AbstractPersistable<Long>{
    // @Max(20)
    private String title;
    private String writer;
    private String type;
    private String url;
    private String description;
    //private List<String> tags;
    //private List<String> prerequisiteCourses;
    //private List<String> relatedCourses;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
