package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;

public class MaxValidator extends AbstractValidator {

    public MaxValidator() {
    }

    public MaxValidator(String message, String value) {
        this.message = message;
        this.value = value;
    }

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object fieldValue) {
        if (!Arrays.asList(Type.LONG, Type.INTEGER, Type.DOUBLE).contains(field.getType())) {
            ValidationError error = new ValidationError(field, "Invalid type for MaxValidator validator. Should be number. Now it is " + field.getType());
            return Collections.singletonList(error);
        }

        if (fieldValue == null || maxNumber(fieldValue)) {
            ValidationError error = new ValidationError(field, message, fieldValue, value);
            return Collections.singletonList(error);
        }

        return Collections.emptyList();
    }

    private boolean maxNumber(Object fieldValue) {
        return compareTo((Number) fieldValue, Double.parseDouble(value)) > 0;
    }

    private int compareTo(Number n1, Number n2) {
        BigDecimal b1 = new BigDecimal(n1.doubleValue());
        BigDecimal b2 = new BigDecimal(n2.doubleValue());
        return b1.compareTo(b2);
    }

    @Override
    public String toString() {
        return "MaxValidator{ " + super.toString() + "}";
    }
}
