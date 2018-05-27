package anatoldevelopers.by.uivalidator.visualizer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Field;

public interface ValidationErrorVisualizer {

    /**
     * Invoke setError(message) on field inside container
     * @param container - Container where we have fields that should be validated
     * @param field - Field object in container on which should method be called
     * @param message - String that should be shown as error
     */
    void showLocalValidationError(@NonNull Object container,
                                  @NonNull Field field,
                                  @Nullable String message);

}
