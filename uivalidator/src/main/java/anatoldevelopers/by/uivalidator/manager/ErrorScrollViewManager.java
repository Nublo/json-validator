package anatoldevelopers.by.uivalidator.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.lang.reflect.Field;
import java.util.List;

import anatoldevelopers.by.validator.ValidationError;

public class ErrorScrollViewManager extends ErrorManager {

    private static final String TAG = ErrorScrollViewManager.class.toString();

    private final ScrollView scrollView;
    private int topOffset;

    public ErrorScrollViewManager(@NonNull Context context,
                                  @NonNull ScrollView scrollView) {
        super(context);
        this.scrollView = scrollView;
        topOffset = Integer.MAX_VALUE;
    }

    @Override
    public void showValidationErrors(@NonNull Object container, @NonNull List<ValidationError> errors) {
        topOffset = Integer.MAX_VALUE;
        super.showValidationErrors(container, errors);
        makeScroll(topOffset);
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

    private int calculateParentTop(View view) {
        if (view.getParent() instanceof ScrollView) {
            return view.getTop();
        } else {
            return view.getTop() + calculateParentTop((ViewGroup) view.getParent());
        }
    }

    private void makeScroll(int offset) {
        if (offset != Integer.MAX_VALUE) {
            scrollView.smoothScrollTo(0, offset);
        }
    }

}