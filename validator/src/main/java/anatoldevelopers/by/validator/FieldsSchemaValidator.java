package anatoldevelopers.by.validator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import anatoldevelopers.by.validator.validator.FieldValidator;

public class FieldsSchemaValidator {

    private final FieldRepository fieldRepository;
    private final FieldValidator fieldValidator = new FieldValidator();

    public FieldsSchemaValidator(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @NonNull
    public List<ValidationError> validate(@Nullable Map<String, Object> map) {
        List<ValidationError> result = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                result.addAll(processField(entry.getKey(), entry.getValue()));
            }
        }
        return result;
    }

    @NonNull
    private List<ValidationError> processField(String fieldName, Object value) {
        final Field field = fieldRepository.find(fieldName);
        if (field == null) {
            return Collections.emptyList();
        }
        try {
            return fieldValidator.validate(field, value);
        } catch (Throwable th) {
            final String message = String.format("System error during processing field %s", fieldName);
            System.out.println(message);
            th.printStackTrace();
            return Collections.singletonList(new ValidationError(field, message));
        }
    }

}
