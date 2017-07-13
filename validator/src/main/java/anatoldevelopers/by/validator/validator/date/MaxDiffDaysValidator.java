package anatoldevelopers.by.validator.validator.date;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;
import anatoldevelopers.by.validator.validator.AbstractValidator;


public class MaxDiffDaysValidator extends AbstractValidator {

    private final Calendar calendar = Calendar.getInstance();

    public MaxDiffDaysValidator() {
    }

    public MaxDiffDaysValidator(String message, String value) {
        this.message = message;
        this.value = value;
    }


    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object fieldValue) {

        if (Type.DATE != field.getType()) {
            ValidationError error =
                    new ValidationError(field, "Invalid type for MaxDiffDaysValidator validator. Should be Date.");
            return Collections.singletonList(error);
        }

        final Date date = (Date) fieldValue;
        if (date != null) {
            calendar.setTime(new Date());
            final Integer diffDays = Integer.valueOf(value);
            calendar.add(Calendar.DATE, diffDays);

            final Date border = calendar.getTime();

            if ((diffDays >= 0 && border.before(date)) || (diffDays < 0 && border.after(date))) {
                ValidationError error = new ValidationError(field, message, value);
                return Collections.singletonList(error);
            }
        }

        return Collections.emptyList();
    }
}
