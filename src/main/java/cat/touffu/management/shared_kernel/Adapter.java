package cat.touffu.management.shared_kernel;

@FunctionalInterface
public interface Adapter<From, To> {
    To adapt(From source);
}
