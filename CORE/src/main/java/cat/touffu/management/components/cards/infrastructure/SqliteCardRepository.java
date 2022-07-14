package cat.touffu.management.components.cards.infrastructure;

import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.adapter.CardStatusToStringAdapter;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.domain.ProjectId;
import cat.touffu.management.kernel.database.SqliteJdbc;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteCardRepository implements CardRepository {
    private final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();

    private static final CardRepository INSTANCE = new SqliteCardRepository();
    private Connection sqlite;
    private final CardStatusToStringAdapter cardStatusToStringAdapter = new CardStatusToStringAdapter();

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
    public boolean exists(CardId cardId) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id from card where id = ?");
            statement.setString(1, cardId.value());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(Card card) {
        if (this.exists(card.id())) {
            update(card);
        } else {
            add(card);
        }
    }

    @Override
    public void add(Card card) {
        try {
            PreparedStatement statement = sqlite.prepareStatement(
                    "insert into card(id, title, project, status) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, card.id().value());
            statement.setString(2, card.title());
            statement.setString(3, card.projectId().value());
            statement.setString(4, cardStatusToStringAdapter.adapt(card.cardStatus()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {
        try {
            PreparedStatement statement = sqlite.prepareStatement(
                    "update card set title = ?, project = ?, status = ? where id = ?"
            );
            statement.setString(1, card.title());
            statement.setString(2, card.projectId().value());
            statement.setString(3, cardStatusToStringAdapter.adapt(card.cardStatus()));
            statement.setString(4, card.id().value());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card findById(CardId cardId) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select title, project, status from card where id = ?");
            statement.setString(1, cardId.value());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;
            return Card.of(
                    cardId,
                    resultSet.getString("title"),
                    ProjectId.of(resultSet.getString("project")),
                    cardStatusFromStringAdapter.adapt(resultSet.getString("status"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Card> findAll() {
        throw new NotImplementedException("find all");
    }

    @Override
    public void remove(Card item) {
        throw new NotImplementedException("remove");
    }

    @Override
    public List<Card> findInProject(String projectId) {
        List<Card> cards = new ArrayList<>();
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id, title, project, status from card where project = ?");
            statement.setString(1, projectId);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                cards.add(Card.of(
                        CardId.of(result.getString("id")),
                        result.getString("title"),
                        ProjectId.of(result.getString("project")),
                        cardStatusFromStringAdapter.adapt(result.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
