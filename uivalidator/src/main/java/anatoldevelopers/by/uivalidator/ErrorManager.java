package anatoldevelopers.by.uivalidator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import anatoldevelopers.by.uivalidator.visualizer.ValidationErrorVisualizer;
import anatoldevelopers.by.validator.ValidationError;

public class ErrorManager {

    private static final String TAG = ErrorManager.class.toString();

    private final ValidationErrorVisualizer visualizer;

    public ErrorManager(ValidationErrorVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void showValidationErrors(@NonNull Object container,
                                     @NonNull List<ValidationError> errors) {
        HashSet<String> shownErrors = new HashSet<>();
        List<Field> fields = getAnnotatedFields(container.getClass());
        for (ValidationError error : errors) {
            String fieldName = error.getSubject().getName();
            if (shownErrors.contains(fieldName))
                continue;
            Field field = findFieldByName(fields, fieldName);
            if (field != null) {
                shownErrors.add(fieldName);
                showError(container, field, error.getMessage());
            } else {
                Log.e(TAG, String.format("Not found field with name %s", fieldName));
            }
        }
    }

    protected void showError(Object container, Field field, String message) {
        visualizer.showLocalValidationError(container, field, message);
    }

    @Nullable
    protected Field findFieldByName(@NonNull List<Field> fields,
                                    @NonNull String fieldName) {
        for (Field field : fields) {
            if (field.getAnnotation(anatoldevelopers.by.uivalidator.Field.class)
                    .value().equalsIgnoreCase(fieldName))
                return field;
        }
        return null;
    }

    @NonNull
    protected List<Field> getAnnotatedFields(@NonNull Class<?> clazz) {
        List<Field> result = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields())
            if (field.isAnnotationPresent(anatoldevelopers.by.uivalidator.Field.class))
                result.add(field);
        return result;
    }

}