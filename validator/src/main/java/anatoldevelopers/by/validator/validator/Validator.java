package anatoldevelopers.by.validator.validator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.ValidationError;

public interface Validator {

    @Nullable
    String getMessage();

    @Nullable
    String getValue();

    @NonNull
    List<ValidationError> validate(Field field, Object value);

}