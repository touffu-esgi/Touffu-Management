package cat.touffu.management.configuration;


import cat.touffu.management.kernel.validators.string.SimpleStringValidator;
import cat.touffu.management.kernel.validators.string.StringValidator;



public final class GlobalConfiguration {

    StringValidator stringValidator() {
        return new SimpleStringValidator();
    }
}
