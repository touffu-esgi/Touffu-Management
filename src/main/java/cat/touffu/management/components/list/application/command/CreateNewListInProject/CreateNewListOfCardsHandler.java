package cat.touffu.management.components.list.application.command.CreateNewListInProject;


import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.components.list.domain.ProjectId;
import cat.touffu.management.components.list.domain.event.ListOfCardCreated;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.components.list.domain.ListOfCard;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventBus;

public class CreateNewListOfCardsHandler implements CommandHandler<CreateNewListOfCardsInProject, ListId> {

    private final ListRepository listRepository;
    private final EventBus<ApplicationEvent> applicationEventBus;

    public CreateNewListOfCardsHandler(ListRepository listRepository, EventBus<ApplicationEvent> domainEventBus) {
        this.listRepository = listRepository;
        this.applicationEventBus = domainEventBus;
    }

    @Override
    public ListId handle(CreateNewListOfCardsInProject command) {
        final ListOfCard list = ListOfCard.of(
                listRepository.newId(),
                command.title(),
                command.id_project()
        );
        listRepository.save(list);
        applicationEventBus.send(ListOfCardCreated.of(
                list.id(),
                list.projectId()
                ));
        return list.id();
    }
}
