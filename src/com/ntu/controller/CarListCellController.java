package com.ntu.controller;

import com.ntu.Main;
import com.ntu.ScreenManager;
import com.ntu.domain.Car;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class CarListCellController extends ListCell<Car> {

    public CarListCellController() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("./view/carListCellView.fxml"));
            loader.setController(this);
            loader.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Car item, boolean empty) {
        super.updateItem(item, empty);
    }
}
