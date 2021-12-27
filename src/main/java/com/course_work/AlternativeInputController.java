package com.course_work;

import com.course_work.util.Parser;
import com.course_work.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlternativeInputController {
    AlternativeList alternativeList;
    CriteriaList criteriaList;
    @FXML
    private TextField alternativeAmountInput;

    @FXML
    private Label alternativeInfoLabel;

    @FXML
    private TableColumn<Alternative, String> alternativeNameColumn;

    @FXML
    private TableView<Alternative> alternativeTable;

    @FXML
    private Button nextStepButton;

    @FXML
    private Button submitAlternativeButton;

    @FXML
    void checkInputField(InputMethodEvent event) {

    }

    public void initData(CriteriaList criteriaList){
            this.criteriaList = criteriaList;
    }
    @FXML
    void clickSubmitAlternativeAmountButton(ActionEvent event) {
        String s = String.valueOf(alternativeAmountInput.getCharacters());
        alternativeList = new AlternativeList(Integer.parseInt(s));
        alternativeTable.setItems(alternativeList.getObservableList());
        alternativeNameColumn.setCellValueFactory(new PropertyValueFactory<Alternative, String>("name"));
        alternativeTable.getColumns().clear();
        alternativeTable.getColumns().add(alternativeNameColumn);
        alternativeTable.setEditable(true);
        alternativeNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    void goToNextScene(ActionEvent event) throws IOException {
        Alternative alternative = new Alternative();
        for (int i = 0; i < alternativeTable.getItems().size(); i++) {
            alternative = alternativeTable.getItems().get(i);
            alternativeList.getAlternativeList().get(i).setName(alternative.getName());
        }
        System.out.println(alternativeList.getAlternativeList().toString());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("comparator-scene.fxml"));
        Parent parent = loader.load();
        Scene comparatorScene = new Scene(parent);
        ComparatorController controller = loader.getController();
        List<VectorWeightList> list = new ArrayList<>();
        Parser parser = new Parser(alternativeList,criteriaList,list);
        controller.initData(parser,"Попарне порівняння критеріїв");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(comparatorScene);
        window.show();
    }

    @FXML
    void updateDataInColumn(TableColumn.CellEditEvent editEvent) {
        Alternative alternative = alternativeTable.getSelectionModel().getSelectedItem();
        alternative.setName(editEvent.getNewValue().toString());
    }

}
