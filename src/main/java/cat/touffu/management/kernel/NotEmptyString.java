package cat.touffu.management.kernel;

import cat.touffu.management.kernel.validators.string.StringValidator;
import java.util.Objects;

public record NotEmptyString(String value) {
    public NotEmptyString {
        Objects.requireNonNull(value);
    }

    public static NotEmptyString of(String value, StringValidator stringValidator) {
        return new NotEmptyString(value, stringValidator);
    }

    private NotEmptyString(String value, StringValidator stringValidator) {
        this(value);
        if (stringValidator.isEmpty(value)) {
            throw new IllegalArgumentException("String must not be empty.");
        }
    }

}
