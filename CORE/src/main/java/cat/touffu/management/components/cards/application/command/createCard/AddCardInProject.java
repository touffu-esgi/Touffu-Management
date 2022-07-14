package cat.touffu.management.components.cards.application.command.createCard;

import cat.touffu.management.kernel.command.Command;

public record AddCardInProject(
        String title,
        String projectId,
        String status
) implements Command {

    public static AddCardInProject of(String cardTitle, String projectId, String status) {
        return new AddCardInProject(
                cardTitle,
                projectId,
                status
        );
    }
}
