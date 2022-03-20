package cat.touffu.management.features.list.application.command.create_new_list_in_project;


import cat.touffu.management.features.list.domain.ListId;
import cat.touffu.management.features.list.domain.ListRepository;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.features.list.domain.AList;

public class CreateNewListOfProjectHandler implements CommandHandler<CreateNewList, ListId> {

    private final ListRepository listRepository;

    public CreateNewListOfProjectHandler(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ListId handle(CreateNewList command) {
        final AList list = new AList(
                listRepository.nextId(),
                command.content(),
                command.id_project()
        );
        listRepository.save(list);
        return list.id();
    }
}
