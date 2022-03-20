package cat.touffu.management.features.list.infrastructure;

import cat.touffu.management.features.list.domain.ListId;
import cat.touffu.management.features.list.domain.ListRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import cat.touffu.management.features.list.domain.AList;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteListRepository implements ListRepository {
    private static final ListRepository INSTANCE = new SqliteListRepository();
    private Connection sqlite;

    private SqliteListRepository() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.sqlite = SqliteJdbc.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ListRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(AList project) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("insert into list(id, content, id_project) VALUES (?, ?, ?)");
            statement.setString(1, project.id().value());
            statement.setString(2, project.content());
            statement.setString(3, project.project_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AList findById(ListId projectId) {
        throw new NotImplementedException();
    }

    @Override
    public java.util.List<AList> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public void remove(AList item) {
        throw new NotImplementedException();
    }
}
