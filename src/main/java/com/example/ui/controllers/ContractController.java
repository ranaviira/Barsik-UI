package com.example.ui.controllers;

import com.example.ui.entity.Contract;
import com.example.ui.service.ContractService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
@FxmlView("/com/example/ui/fxml/barsik-view.fxml")
public class ContractController implements Initializable {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @FXML
    private TableColumn<Contract, Boolean> checkBox;

    @FXML
    private Label welcomeText;

    @FXML
    private TableColumn<Contract, Long> id;

    @FXML
    private TableColumn<Contract, LocalDate> start;

    @FXML
    private TableColumn<Contract, String> number;

    @FXML
    private TableColumn<Contract, LocalDate> update;

    @FXML
    private TableView<Contract> table;

    ObservableList<Contract> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        update.setCellValueFactory(new PropertyValueFactory<>("update"));
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        observableList.addAll(contractService.getAllContracts());
        table.setItems(observableList);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("True если дата последнего обновления договора меньше текущей даты на 60 дней");
    }
}