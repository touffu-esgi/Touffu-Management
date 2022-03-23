package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.adapter.CardListIdAdapter;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.*;
import java.util.HashSet;
import java.util.List;

public class SqliteProjectRepository implements ProjectRepository {
    private static final ProjectRepository INSTANCE = new SqliteProjectRepository();
    private Connection sqlite;

    private final CardListIdAdapter cardListIdAdapter = new CardListIdAdapter();

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
            statement.setString(3, project.cardListIdsToString());
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
            statement.setString(2, project.cardListIdsToString());
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
                    cardListIdAdapter.adapt(resultSet.getString("lists"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
