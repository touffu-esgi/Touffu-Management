package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.kernel.command.Command;

public record CreateCardInList(
        String title,
        String listId
) implements Command {

    public static CreateCardInList of(String cardTitle, String listId) {
        return new CreateCardInList(
                cardTitle,
                listId
        );
    }
}
