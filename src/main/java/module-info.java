module com.course_work {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

   opens com.course_work to javafx.fxml;
    opens com.course_work.model to javafx.base;
    exports com.course_work;
}