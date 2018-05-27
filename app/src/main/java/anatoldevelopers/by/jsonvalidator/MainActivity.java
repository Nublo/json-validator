package anatoldevelopers.by.jsonvalidator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import anatoldevelopers.by.uivalidator.ErrorManager;
import anatoldevelopers.by.uivalidator.Field;
import anatoldevelopers.by.uivalidator.visualizer.ValidationErrorVisualizerImpl;
import anatoldevelopers.by.validator.FieldsSchemaValidator;
import anatoldevelopers.by.validator.ValidationError;
import anatoldevelopers.by.validator.gson.FieldsSchemaValidatorFactory;

public class MainActivity extends AppCompatActivity {

    @Field("userName")
    private TextInputLayout userNameInput;
    @Field("password")
    private TextInputLayout passwordInput;

    private FieldsSchemaValidator validator;
    private ErrorManager errorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        validator = FieldsSchemaValidatorFactory.create(getJsonSchemaStringFromResources(R.raw.validation));
        errorManager = new ErrorManager(new ValidationErrorVisualizerImpl(this));
    }

    private void initViews() {
        userNameInput = findViewById(R.id.userNameInput);
        userNameInput.getEditText().addTextChangedListener(new CleanErrorWatcher(userNameInput));
        passwordInput = findViewById(R.id.passwordInput);
        passwordInput.getEditText().addTextChangedListener(new CleanErrorWatcher(passwordInput));
        findViewById(R.id.validate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });
    }

    private void validateInput() {
        List<ValidationError> errors = validator.validate(collectParams());
        if (errors.isEmpty()) {
            Toast.makeText(this, "Everything is fine", Toast.LENGTH_LONG).show();
        } else {
            errorManager.showValidationErrors(this, errors);
        }
    }

    private Map<String, Object> collectParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userNameInput.getEditText().getText().toString());
        params.put("password", passwordInput.getEditText().getText().toString());
        return params;
    }

    private String getJsonSchemaStringFromResources(int resourceID) {
        InputStream inputStream = getResources().openRawResource(resourceID);
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

    static class CleanErrorWatcher implements TextWatcher {

        private final TextInputLayout layout;

        CleanErrorWatcher(@NonNull TextInputLayout layout) {
            this.layout = layout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            layout.setError(null);
            layout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}