module touffu.management {
    requires org.apache.commons.lang3;
    requires java.sql;
    requires info.picocli;
    requires org.json;
    requires cdi.api;

    requires javax.inject;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens cat.touffu.management to javafx.fxml;
    exports cat.touffu.management;
}