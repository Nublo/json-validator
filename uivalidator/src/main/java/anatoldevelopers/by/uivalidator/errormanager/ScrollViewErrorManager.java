package anatoldevelopers.by.uivalidator.errormanager;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.lang.reflect.Field;
import java.util.List;

import anatoldevelopers.by.uivalidator.visualizer.ValidationErrorVisualizer;
import anatoldevelopers.by.validator.ValidationError;

public class ScrollViewErrorManager extends DefaultErrorManager {

    private static final String TAG = ScrollViewErrorManager.class.toString();

    private final ScrollView scrollView;
    private int topOffset;

    public ScrollViewErrorManager(@NonNull ValidationErrorVisualizer visualizer,
                                  @NonNull ScrollView scrollView) {
        super(visualizer);
        this.scrollView = scrollView;
        topOffset = Integer.MAX_VALUE;
    }

    @Override
    public void showValidationErrors(@NonNull Object container, @NonNull List<ValidationError> errors) {
        topOffset = Integer.MAX_VALUE;
        super.showValidationErrors(container, errors);
        scroll(topOffset);
    }

    @Override
    protected void showError(Object container, Field field, String message) {
        super.showError(container, field, message);
        try {
            int currentOffset = calculateParentTop((View) field.get(container));
            if (topOffset > currentOffset)
                topOffset = currentOffset;
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Can't find field in container");
            e.printStackTrace();
        }
    }

    private int calculateParentTop(@NonNull View view) {
        if (view.getParent() instanceof ScrollView) {
            return view.getTop();
        } else {
            return view.getTop() + calculateParentTop((ViewGroup) view.getParent());
        }
    }

    private void scroll(int offset) {
        if (offset != Integer.MAX_VALUE) {
            scrollView.smoothScrollTo(0, offset);
        }
    }

}