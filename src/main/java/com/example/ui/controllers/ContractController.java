package com.example.ui.controllers;

import com.example.ui.entity.Contract;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
@FxmlView("/com/example/ui/fxml/barsik-view.fxml")
public class ContractController implements Initializable {

    private final ServerController serverController;

    public ContractController(ServerController serverController) {
        this.serverController = serverController;
    }

    @FXML
    private TableColumn<Contract, CheckBox> checkBox;

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
        checkBox.setCellValueFactory(param -> {
            Contract contract = param.getValue();
            CheckBox checkBox1 = new CheckBox();
            checkBox1.setDisable(true);
            checkBox1.selectedProperty().setValue(contract.getCheckBox());
            checkBox1.selectedProperty().addListener((ov, old_val, new_val) -> contract.setCheckBox(new_val));
            return new SimpleObjectProperty<>(checkBox1);
        });

    }

    @FXML
    protected void onHelloButtonClick() {
        observableList.addAll(serverController.getAllContracts());
        table.setItems(observableList);
        welcomeText.setText("*Если дата последнего обновления договора меньше текущей даты на 60 дней");
    }
}