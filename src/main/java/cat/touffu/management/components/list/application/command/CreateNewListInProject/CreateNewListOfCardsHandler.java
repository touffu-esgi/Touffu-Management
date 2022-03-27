package cat.touffu.management.components.list.application.command.CreateNewListInProject;


import cat.touffu.management.components.list.application.event.ListOfCardCreated;
import cat.touffu.management.components.list.domain.CardId;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListOfCard;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExists;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventBus;
import cat.touffu.management.kernel.exception.NotFoundException;
import cat.touffu.management.kernel.query.QueryBus;

import java.util.HashSet;

public class CreateNewListOfCardsHandler implements CommandHandler<CreateNewListOfCardsInProject> {

    private final ListRepository listRepository;
    private final EventBus<ApplicationEvent> applicationEventBus;
    private final QueryBus queryBus;

    public CreateNewListOfCardsHandler(ListRepository listRepository, EventBus<ApplicationEvent> domainEventBus, QueryBus queryBus) {
        this.listRepository = listRepository;
        this.applicationEventBus = domainEventBus;
        this.queryBus = queryBus;
    }

    @Override
    public void handle(CreateNewListOfCardsInProject command) {
        Boolean projectExists = queryBus.send(new DoesProjectExists(command.id_project()));
        if(!projectExists) {
            throw new NotFoundException("Project " + command.id_project());
        }
        final ListOfCard list = ListOfCard.of(
                listRepository.newId(),
                command.title(),
                command.id_project(),
                new HashSet<CardId>());
        listRepository.save(list);
        applicationEventBus.send(ListOfCardCreated.of(
                list.id(),
                list.projectId()
                ));
    }
}
