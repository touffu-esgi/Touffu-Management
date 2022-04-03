package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.kernel.query.Query;
import cat.touffu.management.kernel.query.QueryBus;
import cat.touffu.management.kernel.query.QueryHandler;

import java.util.Map;

public record MockQueryBus(
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
