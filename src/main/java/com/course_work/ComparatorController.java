package com.course_work;

import com.course_work.util.Parser;
import com.course_work.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.*;

public class ComparatorController {
    CriteriaList criteriaList;
    AlternativeList alternativeList;
    List<Row> rows;
    List<VectorWeightList> weightList;
    @FXML
    private Button checkButton;

    @FXML
    private Label criteriaWeightLabel;

    @FXML
    private Label weightSumLabel;

    @FXML
    private Label rowWeightLabel;

    @FXML
    private Button nextStepButton;

    @FXML
    private Label tableNameLabel;

    @FXML
    private TableView comparatorTable;

    public void initData(Parser parser, String label){
        this.alternativeList = parser.getAlternativeList();
        this.criteriaList = parser.getCriteriaList();
        weightList = parser.getWeightList();
        tableNameLabel.setText(label);
        List<TableColumn<Map,String>> columns = new ArrayList<>();
        if(tableNameLabel.getText().contains("критеріїв")){
            for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
                columns.add(new TableColumn<>(criteriaList.getCriteriaList().get(i).getName()));
                columns.get(i).setCellValueFactory(new MapValueFactory(criteriaList.getCriteriaList().get(i).getName()));
                columns.get(i).setSortable(false);
            }
            comparatorTable.getItems().addAll(generateDataInMap("criteria"));
        }
        else{
            for (int i = 0; i < alternativeList.getAlternativeList().size(); i++) {
                columns.add(new TableColumn<>(alternativeList.getAlternativeList().get(i).getName()));
                columns.get(i).setCellValueFactory(new MapValueFactory(alternativeList.getAlternativeList().get(i).getName()));
                columns.get(i).setSortable(false);
            }
            comparatorTable.getItems().addAll(generateDataInMap("alternative"));
        }
        comparatorTable.setEditable(true);
        comparatorTable.getSelectionModel().setCellSelectionEnabled(true);
        comparatorTable.getColumns().setAll(columns);
        comparatorTable.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    comparatorTable.getColumns().setAll(columns);
                    this.suspended = false;
                }
            }
        });

        Callback<TableColumn<Map, String>, TableCell<Map, String>>
                cellFactoryForMap = new Callback<TableColumn<Map, String>,
                TableCell<Map, String>>() {
            @Override
            public TableCell call(TableColumn p) {
                return new TextFieldTableCell(new StringConverter() {
                    @Override
                    public String toString(Object t) {
                        return t.toString();
                    }
                    @Override
                    public Object fromString(String string) {
                        return string;
                    }
                });
            }
        };
        for (TableColumn<Map, String> column : columns) {
            column.setCellFactory(cellFactoryForMap);
            column.setOnEditCommit((TableColumn.CellEditEvent<Map, String> t) -> {
                ((Map<String, String>) t.getTableView()
                        .getItems()
                        .get(t.getTablePosition().getRow()))
                        .put(t.getTableColumn().getText(), t.getNewValue());
            });
        }
    }

    private ObservableList<Map> generateDataInMap(String type) {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        if(type.equals("criteria")) {
            for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
                Map<String, String> dataRow = new HashMap<>();

                for (int j = 0; j < criteriaList.getCriteriaList().size(); j++) {
                    if(i==j)
                    dataRow.put(criteriaList.getCriteriaList().get(j).getName(), new TableField().getOneValue());
                    else
                    dataRow.put(criteriaList.getCriteriaList().get(j).getName(), new TableField().getValue());
                }
                allData.add(dataRow);
            }
        }
        else{
            for (int i = 0; i < alternativeList.getAlternativeList().size(); i++) {
                Map<String, String> dataRow = new HashMap<>();

                for (int j = 0; j < alternativeList.getAlternativeList().size(); j++) {
                    if(i==j)
                    dataRow.put(alternativeList.getAlternativeList().get(j).getName(), new TableField().getOneValue());
                    else
                    dataRow.put(alternativeList.getAlternativeList().get(j).getName(), new TableField().getValue());
                }
                allData.add(dataRow);
            }
        }
        return allData;
    }
    @FXML
    void checkCriteriaWeight(ActionEvent event) {
        if(rows == null)
            rows = new ArrayList<>();
        for (int i = 0; i < comparatorTable.getColumns().size(); i++) {
            rows.add(new Row(comparatorTable.getColumns().size()));
            if(tableNameLabel.getText().contains("критеріїв")) {
                for (int j = 0; j < comparatorTable.getColumns().size(); j++) {
                    HashMap hashMap = (HashMap) comparatorTable.getItems().get(i);
                    rows.get(i).getVectorList().get(j).setValue(hashMap.get(criteriaList.getCriteriaList().get(j).getName()).toString());
                }
            }
            else{
                for (int j = 0; j < comparatorTable.getColumns().size(); j++) {
                    HashMap hashMap = (HashMap) comparatorTable.getItems().get(i);
                    rows.get(i).getVectorList().get(j).setValue(hashMap.get(alternativeList.getAlternativeList().get(j).getName()).toString());
                }
            }

        }
        for (Row r:
             rows) {
            r.setOwnVectorSum();
        }

        weightList.add(new VectorWeightList(rows.size()));
        for (int i = 0; i < rows.size(); i++) {

            weightList.get(weightList.size()-1).getWeights().add(rows.get(i).calculateVectorWeight());
        }
        weightList.get(weightList.size()-1).setWeightSum();
        rowWeightLabel.setText(weightList.get(weightList.size()-1).toString());
        weightSumLabel.setText("Сума: "+weightList.get(weightList.size()-1).getWeightSum());
        Row.refreshOwnVectorSum();
    }
    @FXML
    void goToNextScene(ActionEvent event) throws IOException {
        StringBuilder nextTableLabel= new StringBuilder("Попарне порівняння альтернатив\nза критерієм ");
        FXMLLoader loader = new FXMLLoader();
        Parser parser = new Parser(alternativeList,criteriaList,weightList);
        if(tableNameLabel.getText().contains("критеріїв")){
            nextTableLabel.append(criteriaList.getCriteriaList().get(0).getName());
            loader.setLocation(getClass().getResource("comparator-scene.fxml"));
            Parent parent = loader.load();
            Scene comparatorScene = new Scene(parent);
            ComparatorController controller = loader.getController();
            controller.initData(parser, nextTableLabel.toString());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(comparatorScene);
            window.show();
        }
        else if(tableNameLabel.getText().contains(criteriaList.getCriteriaList().get(criteriaList.getCriteriaList().size()-1).getName())) {
            loader.setLocation(getClass().getResource("result-scene.fxml"));
            Parent parent = loader.load();
            Scene comparatorScene = new Scene(parent);
            ResultController resultController = loader.getController();
            resultController.initData(parser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(comparatorScene);
            window.show();
        }
        else {
            loader.setLocation(getClass().getResource("comparator-scene.fxml"));
            Parent parent = loader.load();
            Scene comparatorScene = new Scene(parent);
            ComparatorController controller = loader.getController();
            for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
                if (tableNameLabel.getText().contains(criteriaList.getCriteriaList().get(i).getName()))
                    nextTableLabel.append(criteriaList.getCriteriaList().get(i + 1).getName());
            }
            controller.initData(parser, nextTableLabel.toString());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(comparatorScene);
            window.show();
        }
    }
}