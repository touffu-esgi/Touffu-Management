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
            subscribers.forEach(subscriber -> sendEventToSubsciber(event, subscriber));
        }
    }

    private void sendEventToSubsciber(E event, Subscriber<E> subscriber) {
        try {
            subscriber.accept(event);
        } catch (Exception e) {
            String subscriberName = subscriber.getClass().getSimpleName();
            System.out.println(subscriberName + " : " + e.getMessage());
        }
    }


    private void trySend(E event) {

    }

    @Override
    public void registerSubscriber(Class<E> classEvent, List<Subscriber<E>> givenSubscribers) {
        subscribers.putIfAbsent(classEvent, givenSubscribers);
    }
}
