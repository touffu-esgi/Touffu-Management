package cat.touffu.management.kernel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteJdbc {
    private static SqliteJdbc INSTANCE;
    private Connection connection;
    String url = "jdbc:sqlite:db.sqlite";

    private SqliteJdbc() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static SqliteJdbc getInstance() throws SQLException {
        if (INSTANCE == null) {
            INSTANCE = new SqliteJdbc();
        } else if (INSTANCE.getConnection().isClosed()) {
            INSTANCE = new SqliteJdbc();
        }
        return INSTANCE;
    }
}
