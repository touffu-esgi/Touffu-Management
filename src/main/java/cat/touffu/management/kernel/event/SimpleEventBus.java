package cat.touffu.management.kernel.event;

import java.util.List;
import java.util.Map;

public class SimpleEventBus<E extends Event> implements EventBus<E> {

    private final Map<Class<E>, List<Subscriber<E>>> subscribers;

    public SimpleEventBus(Map<Class<E>, List<Subscriber<E>>> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public void send(E event) {
        final List<Subscriber<E>> subscribers = this.subscribers.get(event.getClass());
        final boolean subscribersExists = subscribers != null && !subscribers.isEmpty();
        if (subscribersExists) {
            subscribers.forEach(subscriber -> subscriber.accept(event));
        }
    }

    @Override
    public void registerSubscriber(Class<E> classEvent, List<Subscriber<E>> givenSubscribers) {
        subscribers.putIfAbsent(classEvent, givenSubscribers);
    }
}
