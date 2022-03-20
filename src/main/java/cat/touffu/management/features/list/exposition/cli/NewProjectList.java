package cat.touffu.management.features.list.exposition.cli;

import cat.touffu.management.features.list.application.command.create_new_list_in_project.CreateNewList;
import cat.touffu.management.features.list.domain.ListId;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.features.list.ListModule;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-list",
        description = "Create a new list in a project."
)
public class NewProjectList implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "content", description = "List title")
    String content;

    @CommandLine.Parameters(index = "1", paramLabel = "id_project", description = "id of a project")
    String id_project;
    CommandBus commandBus = ListModule.commandBus();

    @Override
    public void run() {
        System.out.println(content);
        CreateNewList createNewProject = new CreateNewList(content, id_project);
        ListId listId = this.commandBus.send(createNewProject);
        System.out.println("New list of project '" + this.content + "' have been created in list with uuid = " + listId.value());
    }
}
