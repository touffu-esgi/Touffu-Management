package cat.touffu.management.kernel.event;

import java.util.function.Consumer;

public interface Subscriber<E extends Event> extends Consumer<E> {
}
