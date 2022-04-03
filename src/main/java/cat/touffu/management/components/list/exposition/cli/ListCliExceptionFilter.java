package cat.touffu.management.components.list.exposition.cli;

import cat.touffu.management.kernel.exception.ExceptionFilter;

public class ListCliExceptionFilter implements ExceptionFilter {
    @Override
    public void grab(Exception exception) {

        switch (exception.getClass().getSimpleName()) {
            case "ProjectNotFoundException":
                System.out.println(exception.getMessage());
                break;
            default: exception.printStackTrace();
        }
    }
}
