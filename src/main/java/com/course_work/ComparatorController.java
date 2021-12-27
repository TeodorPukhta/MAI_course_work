//package com.course_work;
//
//import com.course_work.model.*;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Vector;
//
//public class ComparatorController {
//    CriteriaList criteriaList;
//    AlternativeList alternativeList;
//    Row vectorss;
//    @FXML
//    private Button checkButton;
//
//    @FXML
//    private Label criteriaWeightLabel;
//
//
//    @FXML
//    private Button nextStepButton;
//
//    @FXML
//    private Label tableNameLabel;
//
//    @FXML
//    private TableView<TableField> comparatorTable;
//
////    @FXML
// //   private TableColumn<TableField, String> mainColumn;
//
//    List<TableColumn<TableField,String>> columns = new ArrayList<>();
//
//    public void initData(CriteriaList criteriaList, AlternativeList alternativeList, String label){
//        this.alternativeList = alternativeList;
//        this.criteriaList = criteriaList;
//        tableNameLabel.setText(label);
//        vectorss = new Row(criteriaList.getCriteriaList().size());
//        List<Row> vectors = new ArrayList<>();
//        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
//            vectors.add(new Row(criteriaList.getCriteriaList().size()));
//        }
//      //  mainColumn.setCellValueFactory(new PropertyValueFactory<TableField,String>("value"));
//        List<TableColumn<TableField,String>> columns = new ArrayList<>();
//        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
//            columns.add(new TableColumn<>(criteriaList.getCriteriaList().get(i).getName()));
//            columns.get(i).setCellValueFactory(new PropertyValueFactory<TableField,String>("value"));
//            columns.get(i).setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TableField, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<TableField, String> event) {
//                  TablePosition tb = event.getTablePosition();
//                    TableField v = event.getRowValue();
//                    v.setValue(event.getNewValue());
//                }
//            });
//        }
//      //  mainColumn.getColumns().addAll(columns);
// //       comparatorTable.getColumns().get(0).setVisible(false);
////        for (TableColumn<TableField,String> v:
////                columns) {
////
////        }
//
//
//        comparatorTable.getColumns().addAll(columns);
////        for (int i = 0; i < columns.size(); i++) {
////            comparatorTable.getColumns().add(columns.get(i));
////
////        }
//        columns.get(0).;
//    //    comparatorTable.getItems().add(vectors.get(0).getVectorList().get(0));
//
////        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
////            for (int j = 0; j < criteriaList.getCriteriaList().size(); j++) {
////                comparatorTable.getItems().add(vectors.get(i).getVectorList().get(j));
////            }
////        }
//
//     //   comparatorTable.getColumns();
//   //     comparatorTable.getItems().addAll(vectors.getObservableList());
//      //  comparatorTable.setItems(vectors.getObservableList());
//       // comparatorTable.getColumns().clear();
//        comparatorTable.setEditable(true);
//        for (TableColumn<TableField,String> v:
//             columns) {
//            v.setCellFactory(TextFieldTableCell.forTableColumn());
//        }
//
////        comparatorTable.setItems(vectors.getObservableList());
////        for (int i = 1; i < criteriaList.getCriteriaList().size()+1; i++) {
////            columns.add(new TableColumn(criteriaList.getCriteriaList().get(i-1).getName()));
////            columns.get(i-1).setCellFactory(TextFieldTableCell.forTableColumn());
////            columns.get(i-1).setOnEditCommit((TableColumn.CellEditEvent<TableField, String> t) -> {
////                TableField acct = t.getTableView().getItems().get(t.getTablePosition().getRow());
////                vectors.getVectorList().add(acct);
////            });
////        }
////        comparatorTable.getColumns().addAll(columns);
////        comparatorTable.setEditable(true);
////        for (TableColumn tb: columns
////        ) {
////            tb.setCellFactory(TextFieldTableCell.forTableColumn());
////        }
//
//
//
//
//
//    }
// //   @FXML
// //   void updateDataInColumn(TableColumn.CellEditEvent editEvent) {
////        TableField vectorField = comparatorTable.getSelectionModel().getSelectedItem();
////        vectorField.setValue(editEvent.getNewValue().toString());
//  //  }
//
//    @FXML
//    void checkCriteriaWeight(ActionEvent event) {
//            for (int j = 0; j < comparatorTable.getItems().size(); j++) {
//                TableField vectorField = new TableField();
//                vectorField.setValue(comparatorTable.getItems().get(j).toString());
//                System.out.println(vectorField.getValue());
//            }
//    }
//    @FXML
//    void goToNextScene(ActionEvent event) {
//
//    }
//}

////////////////////////////////////////////////////////////


package com.course_work;

import com.course_work.Util.Parser;
import com.course_work.model.*;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.*;

public class ComparatorController {
    CriteriaList criteriaList;
    AlternativeList alternativeList;
    List<Row> rows;
  //  Row weight;
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

//    @FXML
    //   private TableColumn<TableField, String> mainColumn;

