package com.ntu.controller;

import com.ntu.DateUtil;
import com.ntu.dao.CarDAOImpl;
import com.ntu.dao.ManufacturerDAOImpl;
import com.ntu.domain.Car;
import com.ntu.domain.Manufacturer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

public class AddCarDialogController {
    Runnable onSuccessCallback;
    int dealershipId;
    ManufacturerDAOImpl manufacturerModel;
    CarDAOImpl carModel;

    boolean isEditMode;
    Car editableCar;

    @FXML
    ComboBox<Manufacturer> manufacturerSelect;

    @FXML
    TextField modelInput;

    @FXML
    TextField countInput;

    @FXML
    TextField priceInput;

    public void initialize() {
        manufacturerModel = new ManufacturerDAOImpl();
        carModel = new CarDAOImpl();
        List<Manufacturer> manufacturers = manufacturerModel.getAllManufacturers();

        manufacturerSelect.setConverter(new StringConverter<Manufacturer>() {
            @Override
            public String toString(Manufacturer object) {
                return object.getName();
            }

            @Override
            public Manufacturer fromString(String string) {
                return manufacturerSelect.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        manufacturerSelect.getItems().addAll(manufacturers);
    }

    public void addOnSuccessListener(Runnable callback) {
        this.onSuccessCallback = callback;
    }

    public void setDealershipId(int id) {
        dealershipId = id;
    }

    public void submit() {
        if(countInput.getText().length() == 0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка валідації");
            alert.setHeaderText("Введені дані помилкові");
            alert.setContentText("Поле кількості машин обовязкове");

            alert.showAndWait();
            return;
        }
        if(priceInput.getText().length() == 0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка валідації");
            alert.setHeaderText("Введені дані помилкові");
            alert.setContentText("Поле кількості машин обовязкове");

            alert.showAndWait();
            return;
        }
        int count = Integer.parseInt(countInput.getText());
        int price = Integer.parseInt(priceInput.getText());

        if(modelInput.getText().length() < 1 || modelInput.getText().length() > 64) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка валідації");
            alert.setHeaderText("Введені дані помилкові");
            alert.setContentText("Поле моделі може мати максимум 64 символа, і мінімум 1 символ");

            alert.showAndWait();
            return;
        }

        if(count < 1 || price < 1)
            return;
        if(dealershipId == 0)
            return;
        if(manufacturerSelect.getSelectionModel().getSelectedItem() == null)
            return;

        if(isEditMode) {
            editableCar.setPrice(price);
            editableCar.setCount(count);
            editableCar.setModel(modelInput.getText());
            editableCar.setManufacturerId(manufacturerSelect.getSelectionModel().getSelectedItem().getManufacturerId());
            if(carModel.updateCar(editableCar)) {
                onSuccessCallback.run();
                close();
            }
            return;
        }
        boolean isSuccess = carModel.insertCar(
                new Car(dealershipId, modelInput.getText(),
                        manufacturerSelect.getSelectionModel().getSelectedItem().getManufacturerId(),
                        DateUtil.getCurrentTimestamp(), count, true, price));

        if(isSuccess && this.onSuccessCallback != null) {
            onSuccessCallback.run();
            Stage stage = (Stage) modelInput.getScene().getWindow();
            stage.close();
        }
    }

    public void close() {
        Stage stage = (Stage) modelInput.getScene().getWindow();
        stage.close();
    }

    public void setEditMode(Car car) {
        editableCar = car;
        isEditMode = true;

        Manufacturer manufacturer = manufacturerModel.getManufacturerById(car.getManufacturerId());

        modelInput.setText(car.getModel());
        countInput.setText(Integer.toString(car.getCount()));
        priceInput.setText(Integer.toString(car.getPrice()));
        manufacturerSelect.getSelectionModel().select(manufacturer);

    }
}
