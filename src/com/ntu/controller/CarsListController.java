package com.ntu.controller;

import com.ntu.DateUtil;
import com.ntu.DialogsManager;
import com.ntu.ScreenManager;
import com.ntu.components.Toast;
import com.ntu.dao.CarDAOImpl;
import com.ntu.dao.ManufacturerDAOImpl;
import com.ntu.domain.Car;
import com.ntu.domain.Dealership;
import com.ntu.domain.Manufacturer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;


import java.sql.Timestamp;
import java.util.List;

public class CarsListController {
    CarDAOImpl carModel;
    ManufacturerDAOImpl manufacturersService;
    Dealership dealership;

    @FXML
    Label dealershipLabel;

    @FXML
    Button addCarButton;

    @FXML
    TableView<Car> carsTable;

    @FXML
    ComboBox<Manufacturer> filterManufacturerSelect;

    @FXML
    CheckBox filterAvailableCheckbox;

    @FXML
    CheckBox filterPrice1Checkbox;

    @FXML
    CheckBox filterPrice2Checkbox;

    @FXML
    CheckBox filterPrice3Checkbox;


    public void setDealership(Dealership pDealership) {
        dealership = pDealership;
        dealershipLabel.setText(dealership.getTitle());

        ObservableList<Car> observableCars = FXCollections.observableArrayList(carModel.getCarsByDealerShipId(dealership.getDealershipId()));
        carsTable.setItems(observableCars);
    }

    public void initialize() {
        carModel = new CarDAOImpl();
        manufacturersService = new ManufacturerDAOImpl();
        initializeTable();

        initializeFilters();
    }

    public void updateTableData() {
        if(filterManufacturerSelect.getSelectionModel().getSelectedItem() != null)
            carModel.setManufacturerFilter(filterManufacturerSelect.getSelectionModel().getSelectedItem());
        if(filterAvailableCheckbox.selectedProperty().get())
            carModel.setAvailableFilter(true);
        if(filterPrice1Checkbox.selectedProperty().get())
            carModel.setPriceLimitFilter(0, 50000);
        if(filterPrice2Checkbox.selectedProperty().get())
            carModel.setPriceLimitFilter(50000, 2000000);
        if(filterPrice3Checkbox.selectedProperty().get())
            carModel.setPriceLimitFilter(2000000, 100000000);

        ObservableList<Car> observableCars = FXCollections.observableArrayList(carModel.getFilteredCarsByDealershipId(dealership.getDealershipId()));
        carsTable.setItems(observableCars);
    }

    public void addManufacturer() {
        DialogsManager.addManufacturer(() -> Toast.makeText(ScreenManager.primaryStage, "Виробника додано"));
    }

    public void addCar() {
        DialogsManager.addCarDialog(dealership, () -> {
            updateTableData();
            Toast.makeText(ScreenManager.primaryStage, "Автомобіль додано");
        });
    }

    public void openClientsList() {
        ScreenManager.openClientsList(dealership);
    }

