package cat.touffu.management.components.projects.exposition.cli;

import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.kernel.query.QueryBus;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "ls-projects",
        description = "List all the projects."
)
public class ListAllProjects implements Runnable{
    QueryBus queryBus = ProjectModule.queryBus();

    @Override
    public void run() {
        final List<Project> projects = queryBus.send(new RetrieveProjects());
        final String response = CliProjectsListResponseAdapter.adapt(projects);
        System.out.println(response);
    }
}
