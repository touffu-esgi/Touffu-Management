package cat.touffu.management.configuration;


import cat.touffu.management.kernel.validators.string.SimpleStringValidator;
import cat.touffu.management.kernel.validators.string.StringValidator;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public final class GlobalConfiguration {
    @Produces
    StringValidator stringValidator() {
        return new SimpleStringValidator();
    }
}
