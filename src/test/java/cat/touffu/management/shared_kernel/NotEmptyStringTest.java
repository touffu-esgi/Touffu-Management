package cat.touffu.management.shared_kernel;

import cat.touffu.management.kernel.validators.string.StringValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyStringTest {

    private StringValidator stringValidator;

    @Test
    void shouldCreateANotEmptyString() {
        NotEmptyString str = new NotEmptyString("not empty");
        assertEquals("not empty", str.value());
    }

    @Test
    void shouldCreateAnEmptyStringWithDefaultConstructor() {
        // No verifications on the default constructor
        NotEmptyString str = new NotEmptyString("");
        assertEquals("", str.value());
    }

    @Test
    void shouldThrowNPEWhenNullGiven() {
        Assertions.assertThrows(NullPointerException.class, () -> new NotEmptyString(null));
    }

    @Test
    void shouldCreateANotEmptyStringWithValidator() {
        NotEmptyString str = NotEmptyString.of("not empty", new MockStringValidator());
        assertEquals("not empty", str.value());
    }

    @Test
    void shouldThrowIllegalArgumentWhenEmptyStringIsGivenWithValidator() {
        String empty = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> NotEmptyString.of(empty, new MockStringValidator()));
    }

    @Test
    void shouldThrowNPEWhenNullIsGivenWithValidator() {
        String nullString = null;
        Assertions.assertThrows(NullPointerException.class, () -> NotEmptyString.of(nullString, new MockStringValidator()));
    }
}
