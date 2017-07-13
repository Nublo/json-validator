package anatoldevelopers.by.validator;

import android.support.annotation.Nullable;

public interface FieldRepository {

    @Nullable
    Field find(String name);

}
