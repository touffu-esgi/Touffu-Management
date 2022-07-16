package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kong.unirest.*;
import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestApiProjectRepository implements ProjectRepository {

    private final static RestApiProjectRepository INSTANCE = new RestApiProjectRepository();

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
        throw new NotImplementedException("save project");
    }

    @Override
    public void add(Project project) {
        throw new NotImplementedException("add project");
    }

    @Override
    public void update(Project project) {
        throw new NotImplementedException("update project");
    }

    @Override
    public Project findById(ProjectId projectId) {
        throw new NotImplementedException("find project by id");
    }

    @Override
    public List<Project> findAll() {
        //HttpResponse<JsonNode> res = Unirest
        var res = Unirest.get("http://localhost:1234/projects").asJson();
        var arr = res.getBody().getArray();
        List<JSONObject> projects = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) projects.add(new JSONObject(arr.get(i).toString()));

        var tt = projects.stream().map(p -> Project.of(
                ProjectId.of(p.getString("id")),
                p.getString("title"),
                CardId.ofJsonArray(p.getJSONArray("cards"))
        )).toList();
        System.out.println("tt = " + tt);
        return tt;
    }

    @Override
    public void remove(Project item) {
        throw new NotImplementedException("remove project");
    }
}
