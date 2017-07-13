package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;

public class FieldValidator extends AbstractValidator {

    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    private static final DateFormat DEFAULT_DATE_FORMAT_DATE = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault());

    private final TypeValidator typeValidator = new TypeValidator();

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object value) {
        List<ValidationError> errors = new ArrayList<>();

        if (field.getType() == Type.DATE && value != null) {
            final String format = field.getFormat() != null ? field.getFormat() : DEFAULT_DATE_FORMAT;
            try {
                final DateFormat dateFormat = field.getFormat() != null ?
                        new SimpleDateFormat(field.getFormat(), Locale.getDefault()) : DEFAULT_DATE_FORMAT_DATE;
                value = dateFormat.parse((String) value);
            } catch (ParseException e) {
                errors.add(new ValidationError(field,
                        String.format("Cannot convert to date with format %s value %s for field %s", format, value,
                                field.getName())));
            }
        }

        errors.addAll(typeValidator.validate(field, value));
        if (errors.isEmpty() && field.getValidators() != null) {
            for (Validator validator : field.getValidators()) {
                errors.addAll(validator.validate(field, value));
            }
        }
        return errors;
    }

}
