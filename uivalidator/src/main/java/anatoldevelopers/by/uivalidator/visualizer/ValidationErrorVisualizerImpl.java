package anatoldevelopers.by.uivalidator.visualizer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ValidationErrorVisualizerImpl implements ValidationErrorVisualizer {

    private static final String TAG = ValidationErrorVisualizerImpl.class.toString();

    private final Context context;

    public ValidationErrorVisualizerImpl(@NonNull final Context context) {
        this.context = context;
    }

    @Override
    public void showLocalValidationError(@NonNull Object container,
                                         @NonNull Field field,
                                         @Nullable String message) {
        String errorMessage = getErrorMessage(message);
        if (errorMessage == null)
            return;
        try {
            field.setAccessible(true);
            Method m = field.getType().getMethod("setError", CharSequence.class);
            m.invoke(field.get(container), errorMessage);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "No such method setError with one string parameter on field");
        } catch (InvocationTargetException e) {
            Log.e(TAG, "Can not invoke method setError with one string parameter on field");
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Illegal access exception while invoking method setError with one string parameter on field");
        } catch (NullPointerException e) {
            Log.e(TAG, "No such field on form " + field.getName());
        }
    }

    /**
     * Find resource for <code>errorId</code> in strings resources file
     * @param errorId - resource name
     * @return - resource is found, otherwise <code>errorId</code>
     */
    @Nullable
    private String getErrorMessage(@Nullable String errorId) {
        try {
            int resID = context.getResources().getIdentifier(errorId, "string", context.getPackageName());
            return context.getString(resID);
        } catch (Exception e) {
            Log.e(TAG, String.format("Can't find resource for `%s`", errorId));
            e.printStackTrace();
            return errorId;
        }
    }

}