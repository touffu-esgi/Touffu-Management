package cat.touffu.management.components.list.infrastructure;

import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import cat.touffu.management.components.list.domain.ListOfCard;
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
    public void save(ListOfCard project) {
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
    public ListOfCard findById(ListId projectId) {
        throw new NotImplementedException();
    }

    @Override
    public java.util.List<ListOfCard> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public void remove(ListOfCard item) {
        throw new NotImplementedException();
    }
}