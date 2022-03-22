package cat.touffu.management.components.projects.domain.event;

import cat.touffu.management.components.list.domain.event.ListOfCardCreated;
import cat.touffu.management.components.projects.domain.CardListId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.event.Subscriber;

public class ListOfCardCreatedListener implements Subscriber<ListOfCardCreated> {
    private final ProjectRepository repository;

    public ListOfCardCreatedListener(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void accept(ListOfCardCreated listOfCardCreated) {
        ProjectId projectId = ProjectId.of(listOfCardCreated.projectId().value());
        Project project = repository.findById(projectId);

        project.addCardList(CardListId.of(listOfCardCreated.listId().value()));

        repository.save(project);
    }
}
