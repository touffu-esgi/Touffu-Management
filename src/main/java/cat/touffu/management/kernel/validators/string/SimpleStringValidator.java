package cat.touffu.management.kernel.validators.string;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public final class SimpleStringValidator implements StringValidator {
    @Override
    public boolean isEmpty(String string) {
        return !isNotBlank(string);
    }
}
