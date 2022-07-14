package cat.touffu.management.components.cards.application.command.updateCard;
import cat.touffu.management.kernel.command.Command;


public record UpdateCard(
        String id,
        String title,
        String status
) implements Command{

    public static UpdateCard of(String id, String title, String status) {
        return new UpdateCard(
                id,
                title,
                status
        );
    }
}
