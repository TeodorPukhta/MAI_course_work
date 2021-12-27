package com.course_work;

import com.course_work.util.AdditionalFunctions;
import com.course_work.util.Parser;
import com.course_work.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;

import java.util.*;

public class ResultController {

    @FXML
    private Label bestChoiceLabel;

    @FXML
    private Button exitButton;

    @FXML
    private TableView infoTable;

    @FXML
    private Label resultLabel;

    @FXML
    private Label tableNameLabel;

    CriteriaList criteriaList;
    AlternativeList alternativeList;
    List<VectorWeightList> weightList;

    public void initData(Parser parser) {
        this.alternativeList = parser.getAlternativeList();
        this.criteriaList = parser.getCriteriaList();
        weightList = parser.getWeightList();
        calculateResult();
        List<TableColumn<Map, String>> columns = new ArrayList<>();
        columns.add(new TableColumn<>("Критерій"));
        criteriaList.getCriteriaList().add(new Criteria());
        criteriaList.getCriteriaList().get(criteriaList.getCriteriaList().size() - 1).setName("Criteria");

        columns.get(0).setCellValueFactory(new MapValueFactory(criteriaList.getCriteriaList().get(criteriaList.getCriteriaList().size() - 1).getName()));
        columns.add(new TableColumn<>("Ваги критеріїв"));
        columns.get(1).setCellValueFactory(new MapValueFactory(criteriaList.getCriteriaList().get(0).getName()));

        for (int i = 0; i < alternativeList.getAlternativeList().size(); i++) {
            columns.add(new TableColumn<>(alternativeList.getAlternativeList().get(i).getName()));
            columns.get(i + 2).setCellValueFactory(new MapValueFactory(alternativeList.getAlternativeList().get(i).getName()));
            columns.get(i).setSortable(false);
        }

        infoTable.getItems().addAll(generateDataInMap());
        infoTable.getColumns().setAll(columns);
        infoTable.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    infoTable.getColumns().setAll(columns);
                    this.suspended = false;
                }
            }
        });
    }

    private void calculateResult() {
        double[] result = new double[alternativeList.getAlternativeList().size()];

        for (int i = 0; i < weightList.get(1).getWeights().size(); i++) { // column
            for (int j = 1; j < weightList.size(); j++) {  // row
                result[i] += weightList.get(0).getWeights().get(j-1).getWeight() * weightList.get(j).getWeights().get(i).getWeight();
            }
        }

        int min = AdditionalFunctions.getIndexOfMin(result);
        int max = AdditionalFunctions.getIndexOfMax(result);

        bestChoiceLabel.setText("Найкращий варіант: " + alternativeList.getAlternativeList().get(max).getName() + ". Найгірший: " + alternativeList.getAlternativeList().get(min).getName());
        resultLabel.setText("Зважені середні: " + AdditionalFunctions.setDoubleFormatInArray(result));

    }

    private ObservableList<Map> generateDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0; i < criteriaList.getCriteriaList().size() - 1; i++) {
            Map<String, String> dataRow = new HashMap<>();
            dataRow.put(criteriaList.getCriteriaList().get(criteriaList.getCriteriaList().size() - 1).getName(), criteriaList.getCriteriaList().get(i).getName());
            dataRow.put(criteriaList.getCriteriaList().get(0).getName(), weightList.get(0).getWeights().get(i).toString());

            for (int j = 0; j < alternativeList.getAlternativeList().size(); j++) {
                dataRow.put(alternativeList.getAlternativeList().get(j).getName(), weightList.get(i + 1).getWeights().get(j).toString());
            }
            allData.add(dataRow);
        }


        return allData;
    }


    @FXML
    void clickToCloseProgram(ActionEvent event) {
        ((Stage) exitButton.getScene().getWindow()).close();
    }

}
