package cat.touffu.management.components.list.application.command.CreateNewListInProject;

import cat.touffu.management.kernel.command.Command;

public record CreateNewListOfCardsInProject(
        String title,
        String id_project
) implements Command {}
