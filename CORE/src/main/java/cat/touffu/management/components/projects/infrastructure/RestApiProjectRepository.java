package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;


import java.util.List;

import cat.touffu.management.kernel.database.RestApi;
import org.apache.commons.lang3.NotImplementedException;
import cat.touffu.management.components.projects.infrastructure.JsonAdapters.*;

public class RestApiProjectRepository implements ProjectRepository {

    private final static RestApiProjectRepository INSTANCE = new RestApiProjectRepository();
    private final static RestApi API = RestApi.instance();
    private final static String base = "/projects";

    private RestApiProjectRepository() {
    }

    public static RestApiProjectRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean exists(ProjectId projectId) {
        throw new NotImplementedException("does project exists");
    }

    @Override
    public void save(Project project) {
        this.add(project);
    }

    @Override
    public void add(Project project) {
        API.post(base, new ProjectJsonAdapter(), project);
    }

    @Override
    public void update(Project project) {
        throw new NotImplementedException("update project");
    }

    @Override
    public Project findById(ProjectId projectId) {
        var route = base + "/" + projectId.value();
        return API.get(route, new ProjectJsonAdapter());
    }

    @Override
    public List<Project> findAll() {
        return API.get(base, new ListOfProjectsJsonAdapter());
    }

    @Override
    public void remove(Project item) {
        throw new NotImplementedException("remove project");
    }
}
