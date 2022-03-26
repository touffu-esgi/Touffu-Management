package cat.touffu.management.components.list.application.query.DoesListExists;

import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListOfCard;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.kernel.query.QueryHandler;

public class DoesListExistsHandler implements QueryHandler<DoesListExists, Boolean> {
    private final ListRepository listRepository;

    public DoesListExistsHandler(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public Boolean handle(DoesListExists query) {
        ListOfCard list = listRepository.findById(ListId.of(query.listId()));
        return list == null;
    }
}
