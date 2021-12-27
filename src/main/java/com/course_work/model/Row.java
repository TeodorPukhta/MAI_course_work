package com.course_work.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<TableField> row;
    private double ownVector;
    private static double ownVectorSum=0;

    public Row(int amount) {
        row = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            row.add(new TableField());
        }
        ownVector = 1;
    }

    public List<TableField> getVectorList() {
        return row;
    }

    public ObservableList<TableField> getObservableList(){
        ObservableList<TableField> observableList = FXCollections.observableList(row);
        return observableList;
    }

    public void setVectorList(List<TableField> row) {
        this.row = row;
    }

        public double calculateOwnVector(){
        row.forEach(tableField -> ownVector = ownVector * Double.parseDouble(tableField.getValue()));
        ownVector = Math.pow(ownVector,1.0/ row.size());
        return ownVector;
    }

    public double getOwnVector() {
        return ownVector;
    }


    public double getOwnVectorSum() {
        return ownVectorSum;
    }
    public VectorWeight calculateVectorWeight(){
//        VectorWeight vectorWeightList = new VectorWeightList(tableFieldList.size());
//        for (int i = 0; i < tableFieldList.size(); i++) {
//            vectorWeightList.getWeights().add(new VectorWeight(ownVector/ownVectorSum));
//        }
     //   return vectorWeightList.getWeights();
        return new VectorWeight(ownVector/ownVectorSum);
    }
    public void setOwnVectorSum() {
     //   row.forEach(tableField -> ownVectorSum+= tableField.getDoubleValue());
        ownVectorSum+=ownVector;
      //  ownVectorSum+= ownVectorSum;
    }
    public static void refreshOwnVectorSum(){
        ownVectorSum=0;
    }
    public void printOwnVector(){
        System.out.printf("Own vector: %.2f \n",ownVector);
    }
}
