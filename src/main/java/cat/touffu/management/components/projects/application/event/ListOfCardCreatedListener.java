package cat.touffu.management.components.projects.application.event;

import cat.touffu.management.components.list.domain.event.ListOfCardCreated;
import cat.touffu.management.components.projects.domain.CardListId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.event.Subscriber;

public record ListOfCardCreatedListener(
        ProjectRepository repository
) implements Subscriber<ListOfCardCreated> {
    @Override
    public void accept(ListOfCardCreated listOfCardCreated) {
        ProjectId projectId = ProjectId.of(listOfCardCreated.projectId().value());
        Project project = repository.findById(projectId);

        project.addCardList(CardListId.of(listOfCardCreated.listId().value()));

        repository.save(project);
    }
}
