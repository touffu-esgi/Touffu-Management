package cat.touffu.management.configuration;

import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import com.google.inject.AbstractModule;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import picocli.CommandLine;

public class CDIFactory implements CommandLine.IFactory {
    private final Injector injector = Guice.createInjector(new CliModule());

    @Override
    public <K> K create(Class<K> aClass) throws Exception {
        try {
            return injector.getInstance(aClass);
        } catch (ConfigurationException ex) { // no implementation found in Guice configuration
            return CommandLine.defaultFactory().create(aClass); // fallback if missing
        }
    }

    static class CliModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(CommandBus.class).to(SimpleCommandBus.class);
        }
    }
}
