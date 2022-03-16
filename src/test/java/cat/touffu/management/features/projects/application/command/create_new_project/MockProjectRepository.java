package cat.touffu.management.features.projects.application.command.create_new_project;

import cat.touffu.management.features.projects.domain.Project;
import cat.touffu.management.features.projects.domain.ProjectId;
import cat.touffu.management.features.projects.domain.ProjectRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockProjectRepository implements ProjectRepository {

    public Map<ProjectId, Project> store = new HashMap<>();

    @Override
    public void save(Project project) {
        this.store.put(project.id(), project);
    }

    @Override
    public Project findById(ProjectId projectId) {
        throw new NotImplementedException("find project by id");
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
