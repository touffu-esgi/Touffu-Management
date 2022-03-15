package cat.touffu.management.features.projects.application.command.create_new_project;

import cat.touffu.management.kernel.command.Command;

public record CreateNewProject(
        String title
) implements Command {
}
