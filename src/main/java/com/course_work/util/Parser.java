package com.course_work.util;

import com.course_work.model.AlternativeList;
import com.course_work.model.CriteriaList;
import com.course_work.model.VectorWeightList;

import java.util.List;

public class Parser {
    AlternativeList alternativeList;
    CriteriaList criteriaList;
    List<VectorWeightList> weightList;
    public Parser(AlternativeList alternativeList, CriteriaList criteriaList) {
        this.alternativeList = alternativeList;
        this.criteriaList = criteriaList;
    }

    public Parser(AlternativeList alternativeList, CriteriaList criteriaList, List<VectorWeightList> weightList) {
        this.alternativeList = alternativeList;
        this.criteriaList = criteriaList;
        this.weightList = weightList;
    }

    public AlternativeList getAlternativeList() {
        return alternativeList;
    }

    public CriteriaList getCriteriaList() {
        return criteriaList;
    }

    public List<VectorWeightList> getWeightList() {
        return weightList;
    }
}
