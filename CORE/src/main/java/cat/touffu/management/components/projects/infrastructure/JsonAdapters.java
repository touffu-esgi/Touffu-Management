package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.kernel.database.JsonAdapter;
import kong.unirest.JsonNode;
import kong.unirest.json.*;

import java.util.*;

public class JsonAdapters {

    public static class ListOfProjectsJsonAdapter implements JsonAdapter<List<Project>> {

        @Override
        public List<Project> adapt(JsonNode from) {
            var arr = from.getArray();
            List<JsonNode> projects = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) projects.add(new JsonNode(arr.get(i).toString()));

            var projectJsonAdapter = new ProjectJsonAdapter();
            return projects.stream().map(projectJsonAdapter::adapt).toList();
        }

        @Override
        public Map<String, Object> adapt(List<Project> projects) {
            return null;
        }
    }

    public static class ProjectJsonAdapter implements JsonAdapter<Project> {

        @Override
        public Project adapt(JsonNode from) {
            var project = from.getObject();
            Set cardIds = new HashSet<CardId>();
            try {
                cardIds = CardId.ofJsonArray(project.getJSONArray("cards"));
            } catch (JSONException e) {}

            return Project.of(
                    ProjectId.of(project.getString("id")),
                    project.getString("title"),
                    cardIds
            );
        }

        @Override
        public Map<String, Object>  adapt(Project project) {
            var map = new HashMap<String, Object>();
            map.put("id", project.id().value());
            map.put("title", project.title());
            map.put("cards", project.cards().stream().map(CardId::value).toList());
            return map;
        }
    }

}
