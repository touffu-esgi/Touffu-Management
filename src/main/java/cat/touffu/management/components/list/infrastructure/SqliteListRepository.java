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
            this.sqlite = SqliteJdbc.getInstance().getConnection();
        } catch (SQLException e) {
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
            statement.setString(2, project.title());
            statement.setString(3, project.projectId().value());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(ListOfCard listOfCard) {
        throw new NotImplementedException("add list of card");
    }

    @Override
    public void update(ListOfCard listOfCard) {
        throw new NotImplementedException("update list of card");
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
