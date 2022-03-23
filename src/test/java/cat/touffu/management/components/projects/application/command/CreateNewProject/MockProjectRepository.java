package cat.touffu.management.components.projects.application.command.CreateNewProject;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockProjectRepository implements ProjectRepository {

    public Map<String, Project> store = new HashMap<>();

    @Override
    public void save(Project project) {
        this.store.put(project.id().value(), project);
    }

    @Override
    public Project findById(ProjectId projectId) {
        return store.get(projectId.value());
    }

    @Override
    public List<Project> findAll() {
        throw new NotImplementedException("find all projects");
    }

    @Override
    public void remove(Project item) {
        throw new NotImplementedException("remove project");
    }
}
