package cat.touffu.management.kernel.command;

public class MockCommandHandler implements CommandHandler<MockCommand, String>{
    @Override
    public String handle(MockCommand command) {
        return "response";
    }
}
