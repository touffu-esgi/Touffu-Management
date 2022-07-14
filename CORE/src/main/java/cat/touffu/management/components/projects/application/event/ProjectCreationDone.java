package cat.touffu.management.components.projects.application.event;

import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.EntityId;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventId;
import cat.touffu.management.kernel.event.Subscriber;

import java.time.ZonedDateTime;

public record ProjectCreationDone(
        EventId eventId,
        ZonedDateTime occurredDate,
        String projectTitle,
        EntityId<String> projectId
) implements ApplicationEvent {

    public static ProjectCreationDone of(EntityId<String> projectId, String projectTitle) {
        return new ProjectCreationDone(
                EventId.create(),
                ZonedDateTime.now(),
                projectTitle,
                projectId
        );
    }
}
