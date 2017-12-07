package avile.service;

import avile.domain.Recommendation;
import avile.domain.Tag;
import avile.repository.RecommendationRepository;
import avile.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import avile.validator.TagListValidator;
import avile.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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

    public Tag getByName(String name) {
        return tagRepository.findByName(name);
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
    }

    @Transactional
    public void assignTagsToRecommendation(Recommendation recommendation, String tags, BindingResult bs) {
        if(tags!=null && tags.length()>0) {
            List<Tag> splitted = parseTagsFromString(tags);
            validateTags(splitted, bs);
            List<Tag> savedTags = new ArrayList<>();
            for (Tag t : splitted) {
                savedTags.add(saveIfNotExist(t));
            }
            recommendation.setTags(savedTags);
        }
    }

    private void validateTags(List<Tag> tags, BindingResult bs) {
        TagListValidator tagListValidator = new TagListValidator();
        tagListValidator.validate(tags, bs);

        TagValidator tagValidator = new TagValidator();
        for (Tag tag : tags) {
            tagValidator.validate(tag, bs);
        }
    }

}
