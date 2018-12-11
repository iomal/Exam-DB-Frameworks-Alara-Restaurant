package alararestaurant.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator = Validation
            .byDefaultProvider()
            .configure()
            .buildValidatorFactory()
            .getValidator();
    @Override
    public <E> boolean isValid(E entity) {
        return validator.validate(entity).size()==0;
    }
}
