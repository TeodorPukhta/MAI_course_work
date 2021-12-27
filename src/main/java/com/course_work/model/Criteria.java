package com.course_work.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Criteria {
    private StringProperty name;
    private double value;

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String value) {
        nameProperty().set(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public StringProperty nameProperty() {
        if (name == null) name = new SimpleStringProperty(this, " ");
        return name;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "name=" + getName() +
                '}';
    }
}
