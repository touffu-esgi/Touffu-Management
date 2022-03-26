package cat.touffu.management.components.cards.infrastructure;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.Connection;
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
        throw new NotImplementedException("save");
    }

    @Override
    public void add(Card card) {
        throw new NotImplementedException("add");
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
