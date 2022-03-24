package cat.touffu.management.kernel.query;

@FunctionalInterface
public interface QueryHandler<TQuery extends Query, TResponse> {
    TResponse handle(TQuery command);
}
