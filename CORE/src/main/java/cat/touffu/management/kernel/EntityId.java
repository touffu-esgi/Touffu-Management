package cat.touffu.management.kernel;

public interface EntityId<T> {
    T value();
    boolean equals(Object o);
    int hashCode();
}
