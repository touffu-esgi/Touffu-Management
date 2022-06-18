package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.application.event.ProjectCreationDone;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.kernel.event.Subscriber;
import javafx.event.ActionEvent;

public record ProjectCreationDoneListener(ProjectRepository repository) implements Subscriber<ProjectCreationDone> {

    @Override
    public void accept(ProjectCreationDone event) {
        Board.getInstance().controller.addProjectInLeftBar(
                event.projectId().value(),
                event.projectTitle()
                );
    }
}
