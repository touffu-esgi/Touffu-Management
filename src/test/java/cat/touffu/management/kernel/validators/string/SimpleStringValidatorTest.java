package cat.touffu.management.kernel.validators.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleStringValidatorTest {

    private final SimpleStringValidator validator = new SimpleStringValidator();

    @Test
    void shouldValidateNotEmptyString() {
        String notEmpty = "Not Empty !";
        assertFalse(validator.isEmpty(notEmpty));
    }

    @Test
    void shouldNotValidateEmptyString() {
        String empty = "";
        assertTrue(validator.isEmpty(empty));
    }

    @Test
    void shouldNotValidateBlankString() {
        // blank string is filled with spaces or tabs or other white spaces
        String blank = "  \t \n";
        assertTrue(validator.isEmpty(blank));
    }

    @Test
    void shouldValidateNotTrimmedString() {
        String notEmptyNotTrimmed = "       Not Empty !           ";
        assertFalse(validator.isEmpty(notEmptyNotTrimmed));
    }

}
