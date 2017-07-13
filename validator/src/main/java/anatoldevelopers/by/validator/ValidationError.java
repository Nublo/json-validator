package anatoldevelopers.by.validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidationError {

    private final Field subject;
    private final String systemMessage;
    private final String message;
    private final List messageArgs;

    public ValidationError(Field subject, String systemMessage) {
        this(subject, systemMessage, null, Collections.emptyList());
    }

    public ValidationError(Field subject, String message, Object... messageArgs) {
        this(subject, null, message, Arrays.asList(messageArgs));
    }

    public ValidationError(Field subject, String systemMessage, String message, List messageArgs) {
        this.subject = subject;
        this.message = message;
        this.systemMessage = systemMessage;
        this.messageArgs = messageArgs;
    }

    public Field getSubject() {
        return subject;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public String getMessage() {
        return message;
    }

    public List getMessageArgs() {
        return messageArgs;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "subject=" + subject +
                ", systemMessage='" + systemMessage + '\'' +
                ", message='" + message + '\'' +
                ", messageArgs=" + messageArgs +
                '}';
    }

}