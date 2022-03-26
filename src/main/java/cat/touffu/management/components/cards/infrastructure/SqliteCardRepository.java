package cat.touffu.management.components.cards.infrastructure;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqliteCardRepository implements CardRepository {
    private static final CardRepository INSTANCE = new SqliteCardRepository();
    private Connection sqlite;

    private SqliteCardRepository() {
        try {
            this.sqlite = SqliteJdbc.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CardRepository getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void save(Card card) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id from card where id = ?");
            statement.setString(1, card.id().value());
            ResultSet resultSet = statement.executeQuery();
            boolean idExists = resultSet.next();
            if (idExists) {
                update(card);
            } else {
                add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Card card) {
        try {
            PreparedStatement statement = sqlite.prepareStatement(
                    "insert into card(id, title, list) VALUES (?, ?, ?)"
            );
            statement.setString(1, card.id().value());
            statement.setString(2, card.title());
            statement.setString(3, card.listId().value());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {
        throw new NotImplementedException("update");
    }

    @Override
    public Card findById(CardId cardId) {
        throw new NotImplementedException("find by id");
    }

    @Override
    public List<Card> findAll() {
        throw new NotImplementedException("find all");
    }

    @Override
    public void remove(Card item) {
        throw new NotImplementedException("remove");
    }
}
