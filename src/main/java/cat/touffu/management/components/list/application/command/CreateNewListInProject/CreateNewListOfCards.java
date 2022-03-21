package cat.touffu.management.components.list.application.command.CreateNewListInProject;

import cat.touffu.management.kernel.command.Command;

public record CreateNewListOfCards(
        String content, String id_project
) implements Command {
}
