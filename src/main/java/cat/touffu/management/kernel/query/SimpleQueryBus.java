package cat.touffu.management.kernel.query;

import java.util.Map;

public record SimpleQueryBus(
        Map<Class<? extends Query>, QueryHandler> queryMap
) implements QueryBus {

    @Override
    public <TQuery extends Query, TResponse> TResponse send(TQuery query) {
        return dispatch(query);
    }

    private <TQuery extends Query, TResponse> TResponse dispatch(TQuery query) {
        final QueryHandler queryHandler = queryMap.get(query.getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such command handler for " + query.getClass().getName());
        }
        return (TResponse) queryHandler.handle(query);
    }
}
