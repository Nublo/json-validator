package anatoldevelopers.by.validator;

import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.validator.Validator;

public class Field {

    private String name;
    private Type type;
    private String description;
    private String format;
    private List<Validator> validators = Collections.emptyList();

    public Field(String name, List<Validator> validators, Type type) {
        this.name = name;
        this.validators = validators;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", validators=" + validators +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return equals(name, field.name) &&
                equals(type, field.type) &&
                equals(description, field.description) &&
                equals(validators, field.validators);
    }

    private boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (validators != null ? validators.hashCode() : 0);
        return result;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
