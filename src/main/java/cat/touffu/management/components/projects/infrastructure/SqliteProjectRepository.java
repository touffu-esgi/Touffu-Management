package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteProjectRepository implements ProjectRepository {
    private static final ProjectRepository INSTANCE = new SqliteProjectRepository();
    private Connection sqlite;

    private SqliteProjectRepository() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.sqlite = SqliteJdbc.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ProjectRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Project project) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("insert into project(id, title) VALUES (?, ?)");
            statement.setString(1, project.id().value());
            statement.setString(2, project.title());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project findById(ProjectId projectId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        try {
            Statement statement = sqlite.createStatement();
            ResultSet result = statement.executeQuery("select id, title from project");
            while (result.next()) {
                projects.add(new Project(
                        ProjectId.of(result.getString("id")),
                        result.getString("title")
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
