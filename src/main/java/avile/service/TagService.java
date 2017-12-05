package avile.service;

import avile.domain.Recommendation;
import avile.domain.Tag;
import avile.repository.RecommendationRepository;
import avile.repository.TagRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private RecommendationRepository recommendationRepository;
    
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }
    
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }
    
    public Long addTag(Tag tag) {
        return tagRepository.save(tag).getId();
    }
    
    public Long updateTag(Tag tag) {
        return addTag(tag);
    }
    
    @Transactional
    public void deleteTag(Tag tag) {
        
        for (Recommendation recommendation : recommendationRepository.findAll()) {
            if (recommendation.getTagsForRecommendations().contains(tag)) {
                recommendation.getTagsForRecommendations().remove(tag);
            }
            
            recommendationRepository.save(recommendation);
        }
    }
}
