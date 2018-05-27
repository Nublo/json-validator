package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;

public class RequiredValidator extends AbstractValidator {

    public RequiredValidator() {
    }

    public RequiredValidator(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object value) {
        if (isNull(value) || isEmpty(field, value) || isFalse(field, value)) {
            return Collections.singletonList(new ValidationError(field, message, Collections.EMPTY_LIST));
        }
        return Collections.emptyList();
    }

    private boolean isFalse(Field field, Object value) {
        return field.getType() == Type.BOOLEAN && !((Boolean) value);
    }

    private boolean isEmpty(Field field, Object value) {
        return field.getType() == Type.STRING && ((String) value).isEmpty();
    }

    private boolean isNull(Object value) {
        return value == null;
    }

    @Override
    public String toString() {
        return "RequiredValidator{ " + super.toString() + "}";
    }

}
