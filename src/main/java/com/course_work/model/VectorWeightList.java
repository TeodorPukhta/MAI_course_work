package com.course_work.model;

import java.util.ArrayList;
import java.util.List;

public class VectorWeightList {
    private List<VectorWeight> weights;
    private double weightSum;

    public VectorWeightList(int amount) {
        weights = new ArrayList<>();
        weightSum=0;
//        for (int i = 0; i < amount; i++) {
//            weights.add(new VectorWeight(0));
//        }
    }

    public List<VectorWeight> getWeights() {
        return weights;
    }

    public void setWeights(List<VectorWeight> weights) {
        this.weights = weights;
    }

    public double getWeightSum() {
        return weightSum;
    }

    public void setWeightSum() {
        weights.forEach(w -> weightSum+=w.getWeight());
    }

    @Override
    public String toString() {
        return "Власні вектора: " + weights;
    }
//    public String toString() {
//        return "VectorWeightList{" +
//                "weights=" + weights +
//                ", weightSum=" + weightSum +
//                '}';
//    }
}
