package cat.touffu.management.kernel.query;

public interface QueryBus {
    <TQuery extends Query, TResponse> TResponse request(TQuery query);
}
