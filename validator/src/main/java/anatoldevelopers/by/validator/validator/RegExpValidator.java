package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;

public class RegExpValidator extends AbstractValidator {

    private Pattern pattern;

    public RegExpValidator() {
    }

    public RegExpValidator(String message, String value) {
        this.message = message;
        this.value = value;
    }

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object fieldValue) {
        if (field.getType() != Type.STRING) {
            ValidationError error = new ValidationError(field, "Invalid type for RegExp validator. Should be string. Now it is " + field.getType());
            return Collections.singletonList(error);
        }

        if (fieldValue != null && !getPattern().matcher((String) fieldValue).matches()) {
            ValidationError error = new ValidationError(field, message, fieldValue, getPattern());
            return Collections.singletonList(error);
        }

        return Collections.emptyList();
    }

    private Pattern getPattern() {
        if (pattern == null) {
            pattern = Pattern.compile(value);
        }
        return pattern;
    }

    @Override
    public String toString() {
        return "RegExpValidator{ " + super.toString() + "}";
    }
}
