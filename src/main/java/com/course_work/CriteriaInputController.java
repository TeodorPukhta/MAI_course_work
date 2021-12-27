package com.course_work;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.course_work.model.Criteria;
import com.course_work.model.CriteriaList;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CriteriaInputController {
    CriteriaList criteriaList;

    @FXML
    private TextField criteriaAmountInput;

    @FXML
    private Label criteriaInfoLabel;

    @FXML
    private TableColumn<Criteria, String> criteriaNameColumn;

    @FXML
    private TableView<Criteria> criteriaTable;

    @FXML
    private Button submitCriteriaButton;

    @FXML
    private Button nextStepButton;

    @FXML
    void clickSubmitCriteriaAmountButton(ActionEvent event){
   //     criteriaNameColumn.editableProperty().set(true);
        String s = String.valueOf(criteriaAmountInput.getCharacters());
        criteriaList = new CriteriaList(Integer.parseInt(s));
    //    criteriaTable = new TableView<Criteria>(criteriaList.getObservableList());
        criteriaTable.setItems(criteriaList.getObservableList());
     //   criteriaNameColumn = new TableColumn<Criteria, String>("Назва");
        criteriaNameColumn.setCellValueFactory(new PropertyValueFactory<Criteria,String>("name"));
        criteriaTable.getColumns().clear();
        criteriaTable.getColumns().add(criteriaNameColumn);
        criteriaTable.setEditable(true);
        criteriaNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    void updateDataInColumn(TableColumn.CellEditEvent editEvent) {
        Criteria criteria = criteriaTable.getSelectionModel().getSelectedItem();
        criteria.setName(editEvent.getNewValue().toString());
       // System.out.println(editEvent.getTableColumn());
      //  criteriaList.getCriteriaList().get(editEvent.getTableColumn().getId())
      //  criteriaNameColumn.setEditable(true);
     //   criteriaNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

//    @FXML
//    void saveDataToList(TableColumn.CellEditEvent<Criteria, String> event) {
//        criteriaNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Criteria, String>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<Criteria, String> criteriaStringCellEditEvent) {
//                Criteria cr = criteriaStringCellEditEvent.getRowValue();
//                cr.setName(criteriaStringCellEditEvent.getNewValue());
//            }
//        });
//    }

    @FXML
    void checkInputField(InputMethodEvent event) {
    }

    @FXML
    void goToNextScene(ActionEvent event) throws IOException {
    //    Criteria criteria;
        for (int i = 0; i < criteriaTable.getItems().size(); i++) {
            Criteria criteria = criteriaTable.getItems().get(i);
            criteriaList.getCriteriaList().get(i).setName(criteria.getName());
        }
        System.out.println(criteriaList.getCriteriaList().toString());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("alternative-scene.fxml"));
        Parent parent = loader.load();
        Scene alternativeInputScene = new Scene(parent);
        AlternativeInputController controller = loader.getController();

        controller.initData(criteriaList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alternativeInputScene);
        window.show();
    }

}