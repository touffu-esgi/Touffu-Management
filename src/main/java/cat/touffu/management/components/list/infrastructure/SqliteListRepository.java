package cat.touffu.management.components.list.infrastructure;

import cat.touffu.management.components.list.adapter.CardIdsToStringAdapter;
import cat.touffu.management.components.list.adapter.StringToCardIdsAdapter;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListOfCard;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import cat.touffu.management.kernel.exception.NotFoundException;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteListRepository implements ListRepository {
    private static final ListRepository INSTANCE = new SqliteListRepository();
    private Connection sqlite;

    private final StringToCardIdsAdapter stringToCardIdsAdapter = new StringToCardIdsAdapter();
    private final CardIdsToStringAdapter cardIdsToStringAdapter = new CardIdsToStringAdapter();

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
    public void save(ListOfCard list) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id from list where id = ?");
            statement.setString(1, list.id().value());
            ResultSet resultSet = statement.executeQuery();
            boolean idExists = resultSet.next();
            if (idExists) {
                update(list);
            } else {
                add(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(ListOfCard list) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("""
insert into list(id, title, id_project, cards) VALUES (?, ?, ?, ?)
""");
            statement.setString(1, list.id().value());
            statement.setString(2, list.title());
            statement.setString(3, list.projectId().value());
            statement.setString(4, cardIdsToStringAdapter.adapt(list.cardIds()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ListOfCard list) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("""
update list set title = ?, id_project = ?, cards = ? where id = ?
""");

            statement.setString(1, list.title());
            statement.setString(2, list.projectId().value());
            statement.setString(3, cardIdsToStringAdapter.adapt(list.cardIds()));
            statement.setString(4, list.id().value());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ListOfCard findById(ListId listId) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id, title, id_project, cards from list where id = ?");
            statement.setString(1, listId.value());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                throw new NotFoundException("List with id " + listId.value());
            }
            return ListOfCard.of(
                    ListId.of(resultSet.getString("id")),
                    resultSet.getString("title"),
                    resultSet.getString("id_project"),
                    stringToCardIdsAdapter.adapt(resultSet.getString("cards"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
