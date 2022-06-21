package cat.touffu.management.components.projects.infrastructure;

import cat.touffu.management.components.projects.adapter.CardListIdsToStringAdapter;
import cat.touffu.management.components.projects.adapter.StringToCardListIdsAdapter;
import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.database.SqliteJdbc;
import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONArray;
import org.json.JSONString;
import org.json.JSONStringer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SqliteProjectRepository implements ProjectRepository {
    private static final ProjectRepository INSTANCE = new SqliteProjectRepository();
    private Connection sqlite;

    private final StringToCardListIdsAdapter stringToCardListIdsAdapter = new StringToCardListIdsAdapter();
    private final CardListIdsToStringAdapter cardListIdsToStringAdapter = new CardListIdsToStringAdapter();

    private SqliteProjectRepository() {
        try {
            this.sqlite = SqliteJdbc.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProjectRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean exists(ProjectId id) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id from project where id = ?");
            statement.setString(1, id.value());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(Project project) {
        if (this.exists(project.id())) {
            update(project);
        } else {
            add(project);
        }
    }

    @Override
    public void add(Project project) {
        try {
            PreparedStatement statement = sqlite.prepareStatement(
                    "insert into project(id, title, cards) VALUES (?, ?, ?)"
            );
            statement.setString(1, project.id().value());
            statement.setString(2, project.title());
            statement.setString(3, this.cardsToJsonString(project.cards()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project project) {
        try {
            PreparedStatement statement = sqlite.prepareStatement(
                    "update project set title = ?, cards = ? where id = ?"
            );
            statement.setString(1, project.title());
            statement.setString(2, this.cardsToJsonString(project.cards()));
            statement.setString(3, project.id().value());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String cardsToJsonString(Set<CardId> cards) {
        return new JSONArray(
                cards.stream()
                        .map(CardId::value)
                        .collect(Collectors.toList()))
                .toString();
    }

    @Override
    public Project findById(ProjectId projectId) {
        try {
            PreparedStatement statement = sqlite.prepareStatement("select id, title, cards from project where id = ?");
            statement.setString(1, projectId.value());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return null;
            }
            return Project.of(
                    ProjectId.of(resultSet.getString("id")),
                    resultSet.getString("title"),
                    new JSONArray(resultSet.getString("cards"))
                            .toList().stream()
                            .map(cardId -> CardId.of((String) cardId))
                            .collect(Collectors.toSet())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        try {
            Statement statement = sqlite.createStatement();
            ResultSet result = statement.executeQuery("select id, title, cards from project");
            while (result.next()) {
                projects.add(new Project(
                        ProjectId.of(result.getString("id")),
                        result.getString("title"),
                        new JSONArray(result.getString("cards")).toList().stream()
                                .map(id -> CardId.of((String) id))
                                .collect(Collectors.toSet())
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
