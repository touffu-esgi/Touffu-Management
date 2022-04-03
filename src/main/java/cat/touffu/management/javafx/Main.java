package cat.touffu.management.javafx;

import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.kernel.query.Query;
import cat.touffu.management.kernel.query.QueryBus;
import javafx.application.Application;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        QueryBus queryBus = ProjectModule.queryBus();
        final List<Project> projects = queryBus.send(new RetrieveProjects());
        System.out.println(projects.toString());
        Application.launch(Board.class, args);
    }
}
