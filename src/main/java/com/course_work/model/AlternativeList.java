package com.course_work.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class AlternativeList {
    private List<Alternative> alternativeList;
    private ObservableList<Alternative> observableList;

    public AlternativeList(int amount) {
        alternativeList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            alternativeList.add(new Alternative());
           alternativeList.get(i).setName("A"+i);
        }
    }

    public List<Alternative> getAlternativeList() {
        return alternativeList;
    }

    public void setAlternativeList(List<Alternative> alternativeList) {
        this.alternativeList = alternativeList;
    }

    public ObservableList<Alternative> getObservableList(){
        observableList = FXCollections.observableList(alternativeList);
        return observableList;
    }
}
