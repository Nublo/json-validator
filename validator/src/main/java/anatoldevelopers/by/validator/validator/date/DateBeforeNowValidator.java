package anatoldevelopers.by.validator.validator.date;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;
import anatoldevelopers.by.validator.validator.AbstractValidator;

public class DateBeforeNowValidator extends AbstractValidator {

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object fieldValue) {

        if (Type.DATE != field.getType()) {
            ValidationError error =
                    new ValidationError(field, "Invalid type for DateBeforeNowValidator validator. Should be Date.");
            return Collections.singletonList(error);
        }

        final Date date = (Date) fieldValue;
        if (fieldValue != null && !date.before(new Date())) {
            ValidationError error = new ValidationError(field, message, value);
            return Collections.singletonList(error);
        }

        return Collections.emptyList();
    }
}
