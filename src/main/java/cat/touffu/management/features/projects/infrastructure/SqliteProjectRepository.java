package cat.touffu.management.features.projects.infrastructure;

import cat.touffu.management.features.projects.domain.Project;
import cat.touffu.management.features.projects.domain.ProjectId;
import cat.touffu.management.features.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        throw new NotImplementedException();
    }

    @Override
    public void remove(Project item) {
        throw new NotImplementedException();
    }
}
