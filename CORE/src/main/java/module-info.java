module touffu.management {
    requires org.apache.commons.lang3;
    requires java.sql;
    requires info.picocli;
    requires org.json;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens cat.touffu.management.javafx to javafx.fxml, javafx.graphics;
    exports cat.touffu.management;
    exports cat.touffu.management.javafx;
    exports cat.touffu.management.javafx.projects;
    opens cat.touffu.management.javafx.projects to javafx.fxml, javafx.graphics;
    exports cat.touffu.management.javafx.board;
    opens cat.touffu.management.javafx.board to javafx.fxml, javafx.graphics;
    exports cat.touffu.management.javafx.card;
    opens cat.touffu.management.javafx.card to javafx.fxml, javafx.graphics;
    exports cat.touffu.management.javafx.kanban;
    opens cat.touffu.management.javafx.kanban to javafx.fxml, javafx.graphics;
    exports cat.touffu.management.javafx.plugins;
    opens cat.touffu.management.javafx.plugins to javafx.fxml, javafx.graphics;

    exports cat.touffu.management.plugin;
}