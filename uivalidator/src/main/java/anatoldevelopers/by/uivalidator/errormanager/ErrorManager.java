package anatoldevelopers.by.uivalidator.errormanager;

import android.support.annotation.NonNull;
import java.util.List;
import anatoldevelopers.by.validator.ValidationError;

public interface ErrorManager {

    void showValidationErrors(@NonNull Object container,
                              @NonNull List<ValidationError> errors);

}