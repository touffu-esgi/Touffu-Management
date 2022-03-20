package cat.touffu.management.shared_kernel;

import cat.touffu.management.kernel.validators.string.StringValidator;

public class MockStringValidator implements StringValidator {
    @Override
    public boolean isEmpty(String string) {
        return string == null || string.equals("");
    }
}
