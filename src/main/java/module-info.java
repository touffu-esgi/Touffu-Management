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
}