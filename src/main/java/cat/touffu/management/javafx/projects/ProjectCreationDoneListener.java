package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.projects.application.event.ProjectCreationDone;
import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.event.Subscriber;

public record ProjectCreationDoneListener(ProjectRepository repository) implements Subscriber<ProjectCreationDone> {

    @Override
    public void accept(ProjectCreationDone projectCreationDone) {

    }
}
