package cat.touffu.management.kernel.event;

import java.util.List;

public interface EventBus<E extends Event> {
    void send(E event);

    void registerSubscriber(Class<E> classEvent, List<Subscriber<E>> subscribers);
}
