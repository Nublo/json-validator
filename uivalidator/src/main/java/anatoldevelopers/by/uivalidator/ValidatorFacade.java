package anatoldevelopers.by.uivalidator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import anatoldevelopers.by.uivalidator.errormanager.ErrorManager;
import anatoldevelopers.by.uivalidator.errormanager.DefaultErrorManager;
import anatoldevelopers.by.uivalidator.visualizer.ValidationErrorVisualizerImpl;
import anatoldevelopers.by.validator.FieldsSchemaValidator;
import anatoldevelopers.by.validator.ValidationError;
import anatoldevelopers.by.validator.gson.FieldsSchemaValidatorFactory;

public class ValidatorFacade {

    private final FieldsSchemaValidator validator;
    private final ErrorManager errorManager;

    public ValidatorFacade(String schema, ErrorManager manager) {
        validator = FieldsSchemaValidatorFactory.create(schema);
        errorManager = manager;
    }

    public ValidatorFacade(Context context, int schemaResourceId, ErrorManager manager) {
        this(parseSchemaFromResource(context, schemaResourceId), manager);
    }

    public ValidatorFacade(Context context, int schemaResourceId) {
        this(context, parseSchemaFromResource(context, schemaResourceId));
    }

    public ValidatorFacade(Context context, String schema) {
        this(schema, new DefaultErrorManager(new ValidationErrorVisualizerImpl(context)));
    }

    public List<ValidationError> validate(Map<String, Object> params) {
        return validator.validate(params);
    }

    public void showValidationErrors(Object container, List<ValidationError> errors) {
        errorManager.showValidationErrors(container, errors);
    }

    private static String parseSchemaFromResource(Context context, int resourceID) {
        InputStream inputStream = context.getResources().openRawResource(resourceID);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while ((line = buffreader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

}