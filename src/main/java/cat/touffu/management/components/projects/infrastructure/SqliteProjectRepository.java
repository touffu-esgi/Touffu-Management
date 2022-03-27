package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.adapter.CardListIdsToStringAdapter;
import cat.touffu.management.components.projects.adapter.StringToCardListIdsAdapter;
import cat.touffu.management.components.projects.domain.CardListId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import cat.touffu.management.kernel.exception.NotFoundException;
import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqliteProjectRepository implements ProjectRepository {
    private static final ProjectRepository INSTANCE = new SqliteProjectRepository();
    private Connection sqlite;

    private final StringToCardListIdsAdapter stringToCardListIdsAdapter = new StringToCardListIdsAdapter();
    private final CardListIdsToStringAdapter cardListIdsToStringAdapter = new CardListIdsToStringAdapter();

    private SqliteProjectRepository() {
        try {
            this.sqlite = SqliteJdbc.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProjectRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Project project) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id from project where id = ?");
            statement.setString(1, project.id().value());
            ResultSet resultSet = statement.executeQuery();
            boolean idExists = resultSet.next();
            if (idExists) {
                update(project);
            } else {
                add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Project project) {
        try {
            PreparedStatement statement = sqlite.prepareStatement(
                    "insert into project(id, title, lists) VALUES (?, ?, ?)"
            );
            statement.setString(1, project.id().value());
            statement.setString(2, project.title());
            statement.setString(3, cardListIdsToStringAdapter.adapt(project.cardListIds()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project project) {
        try {
            PreparedStatement statement = sqlite.prepareStatement(
                    "update project set title = ?, lists = ? where id = ?"
            );
            statement.setString(1, project.title());
            statement.setString(2, cardListIdsToStringAdapter.adapt(project.cardListIds()));
            statement.setString(3, project.id().value());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project findById(ProjectId projectId) {

        try {
            PreparedStatement statement = sqlite.prepareStatement("select id, title, lists from project where id = ?");
            statement.setString(1, projectId.value());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return null;
            }
            return Project.of(
                    ProjectId.of(resultSet.getString("id")),
                    resultSet.getString("title"),
                    stringToCardListIdsAdapter.adapt(resultSet.getString("lists"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        try {
            Statement statement = sqlite.createStatement();
            ResultSet result = statement.executeQuery("select id, title, lists from project");
            while (result.next()) {
                projects.add(new Project(
                        ProjectId.of(result.getString("id")),
                        result.getString("title"),
                        new JSONArray(result.getString("lists")).toList().stream()
                                .map(id -> CardListId.of((String) id))
                                .collect(Collectors.toSet())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public void remove(Project item) {
        throw new NotImplementedException();
    }
}
