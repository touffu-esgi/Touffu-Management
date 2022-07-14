package cat.touffu.management.components.cards.exposition.cli;

import cat.touffu.management.kernel.exception.ExceptionFilter;

public class CardCliExceptionFilter implements ExceptionFilter {
    @Override
    public void grab(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
