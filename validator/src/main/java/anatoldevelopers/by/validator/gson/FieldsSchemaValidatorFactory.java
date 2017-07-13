package anatoldevelopers.by.validator.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.FieldsSchemaValidator;
import anatoldevelopers.by.validator.MappedFieldRepository;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.validator.Validator;
import anatoldevelopers.by.validator.validator.Validators;

public class FieldsSchemaValidatorFactory {

    private static final Pattern INITIAL_SLASH = Pattern.compile("^/+");

    public static FieldsSchemaValidator createFromResource(String jsonSchemaResource) {
        URL resourceAsStream = FieldsSchemaValidator.class.getClassLoader().getResource(jsonSchemaResource);
        if (resourceAsStream == null) {
            final ClassLoader classLoader = getClassLoader();
            final String s = INITIAL_SLASH.matcher(jsonSchemaResource).replaceFirst("");
            resourceAsStream = classLoader.getResource(s);
        }
        try {
            if (resourceAsStream != null) {
                return create(new Scanner(resourceAsStream.openStream(), "UTF-8").useDelimiter("\\A").next());
            } else {
                throw new IllegalArgumentException(String.format("resource not found %s", jsonSchemaResource));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("resource not found %s", jsonSchemaResource), e);
        }
    }

    private static ClassLoader getClassLoader() {
        final ClassLoader classLoader;
        if (Thread.currentThread().getContextClassLoader() != null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        } else {
            classLoader = FieldsSchemaValidator.class.getClassLoader();
        }
        return classLoader;
    }


    public static FieldsSchemaValidator create(String... jsonSchemas) {
        MappedFieldRepository fieldRepository = new MappedFieldRepository();
        for (String schema : jsonSchemas) {
            fieldRepository.merge(getStringFieldMap(schema));
        }
        return new FieldsSchemaValidator(fieldRepository);
    }

    public static FieldsSchemaValidator create(String jsonSchema) {
        return new FieldsSchemaValidator(fieldDictionary(jsonSchema));
    }


    private static MappedFieldRepository fieldDictionary(String jsonSchema) {
        return new MappedFieldRepository(getStringFieldMap(jsonSchema));
    }

    private static Map<String, Field> getStringFieldMap(String jsonSchema) {
        java.lang.reflect.Type type = new TypeToken<Map<String, Field>>() {
        }.getType();
        Map<String, Field> fieldMap = getGson().fromJson(jsonSchema, type);
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            entry.getValue().setName(entry.getKey());
        }
        return fieldMap;
    }

    private static Gson getGson() {
        final RuntimeTypeAdapterFactory<Validator> validatorFactory = RuntimeTypeAdapterFactory.of(Validator.class, "type");
        for (Validators validator : Validators.values()) {
            validatorFactory.registerSubtype(validator.getValidator().getClass(), validator.getKeyword());
        }
        return new GsonBuilder()
                .registerTypeAdapterFactory(validatorFactory)
                .registerTypeAdapter(Type.class, new TypeDeserializer())
                .create();
    }

}
