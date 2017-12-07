package avile.validator;


import avile.domain.Tag;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class TagListValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return List.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        List<Tag> tags = (List<Tag>) target;
        if (tags.size() > 10) {
            errors.rejectValue("recommendation.rawTags", "Length", "You can not have more than 10 tags");
        }

    }
}
