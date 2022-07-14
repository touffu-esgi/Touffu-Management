package cat.touffu.management.kernel;

@FunctionalInterface
public interface Adapter<From, To> {
    To adapt(From source);
}
