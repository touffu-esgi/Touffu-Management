package cat.touffu.management.kernel.exception;

public class ListNotFoundException extends NotFoundException{
    public ListNotFoundException(String listId) {
        super("List with id " + listId + " not found.");
    }
}
