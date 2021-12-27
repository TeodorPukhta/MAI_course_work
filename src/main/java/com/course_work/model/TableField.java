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
    public double getDoubleValue(){
        return Double.parseDouble(getValue());
    }
    public TableField() {
      //  Random r = new Random();
          valueProperty().set("1.0");
      //  valueProperty().set(String.valueOf(r.nextInt(100)/100.0));
    }

    public void setValue(String v) {
        valueProperty().set(v);
    }
    public StringProperty valueProperty() {
        if (value == null) value = new SimpleStringProperty(this, " ");
        return value;
    }
//    public void setValue(List<String> list) {
//
//        this.value = calculateVector(list);
//    }

//    public double calculateVector(List<Double> list){
//        double sum=1;
//        for (Double l :
//             list) {
//            sum *= Double.parseDouble(String.valueOf(list));
//        }
//        sum = sum*(1.0/list.size());
//        return sum;
//    }

    @Override
    public String toString() {
        return "TableField{" +
                "value=" + getValue() +
                '}';
    }
}