  //  List<TableColumn<TableField,String>> columns = new ArrayList<>();

    public void initData(Parser parser, String label){
        this.alternativeList = parser.getAlternativeList();
        this.criteriaList = parser.getCriteriaList();
        weightList = parser.getWeightList();
        tableNameLabel.setText(label);

        if(tableNameLabel.getText().contains("критеріїв")){

        }
   //     vectorss = new Row(criteriaList.getCriteriaList().size());
//        List<Row> vectors = new ArrayList<>();
//        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
//            vectors.add(new Row(criteriaList.getCriteriaList().size()));
//        }
        List<TableColumn<Map,String>> columns = new ArrayList<>();
        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
            columns.add(new TableColumn<>(criteriaList.getCriteriaList().get(i).getName()));
            columns.get(i).setCellValueFactory(new MapValueFactory(criteriaList.getCriteriaList().get(i).getName()));
        }
        comparatorTable.getItems().addAll((generateDataInMap()));
     //   comparatorTable = new TableView<>(generateDataInMap(criteriaList.getCriteriaList()));
        comparatorTable.setEditable(true);
        comparatorTable.getSelectionModel().setCellSelectionEnabled(true);
        comparatorTable.getColumns().setAll(columns);

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
//        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
//            columns.add(new TableColumn<>(criteriaList.getCriteriaList().get(i).getName()));
       //     columns.get(i).setCellValueFactory(new PropertyValueFactory<TableField,String>("value"));
//            columns.get(i).setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TableField, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<TableField, String> event) {
//                    TablePosition tb = event.getTablePosition();
//                    TableField v = event.getRowValue();
//                    v.setValue(event.getNewValue());
//                }
//            });
//        }


//        comparatorTable.getColumns().addAll(columns);
//        comparatorTable.setEditable(true);
//        for (TableColumn<TableField,String> v:
//                columns) {
//            v.setCellFactory(TextFieldTableCell.forTableColumn());
//        }





    }

    private ObservableList<Map> generateDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
            Map<String, String> dataRow = new HashMap<>();

            for (int j = 0; j < criteriaList.getCriteriaList().size(); j++) {
                dataRow.put(criteriaList.getCriteriaList().get(j).getName(),new TableField().getValue());
            }
//            String value1 = "A" + i;
//            String value2 = "B" + i;

