package com.course_work.model;

import java.text.DecimalFormat;

public class VectorWeight {
    double weight;

    public VectorWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
       // DecimalFormat df = new DecimalFormat("0.00");
        return "" + String.format("%.3f",weight);
    }
}
