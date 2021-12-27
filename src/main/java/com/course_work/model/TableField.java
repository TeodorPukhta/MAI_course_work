package com.course_work.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;
import java.util.Random;

public class TableField {
    private StringProperty value;

    public String getValue() {
        return valueProperty().get();
    }
    public String getOneValue(){
        valueProperty().set("1.0");
        return valueProperty().get();
    }
    public double getDoubleValue(){
        return Double.parseDouble(getValue());
    }

    public TableField() {
          valueProperty().set(" ");
    }

    public void setValue(String v) {
        valueProperty().set(v);
    }

    public StringProperty valueProperty() {
        if (value == null) value = new SimpleStringProperty(this, " ");
        return value;
    }

    @Override
    public String toString() {
        return "TableField{" +
                "value=" + getValue() +
                '}';
    }
}
