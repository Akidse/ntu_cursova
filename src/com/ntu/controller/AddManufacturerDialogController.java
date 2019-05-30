package com.ntu.controller;

import com.ntu.dao.ManufacturerDAOImpl;
import com.ntu.domain.Manufacturer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddManufacturerDialogController {
    Runnable onSuccessCallback;
    ManufacturerDAOImpl model;

    @FXML
    Button closeButton;

    @FXML
    TextField nameInput;

    public AddManufacturerDialogController() {
        model = new ManufacturerDAOImpl();
    }

    public void addOnSuccessListener(Runnable callback) {
        onSuccessCallback = callback;
    }

    public void submit() {
        if(nameInput.getText().length() < 1 || nameInput.getText().length() > 64)
            return;

        if(model.insertManufacturer(new Manufacturer(nameInput.getText())) && onSuccessCallback != null) {
            onSuccessCallback.run();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }


    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
