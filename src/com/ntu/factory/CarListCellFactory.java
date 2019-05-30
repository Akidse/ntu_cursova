package com.ntu.factory;

import com.ntu.controller.CarListCellController;
import com.ntu.domain.Car;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CarListCellFactory implements Callback<ListView<Car>, ListCell<Car>> {

    @Override
    public ListCell<Car> call(ListView<Car> param) {
        return new CarListCellController();
    }
}
