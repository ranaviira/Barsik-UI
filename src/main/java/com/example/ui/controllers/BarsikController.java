package com.example.ui.controllers;

import com.example.ui.entity.Dogovor;
import com.example.ui.repository.DogovorRepository;
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
import java.util.Collection;
import java.util.ResourceBundle;

@Component
@FxmlView("/com/example/ui/controllers/barsik-view.fxml")
public class BarsikController implements Initializable {

    @Autowired
    private DogovorRepository dogovorRepository;

    @FXML
    private Label welcomeText;

    @FXML
    private TableColumn<Dogovor, Long> id;

    @FXML
    private TableColumn<Dogovor, String> login;

    @FXML
    private TableColumn<Dogovor, String> name;

    @FXML
    private TableColumn<Dogovor, String> password;

    @FXML
    private TableView<Dogovor> table;

    ObservableList<Dogovor> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Dogovor, Long>("id"));
        login.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("login"));
        name.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("name"));
        password.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("password"));

        observableList.addAll((Collection<? extends Dogovor>) dogovorRepository.findAll());
        table.setItems(observableList);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("УРААААААА");
    }
}