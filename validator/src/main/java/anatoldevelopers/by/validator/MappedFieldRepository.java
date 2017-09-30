package anatoldevelopers.by.validator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MappedFieldRepository implements FieldRepository {

    private final Map<String, Field> fieldMap;

    public MappedFieldRepository(@NonNull Map<String, Field> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public MappedFieldRepository() {
        fieldMap = new HashMap<>();
    }

    @Nullable
    @Override
    public Field find(@Nullable String name) {
        return fieldMap.get(name);
    }

    public void merge(@NonNull Map<String, Field> toMerge) {
        fieldMap.putAll(toMerge);
    }

}