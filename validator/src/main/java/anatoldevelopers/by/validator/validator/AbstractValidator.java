package anatoldevelopers.by.validator.validator;

public abstract class AbstractValidator implements Validator {
    protected String message;
    protected String value;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return
                "message='" + message + '\'' +
                        ", value='" + value + '\'';

    }

}