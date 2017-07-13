package anatoldevelopers.by.validator;

import java.util.Date;

public enum Type {

    STRING("string", String.class),
    BOOLEAN("boolean", Boolean.class),
    DATE("date", Date.class),
    INTEGER("integer", Integer.class),
    LONG("long", Long.class),
    DOUBLE("double", Double.class);

    private final String keyword;
    private final Class typeClass;

    Type(String keyword, Class typeClass) {
        this.keyword = keyword;
        this.typeClass = typeClass;
    }

    public String keyword() {
        return keyword;
    }

    public Class typeClass() {
        return typeClass;
    }

}