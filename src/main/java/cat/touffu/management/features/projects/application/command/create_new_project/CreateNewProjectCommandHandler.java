package cat.touffu.management.features.projects.application.command.create_new_project;


import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.shared_kernel.EntityId;

public class CreateNewProjectCommandHandler implements CommandHandler<CreateNewProject, EntityId> {

    @Override
    public EntityId handle(CreateNewProject command) {
        System.out.println("HANDLE CREATE NEW PROJECT");
        // TODO
        return null;
    }
}
