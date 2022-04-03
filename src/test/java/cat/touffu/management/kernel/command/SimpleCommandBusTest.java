package cat.touffu.management.kernel.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class SimpleCommandBusTest {
    private Map<Class<? extends Command>, CommandHandler> commandMap;
    private SimpleCommandBus simpleCommandBus;

    SimpleCommandBusTest() {
    }

    @BeforeEach
    void init() {
        MockCommandHandler mockCommandHandler = new MockCommandHandler();
        commandMap = new HashMap<>();
        commandMap.put(MockCommand.class, mockCommandHandler);
        simpleCommandBus = new SimpleCommandBus(commandMap);
    }

    @Test
    void shouldSendACommand() {
        Assertions.assertDoesNotThrow(() -> simpleCommandBus.send(new MockCommand()));
    }

}
