package avile.service;

import avile.domain.Recommendation;
import avile.domain.Tag;
import avile.repository.RecommendationRepository;
import avile.repository.TagRepository;
import java.util.ArrayList;
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
            if (recommendation.getTags().contains(tag)) {
                recommendation.getTags().remove(tag);
            }

            recommendationRepository.save(recommendation);
        }
    }

    public List<Tag> parseTagsFromString(String tagString) {
        List<Tag> list = new ArrayList<>();
        String[] splitted = tagString.split(",");
        for (int i = 0; i < splitted.length; i++) {
            Tag t = new Tag();
            t.setName(splitted[i]);
            list.add(t);
        }
        return list;
    }

    @Transactional
    public Tag saveIfNotExist(Tag tag) {
        Tag t = tagRepository.findByName(tag.getName());
        if (t != null) {
            return t;
        }
        tagRepository.save(tag);
        return tag;
        
        /* try this if not working
   Long newId = tagRepository.save(tag);
   tag.setId(newId);
   return tag;
         */
    }
    
    @Transactional
    public void assignTagsToRecommendation(Recommendation recommendation, String tags) {
        List<Tag> splitted = parseTagsFromString(tags);
        List<Tag> savedTags = new ArrayList<>();
        for (Tag t : splitted) {
            savedTags.add(saveIfNotExist(t));
        }
        recommendation.setTags(savedTags);
    }
}
