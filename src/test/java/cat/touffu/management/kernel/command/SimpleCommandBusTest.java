package cat.touffu.management.kernel.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals("response", simpleCommandBus.send(new MockCommand()));
    }

    @Test
    void shouldThrowRunTimeExceptionForUnknownCommand() {
        commandMap = new HashMap<>();
        simpleCommandBus = new SimpleCommandBus(commandMap);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> simpleCommandBus.send(new MockCommand()));
        assertEquals("No such command handler for cat.touffu.management.kernel.command.MockCommand", exception.getMessage());
    }
}
