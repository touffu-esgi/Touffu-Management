package cat.touffu.management.components.projects.application.event;

import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.event.Subscriber;

public record CardCreationDoneListener(ProjectRepository repository) implements Subscriber<CardCreationDone> {
    @Override
    public void accept(CardCreationDone event) {
        ProjectId projectToUpdate = ProjectId.of(event.projectId().value());
        CardId cardCreated = CardId.of(event.cardId().value());
        Project project = repository.findById(projectToUpdate);

        if(project.contains(cardCreated)) return;

        project.addCard(cardCreated);
        repository.save(project);
    }
}
