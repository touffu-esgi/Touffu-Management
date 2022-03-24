package cat.touffu.management.components.list.application.command.create_new_list_in_project;

import cat.touffu.management.kernel.command.Command;

public record CreateNewListOfCards(
        String content, String id_project
) implements Command {
}
