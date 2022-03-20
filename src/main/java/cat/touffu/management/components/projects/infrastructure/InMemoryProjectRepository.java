package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProjectRepository implements ProjectRepository {
    private static final ProjectRepository INSTANCE = new InMemoryProjectRepository();
    private final Map<ProjectId, Project> projectStore;

    private InMemoryProjectRepository() {
        this.projectStore = new ConcurrentHashMap<>();
    }

    public static ProjectRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Project project) {
        projectStore.put(project.id(), project);
    }

    @Override
    public Project findById(ProjectId projectId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Project> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public void remove(Project item) {
        throw new NotImplementedException();
    }
}
