package cat.touffu.management.kernel.command;

@FunctionalInterface
public interface Handler<C extends Command, R> {
    R handle(C command);
}
