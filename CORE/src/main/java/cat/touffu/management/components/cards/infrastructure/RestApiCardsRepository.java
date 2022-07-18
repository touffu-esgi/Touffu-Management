package cat.touffu.management.components.cards.infrastructure;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.components.projects.infrastructure.JsonAdapters.ListOfProjectsJsonAdapter;
import cat.touffu.management.components.projects.infrastructure.JsonAdapters.ProjectJsonAdapter;
import cat.touffu.management.kernel.database.RestApi;
import org.apache.commons.lang3.NotImplementedException;
import cat.touffu.management.components.cards.infrastructure.JsonAdapters.*;

import java.util.List;

public class RestApiCardsRepository implements CardRepository {

    private final static RestApiCardsRepository INSTANCE = new RestApiCardsRepository();
    private final static RestApi API = RestApi.instance();
    private final static String base = "/cards";

    private RestApiCardsRepository() {
    }

    public static RestApiCardsRepository getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Card> findInProject(String projectId) {
        var route = base + "/project/" + projectId;
        return API.get(route, new CardsJsonAdapter());
    }

    @Override
    public boolean exists(CardId cardId) {
        return false;
    }

    @Override
    public void save(Card card) {
        this.update(card);
    }

    @Override
    public void add(Card card) {
        this.update(card);
    }

    @Override
    public void update(Card card) {
        var route = base + "/" + card.id().value();
        API.patch(route, new CardJsonAdapter(), card);
    }

    @Override
    public Card findById(CardId cardId) {
        var route = base + "/" + cardId.value();
        return API.get(route, new CardJsonAdapter());
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public void remove(Card item) {

    }
}
