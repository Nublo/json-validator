package anatoldevelopers.by.validator.validator;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import anatoldevelopers.by.validator.Field;
import anatoldevelopers.by.validator.Type;
import anatoldevelopers.by.validator.ValidationError;

import static org.junit.Assert.assertEquals;

public class MaxLengthValidatorTest {

    @Test
    public void verifyCorrectLengthValidation() {
        MaxLengthValidator lengthValidator = new MaxLengthValidator("", "2");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 32);
        assertEquals(0, errors.size());
    }

    @Test
    public void testInputMoreThenExpected() {
        MaxLengthValidator lengthValidator = new MaxLengthValidator("", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 321);
        assertEquals(1, errors.size());
    }

    @Test
    public void testInputLessThenExpected() {
        MaxLengthValidator lengthValidator = new MaxLengthValidator("", "3");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 22);
        assertEquals(0, errors.size());
    }

    @Test
    public void testErrorAndValueSameAsInValidator() {
        MaxLengthValidator lengthValidator = new MaxLengthValidator("incorrectMessage", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.INTEGER), 32);
        assertEquals(1, errors.size());
        assertEquals("incorrectMessage", errors.get(0).getMessage());
        assertEquals(Collections.singletonList("1"), errors.get(0).getMessageArgs());
    }

    @Test
    public void testDoubleType() {
        MaxLengthValidator lengthValidator = new MaxLengthValidator("", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.DOUBLE), 1);
        assertEquals(0, errors.size());
    }

    @Test
    public void testIncorrectType() {
        MaxLengthValidator lengthValidator = new MaxLengthValidator("", "1");
        List<ValidationError> errors = lengthValidator.validate(new Field("age", Collections.<Validator>emptyList(), Type.DATE), 1);
        assertEquals(1, errors.size());
    }

}
