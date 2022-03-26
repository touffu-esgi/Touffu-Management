package cat.touffu.management.components.list.application.command.CreateNewListOfCard;

import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsHandler;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.application.event.ListOfCardCreated;
import cat.touffu.management.components.projects.application.command.CreateNewProject.MockProjectRepository;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.kernel.event.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateNewListOfCardInProjectHandlerTest {

    @Test
    void shouldCreateANewListOfCard() {
        MockListRepository listRepository = new MockListRepository();
        Project project = Project.of(
                ProjectId.of("0308eb60-bb8c-422e-a356-a0f5434a5325"),
                "title",
                new HashSet<>()
                );
        MockProjectRepository projectRepository = new MockProjectRepository();
        projectRepository.store.put(project.id().value(), project);
        Map<Class<? extends Event>, List<Subscriber<? extends Event>>> mapEvent = new HashMap<>();
        mapEvent.put(
                ListOfCardCreated.class,
                List.of(new ListOfCardCreatedListenerTest(projectRepository))
        );
        EventBus<ApplicationEvent> eventBus = new SimpleEventBus(mapEvent);
        CreateNewListOfCardsHandler handler = new CreateNewListOfCardsHandler(listRepository, eventBus);

        ListId createdId = handler.handle(new CreateNewListOfCardsInProject("To Do", project.id().value()));

        assertTrue(listRepository.store.containsKey(createdId.value()));
        assertEquals("To Do", listRepository.store.get(createdId.value()).title());
    }
}
