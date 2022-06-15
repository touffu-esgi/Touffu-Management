package cat.touffu.management.components.projects.exposition.cli;

import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CliProjectsListResponseAdapter {
    public static String adapt(List<Project> projects) {
        return projects.stream()
                .map(CliProjectsListResponseAdapter::projectToString)
                .collect(Collectors.joining("\n"));
    }

    private static String projectToString(Project project) {
        return project.id().value() + "\t" + project.title() + "\t" + cardIdsToString(project.cards());
    }

    private static String cardIdsToString(Set<CardId> cardIds) {
        return cardIds.stream()
                .map(CardId::value)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
