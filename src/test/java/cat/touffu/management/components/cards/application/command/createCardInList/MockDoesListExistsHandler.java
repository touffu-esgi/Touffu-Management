package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.components.list.application.query.DoesListExists.DoesListExists;
import cat.touffu.management.kernel.query.QueryHandler;

public class MockDoesListExistsHandler implements QueryHandler<DoesListExists, Boolean> {
    public Boolean listExists = null;

    public MockDoesListExistsHandler(Boolean listExists) {
        this.listExists = listExists;
    }

    @Override
    public Boolean handle(DoesListExists query) {
        return listExists;
    }
}
