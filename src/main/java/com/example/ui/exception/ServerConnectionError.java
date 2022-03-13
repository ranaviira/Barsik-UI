package com.example.ui.exception;

import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

@Component
public class ServerConnectionError {
    public void showErrorConnection(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ОШИБКА!!!");
        alert.setHeaderText("Сервер не доступен");
        alert.setContentText("Запустите Start-Server.bat");
        alert.showAndWait();
    }
}
