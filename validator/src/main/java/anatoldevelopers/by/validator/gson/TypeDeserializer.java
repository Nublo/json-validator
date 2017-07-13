package anatoldevelopers.by.validator.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.util.Arrays;

import anatoldevelopers.by.validator.Type;

public class TypeDeserializer implements JsonDeserializer<Type> {

    @Override
    public Type deserialize(JsonElement json, java.lang.reflect.Type type,
                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Type[] types = Type.values();
        String stringType = json.getAsString();
        for (Type aType : types) {
            if (aType.keyword().equals(stringType))
                return aType;
        }
        throw new IllegalStateException("Unsupported type found '" + stringType + "' should be on of" + Arrays.toString(types));
    }

}