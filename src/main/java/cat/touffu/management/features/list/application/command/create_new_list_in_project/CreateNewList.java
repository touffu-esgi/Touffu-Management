package cat.touffu.management.features.list.application.command.create_new_list_in_project;

import cat.touffu.management.kernel.command.Command;

public record CreateNewList(
        String content, String id_project
) implements Command {
}