    public void initializeFilters() {
        List<Manufacturer> manufacturers = manufacturersService.getAllManufacturers();

        filterManufacturerSelect.setConverter(new StringConverter<Manufacturer>() {
            @Override
            public String toString(Manufacturer object) {
                return object.getName();
            }

            @Override
            public Manufacturer fromString(String string) {
                return filterManufacturerSelect.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        filterManufacturerSelect.getItems().addAll(manufacturers);

        filterManufacturerSelect.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            updateTableData();
        });

        filterAvailableCheckbox.selectedProperty().addListener((options, oldValue, newValue)->{
            updateTableData();
        });

        filterPrice1Checkbox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                filterPrice1Checkbox.setSelected(true);
                filterPrice2Checkbox.setSelected(false);
                filterPrice3Checkbox.setSelected(false);
                updateTableData();
            }
        });
        filterPrice2Checkbox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                filterPrice2Checkbox.setSelected(true);
                filterPrice1Checkbox.setSelected(false);
                filterPrice3Checkbox.setSelected(false);
                updateTableData();
            }
        });
        filterPrice3Checkbox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                filterPrice3Checkbox.setSelected(true);
                filterPrice1Checkbox.setSelected(false);
                filterPrice2Checkbox.setSelected(false);
                updateTableData();
            }
        });
    }

    public void initializeTable() {
        TableColumn<Car, Integer> idTableColumn = new TableColumn<Car, Integer>("#");
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("carId"));
        TableColumn<Car, String> modelTableColumn = new TableColumn<Car, String>("Модель");
        modelTableColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<Car, String> manufacturerTableColumn = new TableColumn<>("Виробник");
        manufacturerTableColumn.setCellValueFactory((p)->{
            Car x = p.getValue();
            if(x.getManufacturer() == null)
                return new SimpleStringProperty("-");

            return new SimpleStringProperty(x.getManufacturer().getName());
        });
        TableColumn<Car, Timestamp> dateAddedTableColumn = new TableColumn<Car, Timestamp>("Додано");
        dateAddedTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastAdded"));
        TableColumn<Car, Integer> countTableColumn = new TableColumn<Car, Integer>("К-сть");
        countTableColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        TableColumn<Car, String> availableTableColumn = new TableColumn<>("Є в наявності");
        availableTableColumn.setCellValueFactory((p)->{
            Car x = p.getValue();
            if(x.isAvailable())
                return new SimpleStringProperty("Так");
            else
                return new SimpleStringProperty("Ні");
        });
        TableColumn<Car, Integer> priceTableColumn = new TableColumn<Car, Integer>("Ціна");
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Car, Void> actionTableColumn = new TableColumn<>("Дії");
        Callback<TableColumn<Car, Void>, TableCell<Car, Void>> cellWorkerFactory = new Callback<TableColumn<Car, Void>, TableCell<Car, Void>>() {
            @Override
            public TableCell<Car, Void> call(final TableColumn<Car, Void> param) {
                final TableCell<Car, Void> cell = new TableCell<Car, Void>() {
                    private final Button btn = new Button("");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Car data = getTableView().getItems().get(getIndex());
                            DialogsManager.editCarDialog(dealership, data, () -> {
                                Toast.makeText(ScreenManager.primaryStage, "Машину успішно відредаговано!");
                                updateTableData();
                            });
                        });
                        Image image1 = new Image(getClass().getResourceAsStream("/com/ntu/edit.png"));
                        btn.setGraphic(new ImageView(image1));
                    }
                    private final Button btn2 = new Button("");
                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            Car data = getTableView().getItems().get(getIndex());
                            if(carModel.deleteCar(data.getCarId())) {
                                Toast.makeText(ScreenManager.primaryStage, "Машину успішно видалено!");
                                updateTableData();
                            }
                        });
                        Image image2 = new Image(getClass().getResourceAsStream("/com/ntu/delete.png"));
                        btn2.setGraphic(new ImageView(image2));
                    }

                    private final Button btn3 = new Button("Продати");
                    {
                        btn3.setOnAction((ActionEvent event) -> {
                            Car data = getTableView().getItems().get(getIndex());
                            DialogsManager.addSaleDialog(dealership, data, () -> {
                                Toast.makeText(ScreenManager.primaryStage, "Машину продано! Вітаємо!");
                                updateTableData();
                            });
                        });
                    }
                    private final HBox hbox = new HBox();
                    {
                        hbox.getChildren().addAll(btn, btn2, btn3);
                        hbox.setSpacing(4);
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        };
        actionTableColumn.setCellFactory(cellWorkerFactory);

        carsTable.getColumns().addAll(idTableColumn, modelTableColumn, manufacturerTableColumn, dateAddedTableColumn, countTableColumn, availableTableColumn, priceTableColumn, actionTableColumn);
    }
}
