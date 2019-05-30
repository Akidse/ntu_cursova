package com.ntu.controller;

import com.ntu.dao.CarDAOImpl;
import com.ntu.dao.ClientDAOImpl;
import com.ntu.dao.SaleDAOImpl;
import com.ntu.domain.Car;
import com.ntu.domain.Client;
import com.ntu.domain.Sale;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

public class AddSaleDialogController {
    ClientDAOImpl clientsService;
    SaleDAOImpl salesService;
    CarDAOImpl carsService;
    Car car;

    int dealershipId;
    Runnable onSuccessCallback;

    @FXML
    ComboBox<Client> clientSelect;

    public void initialize() {
        clientsService = new ClientDAOImpl();
        salesService = new SaleDAOImpl();
        carsService = new CarDAOImpl();



        clientSelect.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client object) {
                return object.getFullName();
            }

            @Override
            public Client fromString(String string) {
                return clientSelect.getItems().stream().filter(ap ->
                        ap.getFullName().equals(string)).findFirst().orElse(null);
            }
        });


    }

    public void addOnSuccessListener(Runnable callback) {
        this.onSuccessCallback = callback;
    }

    public void setDealershipId(int id) {
        dealershipId = id;

        List<Client> clients = clientsService.getAllClientsByDealershipId(dealershipId);
        clientSelect.getItems().addAll(clients);
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void close() {
        Stage stage = (Stage) clientSelect.getScene().getWindow();
        stage.close();
    }

    public void submit() {
        if(clientSelect.getSelectionModel().getSelectedItem() == null)
            return;

        Client client = clientSelect.getSelectionModel().getSelectedItem();
        Sale sale = new Sale();
        sale.setClientId(client.getClientId());
        sale.setCarId(car.getCarId());

        car.setCount(car.getCount()-1);

        if(salesService.insertSale(sale) && carsService.updateCar(car)) {
            if(onSuccessCallback != null)
                onSuccessCallback.run();
            close();
        }
    }
}
