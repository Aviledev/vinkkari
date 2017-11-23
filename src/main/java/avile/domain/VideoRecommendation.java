package avile.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
public class VideoRecommendation extends AbstractPersistable<Long>
{
    private String url;
}
