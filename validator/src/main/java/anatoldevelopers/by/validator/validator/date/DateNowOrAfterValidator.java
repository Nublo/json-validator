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

public class DateNowOrAfterValidator extends AbstractValidator {

    @NonNull
    @Override
    public List<ValidationError> validate(Field field, Object fieldValue) {

        if (Type.DATE != field.getType()) {
            ValidationError error =
                    new ValidationError(field, "Invalid type for DateNowOrAfterValidator validator. Should be Date.");
            return Collections.singletonList(error);
        }

        final Date date = (Date) fieldValue;
        Date now = new Date();

        if (fieldValue != null && !date.after(now) && !truckToDay(date).equals(truckToDay(now))) {
            ValidationError error = new ValidationError(field, message, value);
            return Collections.singletonList(error);
        }

        return Collections.emptyList();
    }

    private Date truckToDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