//            dataRow.put(Column1MapKey, value1);
//            dataRow.put(Column2MapKey, value2);

            allData.add(dataRow);
        }
        return allData;
    }
    @FXML
    void checkCriteriaWeight(ActionEvent event) {
        if(rows == null)
            rows = new ArrayList<>();
        for (int i = 0; i < comparatorTable.getColumns().size(); i++) {
            rows.add(new Row(comparatorTable.getColumns().size()));
            TableField tableField = new TableField();
          //  tableField.setValue(comparatorTable.getItems().get(j).toString());
            for (int j = 0; j < comparatorTable.getColumns().size(); j++) {
                HashMap hashMap = (HashMap) comparatorTable.getItems().get(i);
                rows.get(i).getVectorList().get(j).setValue(hashMap.get(criteriaList.getCriteriaList().get(j).getName()).toString());
              //  tableField.setValue(hashMap.get(criteriaList.getCriteriaList().get(j).getName()).toString());
              //  System.out.println(tableField.getValue());
            }
        //    System.out.println();

            System.out.printf("%.2f \n", rows.get(i).calculateOwnVector());
        //    rows.get(i).setOwnVectorSum();

        }
        for (Row r:
             rows) {
            r.setOwnVectorSum();
        }
      //  rows.get(0).setOwnVectorSum();
        System.out.println(rows.get(0).getOwnVectorSum());

        weightList.add(new VectorWeightList(rows.size()));
      //  weight = new Row(comparatorTable.getColumns().size());
        for (int i = 0; i < rows.size(); i++) {

            weightList.get(weightList.size()-1).getWeights().add(rows.get(i).calculateVectorWeight());
        }
        System.out.println("--------------");
        weightList.get(weightList.size()-1).setWeightSum();
        System.out.println(weightList.toString() + weightList.get(weightList.size()-1).getWeightSum());
        rowWeightLabel.setText(weightList.get(weightList.size()-1).toString());
        weightSumLabel.setText("Сума: "+weightList.get(weightList.size()-1).getWeightSum());
        System.out.println("--------------");
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
//            loader.setLocation(getClass().getResource("result-scene.fxml"));
//            Parent parent = loader.load();
//            Scene comparatorScene = new Scene(parent);
//            ComparatorController controller = loader.getController();
//            nextTableLabel = new StringBuilder("Результати розв'язання задачі");
//            controller.initData(parser, nextTableLabel.toString());
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(comparatorScene);
//            window.show();
            System.out.println(weightList.toString());
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





/////////////////////////////////////////////////////////////////////////////////////////////////
//package com.course_work;
//
//import com.course_work.model.*;
//import javafx.beans.binding.Bindings;
//import javafx.collections.FXCollections;
//import javafx.collections.MapChangeListener;
//import javafx.collections.ObservableList;
//import javafx.collections.ObservableMap;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.MapValueFactory;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.util.Callback;
//import javafx.util.StringConverter;
//
//import java.util.*;
//
//public class ComparatorController {
//    CriteriaList criteriaList;
//    AlternativeList alternativeList;
//    //   Row vectorss;
//    @FXML
//    private Button checkButton;
//
//    @FXML
//    private Label criteriaWeightLabel;
//
//
//    @FXML
//    private Button nextStepButton;
//
//    @FXML
//    private Label tableNameLabel;
//
//    @FXML
//    private TableView comparatorTable;
//
////    @FXML
//    //   private TableColumn<TableField, String> mainColumn;
//
//    List<TableColumn<TableField,String>> columns = new ArrayList<>();
//
//    public void initData(CriteriaList criteriaList, AlternativeList alternativeList, String label){
//        this.alternativeList = alternativeList;
//        this.criteriaList = criteriaList;
//        tableNameLabel.setText(label);
//        ObservableList<MapEntry<String, String>> entries = FXCollections.observableArrayList();
//        List<TableColumn<MapEntry<String,String>,String>> columns = new ArrayList<>();
//        for (int i = 0; i < criteriaList.getCriteriaList().size(); i++) {
//            columns.add(new TableColumn<>(criteriaList.getCriteriaList().get(i).getName()));
//            columns.get(i).setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue().getValue()));
//        }
//        comparatorTable.getItems().addAll((generateDataInMap(entries,criteriaList.getCriteriaList())));
//        //   comparatorTable = new TableView<>(generateDataInMap(criteriaList.getCriteriaList()));
//        comparatorTable.setEditable(true);
//        comparatorTable.getSelectionModel().setCellSelectionEnabled(true);
//        comparatorTable.getColumns().setAll(columns);
//
//        Callback<TableColumn<MapEntry<String,String>,String>, TableCell<MapEntry<String,String>,String>>
//                cellFactoryForMap = new Callback<TableColumn<MapEntry<String,String>,String>,
//                TableCell<MapEntry<String,String>,String>>() {
//            @Override
//            public TableCell call(TableColumn p) {
//                return new TextFieldTableCell(new StringConverter() {
//                    @Override
//                    public String toString(Object t) {
//                        return t.toString();
//                    }
//                    @Override
//                    public Object fromString(String string) {
//                        return string;
//                    }
//                });
//            }
//        };
//        for (int i = 0; i < columns.size(); i++) {
//            columns.get(i).setCellFactory(cellFactoryForMap);
////            columns.get(i).setOnEditCommit((TableColumn.CellEditEvent<Map<String, String>, String> t) -> {
////                ((Map<String, String>) t.getTableView().getItems().get(t.getTablePosition().getRow())).put(Column1MapKey, t.getNewValue());
////            });
//        }
//    }
//
//    private ObservableList<MapEntry<String,String>> generateDataInMap(ObservableList<MapEntry<String, String>> entries, List<Criteria> criteria) {
//        ObservableList<MapEntry<String,String>> allData = FXCollections.observableArrayList();
//        ObservableMap <String, String> map = FXCollections.observableHashMap();
//
//        map.addListener((MapChangeListener.Change<? extends String, ? extends String> change) -> {
//            boolean removed = change.wasRemoved();
//            if (removed != change.wasAdded()) {
//                if (removed) {
//                    // no put for existing key
//                    // remove pair completely
//                    entries.remove(new MapEntry<>(change.getKey(), (String) null));
//                } else {
//                    // add new entry
//                    entries.add(new MapEntry<>(change.getKey(), change.getValueAdded()));
//                }
//            } else {
//                // replace existing entry
//                MapEntry<String, String> entry = new MapEntry<>(change.getKey(), change.getValueAdded());
//
//                int index = entries.indexOf(entry);
//                entries.set(index, entry);
//            }
//        });
//        for (int i = 0; i < criteria.size(); i++) {
//            Map<String, String> dataRow = new HashMap<>();
//
//            for (int j = 0; j < criteria.size(); j++) {
//                map.put(criteria.get(j).getName(),new TableField().getValue());
//            }
//
//           // map.put(dataRow);
//        }
//        return entries;
//    }
//    @FXML
//    void checkCriteriaWeight(ActionEvent event) {
//        for (int i = 0; i < comparatorTable.getItems().size(); i++) {
////            TableField vectorField = new TableField();
////            vectorField.setValue(comparatorTable.getItems().get(j).toString());
////            System.out.println(vectorField.getValue());
//            System.out.println(comparatorTable.getItems().get(i).toString());
//        }
//    }
//    @FXML
//    void goToNextScene(ActionEvent event) {
//
//    }
//}