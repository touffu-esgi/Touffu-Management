package cat.touffu.management.components.list.application.command.create_new_list_in_project;


import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.components.list.domain.ListOfCard;

public class CreateNewListOfCardsHandler implements CommandHandler<CreateNewListOfCards, ListId> {

    private final ListRepository listRepository;

    public CreateNewListOfCardsHandler(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ListId handle(CreateNewListOfCards command) {
        final ListOfCard list = new ListOfCard(
                listRepository.newId(),
                command.content(),
                command.id_project()
        );
        listRepository.save(list);
        return list.id();
    }
}
