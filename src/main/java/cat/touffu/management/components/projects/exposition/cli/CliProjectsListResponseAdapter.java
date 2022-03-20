package cat.touffu.management.components.projects.exposition.cli;

import cat.touffu.management.components.projects.domain.Project;

import java.util.List;
import java.util.stream.Collectors;

public class CliProjectsListResponseAdapter {
    public static String adapt(List<Project> projects) {
        return projects.stream()
                .map(project -> project.id().value() + "\t" + project.title())
                .collect(Collectors.joining("\n"));
    }
}
