package cat.touffu.management.components.list.application.command.CreateNewListOfCard;

import cat.touffu.management.kernel.event.Event;
import cat.touffu.management.kernel.event.EventBus;
import cat.touffu.management.kernel.event.Subscriber;

import java.util.List;
import java.util.Map;

public class MockEventBus<E extends Event> implements EventBus<E> {

    private final Map<Class<E>, List<Subscriber<E>>> subscribers;

    public MockEventBus(Map<Class<E>, List<Subscriber<E>>> subscribers) {
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
