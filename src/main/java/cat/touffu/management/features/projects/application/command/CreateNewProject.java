package cat.touffu.management.features.projects.application.command;

import cat.touffu.management.kernel.command.Command;

public record CreateNewProject(
        String title
) implements Command {
}
