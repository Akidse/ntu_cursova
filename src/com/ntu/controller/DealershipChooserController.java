package com.ntu.controller;

import com.ntu.ScreenManager;
import com.ntu.components.Toast;
import com.ntu.dao.DealershipDAOImpl;
import com.ntu.domain.Dealership;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.List;

public class DealershipChooserController {

    DealershipDAOImpl model;
    @FXML
    ComboBox<Dealership> dealershipChooser;

    @FXML
    TextField dealershipNameInput;

    @FXML
    Label copyright;

    public DealershipChooserController() {
        model = new DealershipDAOImpl();
    }

    public void initialize() {
        List<Dealership> dealerships = model.getAllDealerships();

        dealershipChooser.setConverter(new StringConverter<Dealership>() {
            @Override
            public String toString(Dealership object) {
                return object.getTitle();
            }

            @Override
            public Dealership fromString(String string) {
                return dealershipChooser.getItems().stream().filter(ap ->
                        ap.getTitle().equals(string)).findFirst().orElse(null);
            }
        });

        dealershipChooser.getItems().addAll(dealerships);

        dealershipChooser.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            ScreenManager.openCarsListScreen(newValue);
        });
    }

    public void createDealership() {
        String dealershipTitle = dealershipNameInput.getText();
        if(dealershipTitle.length() == 0 || dealershipTitle.length() > 64) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка валідації");
            alert.setHeaderText("Введені дані помилкові");
            alert.setContentText("Назва салону має бути більше ніж 1 символ і менше ніж 64.");

            alert.showAndWait();
            return;
        }


        Dealership dealership = new Dealership(dealershipTitle);

        if(model.insertDealership(dealership)) {
            List<Dealership> dealerships = model.getAllDealerships();
            dealershipChooser.getSelectionModel().clearSelection();
            dealershipChooser.getItems().clear();
            dealershipChooser.getItems().addAll(dealerships);
            Toast.makeText(ScreenManager.primaryStage, "Автосалон додано");
            dealershipNameInput.setText("");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка");
            alert.setHeaderText("Помилка створення");
            alert.setContentText("Під час створення відбулася помилка на сервері.");

            alert.showAndWait();
        }
    }
}
