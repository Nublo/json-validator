package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.ValidationError;

public class EnumValidator extends AbstractValidator {

    private Object[] oneOf;

    public EnumValidator() {
    }

    public EnumValidator(String message, Object[] oneOf) {
        this.message = message;
        this.oneOf = oneOf;
    }

    public Object[] getOneOf() {
        return oneOf;
    }

    public void setOneOf(Object[] oneOf) {
        this.oneOf = oneOf;
    }

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object value) {
        Arrays.sort(oneOf);
        if (value != null && Arrays.binarySearch(oneOf, value) < 0) {
            return Collections.singletonList(new ValidationError(field, message, value));
        }
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "EnumValidator{" +
                super.toString() +
                "oneOf=" + Arrays.toString(oneOf) +
                '}';
    }

}