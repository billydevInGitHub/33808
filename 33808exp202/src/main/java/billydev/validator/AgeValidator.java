package billydev.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * @author Billy
 */
public class AgeValidator implements ConstraintValidator<Age, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int inputAge=value.intValue();
        return inputAge >= 6 && inputAge < 22;
    }
}
