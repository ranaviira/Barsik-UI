package com.example.ui.controllers;

import com.example.ui.entity.Contract;
import com.example.ui.exception.ServerConnectionError;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
@FxmlView("/com/example/ui/fxml/barsik-view.fxml")
public class UserInterfaceController implements Initializable {

    private final ServerController serverController;

    private final ServerConnectionError serverConnectionError;

    public UserInterfaceController(ServerController serverController, ServerConnectionError serverConnectionError) {
        this.serverController = serverController;
        this.serverConnectionError = serverConnectionError;
    }

    @FXML
    private TableColumn<Contract, CheckBox> checkBox;

    @FXML
    private Label welcomeText;

    @FXML
    private TableColumn<Contract, Long> id;

    @FXML
    private TableColumn<Contract, LocalDate> startDate;

    @FXML
    private TableColumn<Contract, String> contractNumber;

    @FXML
    private TableColumn<Contract, LocalDate> updateDate;

    @FXML
    private TableView<Contract> table;

    ObservableList<Contract> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        contractNumber.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        updateDate.setCellValueFactory(new PropertyValueFactory<>("updateDate"));
        checkBox.setCellValueFactory(value -> {
            Contract contract = value.getValue();
            CheckBox checkBoxInColumn = new CheckBox();
            checkBoxInColumn.setDisable(true);
            checkBoxInColumn.selectedProperty().setValue(contract.getCheckBox());
            checkBoxInColumn.selectedProperty().addListener((valueBoolean, oldValueBoolean, newValueBoolean)
                    -> contract.setCheckBox(newValueBoolean));
            return new SimpleObjectProperty<>(checkBoxInColumn);
        });
    }

    @FXML
    protected void onHelloButtonClick() {
        try {
            observableList.clear();
            observableList.addAll(serverController.getContractsList());
            table.setItems(observableList);
            welcomeText.setText("*Если дата последнего обновления договора меньше текущей даты на 60 дней");
        } catch (Exception e) {
            serverConnectionError.showErrorConnection();
        }
    }
}