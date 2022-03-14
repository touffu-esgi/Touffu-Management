package cat.touffu.management.kernel.validators.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleStringValidatorTest {

    private final SimpleStringValidator validator = new SimpleStringValidator();

    @Test
    void shouldValidateNotEmptyString() {
        String notEmpty = "Not Empty !";
        assertTrue(validator.isEmpty(notEmpty));
    }

    @Test
    void shouldNotValidateEmptyString() {
        String empty = "";
        assertFalse(validator.isEmpty(empty));
    }

    @Test
    void shouldNotValidateBlankString() {
        // blank string is filled with spaces or tabs or other white spaces
        String blank = "  \t \n";
        assertFalse(validator.isEmpty(blank));
    }

    @Test
    void shouldValidateNotTrimmedString() {
        String notEmptyNotTrimmed = "       Not Empty !           ";
        assertTrue(validator.isEmpty(notEmptyNotTrimmed));
    }

}
