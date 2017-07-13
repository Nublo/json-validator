package anatoldevelopers.by.validator.validator;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;

import static org.junit.Assert.assertEquals;

public class LengthValidatorTest {

    @Test
    public void verifyCorrectLengthValidation() {
        LengthValidator lengthValidator = new LengthValidator("", "2");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 32);
        assertEquals(0, errors.size());
    }

    @Test
    public void testInputMoreThenExpected() {
        LengthValidator lengthValidator = new LengthValidator("", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 32);
        assertEquals(1, errors.size());
    }

    @Test
    public void testInputLessThenExpected() {
        LengthValidator lengthValidator = new LengthValidator("", "3");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 1);
        assertEquals(1, errors.size());
    }

    @Test
    public void testErrorAndValueSameAsInValidator() {
        LengthValidator lengthValidator = new LengthValidator("incorrectMessage", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 32);
        assertEquals(1, errors.size());
        assertEquals("incorrectMessage", errors.get(0).getMessage());
        assertEquals(Collections.singletonList("1"), errors.get(0).getMessageArgs());
    }

    @Test
    public void testDoubleType() {
        LengthValidator lengthValidator = new LengthValidator("", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.DOUBLE), 1);
        assertEquals(0, errors.size());
    }

    @Test
    public void testIncorrectType() {
        LengthValidator lengthValidator = new LengthValidator("", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.DATE), 1);
        assertEquals(1, errors.size());
    }

}