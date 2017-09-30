package anatoldevelopers.by.validator;

import android.support.annotation.Nullable;

interface FieldRepository {

    @Nullable
    Field find(@Nullable String name);

}