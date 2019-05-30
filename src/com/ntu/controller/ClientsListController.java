package com.ntu.controller;

import com.ntu.DialogsManager;
import com.ntu.ScreenManager;
import com.ntu.components.Toast;
import com.ntu.dao.ClientDAOImpl;
import com.ntu.dao.SaleDAO;
import com.ntu.dao.SaleDAOImpl;
import com.ntu.domain.Client;
import com.ntu.domain.Dealership;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.sql.Timestamp;

public class ClientsListController {
    Dealership dealership;

    ClientDAOImpl clientsService;
    SaleDAOImpl salesService;

    @FXML
    TableView<Client> clientsTable;

    @FXML
    ListView<String> clientCarsListView;

    @FXML
    Label chooseClientLabel;

    public void initialize() {
        clientsService = new ClientDAOImpl();
        salesService = new SaleDAOImpl();
        initializeTable();

        clientCarsListView.setVisible(false);

        clientsTable.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue)-> {
            chooseClientLabel.setText("Вибраний клієнт: "+ newValue.getLastName()+" "+newValue.getFirstName());
            clientCarsListView.setVisible(true);
            ObservableList<String> items = FXCollections.observableArrayList(salesService.getNameAndPriceCarByClientId(newValue.getClientId()));
            clientCarsListView.setItems(items);
        });
    }

    public void initializeTable() {
        TableColumn<Client, Integer> idTableColumn = new TableColumn<Client, Integer>("#");
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        TableColumn<Client, String> modelTableColumn = new TableColumn<Client, String>("Прізвище");
        modelTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Client, String> manufacturerTableColumn = new TableColumn<>("Ім'я");
        manufacturerTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Client, Timestamp> dateAddedTableColumn = new TableColumn<Client, Timestamp>("Останній візит");
        dateAddedTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastVisit"));

        TableColumn<Client, Timestamp> countCarsTableColumn = new TableColumn<Client, Timestamp>("К-сть машин");
        countCarsTableColumn.setCellValueFactory(new PropertyValueFactory<>("carsCount"));
        TableColumn<Client, Void> actionTableColumn = new TableColumn<>("Дії");
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellWorkerFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {
                    private final Button btn = new Button("");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Client data = getTableView().getItems().get(getIndex());
                            DialogsManager.editClientDialog(dealership, data, () -> {
                                Toast.makeText(ScreenManager.primaryStage, "Клієнт успішно відредагований!");
                                updateTableData();
                            });
                        });
                        Image image1 = new Image(getClass().getResourceAsStream("/com/ntu/edit.png"));
                        btn.setGraphic(new ImageView(image1));
                    }
                    private final Button btn2 = new Button("");
                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            Client data = getTableView().getItems().get(getIndex());
                            if(clientsService.deleteCLient(data.getClientId())) {
                                Toast.makeText(ScreenManager.primaryStage, "Клієнт успішно видалено!");
                                updateTableData();
                            }
                        });
                        Image image2 = new Image(getClass().getResourceAsStream("/com/ntu/delete.png"));
                        btn2.setGraphic(new ImageView(image2));
                    }
                    private final HBox hbox = new HBox();
                    {
                        hbox.getChildren().addAll(btn, btn2);
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

        clientsTable.getColumns().addAll(idTableColumn, modelTableColumn, manufacturerTableColumn, dateAddedTableColumn, countCarsTableColumn, actionTableColumn);
    }

    public void updateTableData() {
        ObservableList<Client> observableClients = FXCollections.observableArrayList(clientsService.getAllClientsByDealershipId(dealership.getDealershipId()));
        System.out.println(observableClients);
        clientsTable.setItems(observableClients);
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
        updateTableData();
    }

    public void addClient() {
        DialogsManager.addClientDialog(dealership, () -> {
            Toast.makeText(ScreenManager.primaryStage, "Клієнт успішно добавлений");
            updateTableData();
        });
    }

    public void openCarsList() {
        ScreenManager.openCarsListScreen(dealership);
    }
}
