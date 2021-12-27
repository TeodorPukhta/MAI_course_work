package com.course_work.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CriteriaList {
    private List<Criteria> criteriaList;
    private ObservableList<Criteria> observableList;

    public CriteriaList(int amount) {
        criteriaList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            criteriaList.add(new Criteria());
            criteriaList.get(i).setName("K"+i);
        }
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public ObservableList<Criteria> getObservableList(){
        observableList = FXCollections.observableList(criteriaList);
        return observableList;
    }

}
