package avile.validator;


import avile.domain.Tag;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TagValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Tag.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tag tag = (Tag) target;
        if (tag.getName().length() < 2) {
            errors.rejectValue("recommendation.rawTags", "Length", "Individual tag must be longer than 1 characters long");
        }

        if (tag.getName().length() > 20) {
            errors.rejectValue("recommendation.rawTags", "Length", "Individual tag must be lower than 20 characters long");
        }
    }
}
