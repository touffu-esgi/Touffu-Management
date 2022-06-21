package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.application.event.ProjectCreationDone;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.kernel.event.Subscriber;
import javafx.event.ActionEvent;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Set;

public record ProjectCreationDoneListener(ProjectRepository repository) implements Subscriber<ProjectCreationDone> {

    @Override
    public void accept(ProjectCreationDone event) {
        var project = Project.of(
                (ProjectId) event.projectId(),
                event.projectTitle(),
                Set.of()
        );
        Board.getInstance().controller.addProjectInLeftBar(project);
    }
}
