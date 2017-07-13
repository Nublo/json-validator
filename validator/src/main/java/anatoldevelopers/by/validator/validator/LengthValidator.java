package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;

public class LengthValidator extends AbstractValidator {

    public LengthValidator() {
    }

    public LengthValidator(String message, String value) {
        this.message = message;
        this.value = value;
    }

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object fieldValue) {
        if (!Arrays.asList(Type.LONG, Type.INTEGER, Type.DOUBLE, Type.STRING).contains(field.getType())) {
            ValidationError error =
                    new ValidationError(field, "Invalid type for LengthValidator validator. Should have size.");
            return Collections.singletonList(error);
        }

        if (fieldValue == null || String.valueOf(fieldValue).length() != Integer.valueOf(value)) {
            ValidationError error = new ValidationError(field, message, value);
            return Collections.singletonList(error);
        }

        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "LengthValidator{ " + super.toString() + "}";
    }

}