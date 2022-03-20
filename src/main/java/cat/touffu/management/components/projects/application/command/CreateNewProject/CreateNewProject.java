package cat.touffu.management.components.projects.application.command.CreateNewProject;

import cat.touffu.management.kernel.command.Command;

public record CreateNewProject(
        String title
) implements Command {
}
