package cat.touffu.management.components.list.application.command.CreateNewListOfCard;

import cat.touffu.management.kernel.query.Query;
import cat.touffu.management.kernel.query.QueryBus;
import cat.touffu.management.kernel.query.QueryHandler;

import java.util.Map;

public record MockQueryBusForCreateNewListOfCard(
        Boolean projectExists
) implements QueryBus {

    @Override
    public <TQuery extends Query, TResponse> TResponse send(TQuery query) {
        return dispatch(query);
    }

    private <TQuery extends Query, TResponse> TResponse dispatch(TQuery query) {
        // do nothing
        return (TResponse) projectExists;
    }
}
