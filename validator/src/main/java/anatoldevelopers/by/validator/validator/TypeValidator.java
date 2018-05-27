package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.ValidationError;

import static java.util.Collections.emptyList;

public class TypeValidator extends AbstractValidator {

    public TypeValidator() {
    }

    public TypeValidator(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object value) {
        if (value != null) {
            final Class<?> valueClass = value.getClass();

            if (field.getType().typeClass() == Long.class) {
                if (valueClass == Integer.class || valueClass == Long.class) {
                    return emptyList();
                }
            }
            if (field.getType().typeClass() == Double.class) {
                if (valueClass == Double.class || valueClass == Long.class || valueClass == Integer.class) {
                    return emptyList();
                }
            }

            if (!valueClass.isAssignableFrom(field.getType().typeClass())) {
                return Collections.singletonList(new ValidationError(field,
                        "Cannot convert to type " + field.getType() + " value " + value + " for field " + field
                                .getName()));
            }
        }
        return emptyList();
    }

    @Override
    public String toString() {
        return "TypeValidator{ " + super.toString() + "}";
    }
}
