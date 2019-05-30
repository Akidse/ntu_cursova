package com.ntu;

import com.ntu.controller.AddCarDialogController;
import com.ntu.controller.AddClientDialogController;
import com.ntu.controller.AddManufacturerDialogController;
import com.ntu.controller.AddSaleDialogController;
import com.ntu.domain.Car;
import com.ntu.domain.Client;
import com.ntu.domain.Dealership;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogsManager {

    public static void addManufacturer(Runnable onSuccessCallback) {
        try {
            final Stage dialog = new Stage();
            dialog.setTitle("Додавання виробника");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(ScreenManager.primaryStage);
            VBox dialogVbox = new VBox(20);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./view/AddManufacturerDialogView.fxml"));

            dialogVbox.getChildren().add(loader.load());
            Scene dialogScene = new Scene(dialogVbox, 300, 100);
            dialog.setScene(dialogScene);
            dialog.show();
            AddManufacturerDialogController dialogController = loader.getController();
            dialogController.addOnSuccessListener(onSuccessCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addCarDialog(Dealership dealership, Runnable onSuccessCallback) {
        try {
            final Stage dialog = new Stage();
            dialog.setTitle("Додавання машини");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(ScreenManager.primaryStage);
            VBox dialogVbox = new VBox(20);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./view/AddCarDialogView.fxml"));

            dialogVbox.getChildren().add(loader.load());
            Scene dialogScene = new Scene(dialogVbox, 300, 220);
            dialog.setScene(dialogScene);
            dialog.show();
            AddCarDialogController dialogController = loader.getController();
            dialogController.setDealershipId(dealership.getDealershipId());
            dialogController.addOnSuccessListener(onSuccessCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editCarDialog(Dealership dealership,Car car, Runnable onSuccessCallback) {
        try {
            final Stage dialog = new Stage();
            dialog.setTitle("Додавання машини");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(ScreenManager.primaryStage);
            VBox dialogVbox = new VBox(20);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./view/AddCarDialogView.fxml"));

            dialogVbox.getChildren().add(loader.load());
            Scene dialogScene = new Scene(dialogVbox, 300, 220);
            dialog.setScene(dialogScene);
            dialog.show();
            AddCarDialogController dialogController = loader.getController();
            dialogController.setDealershipId(dealership.getDealershipId());
            dialogController.setEditMode(car);
            dialogController.addOnSuccessListener(onSuccessCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addClientDialog(Dealership dealership, Runnable onSuccessCallback){
        try {
            final Stage dialog = new Stage();
            dialog.setTitle("Додавання клієнта");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(ScreenManager.primaryStage);
            VBox dialogVbox = new VBox(20);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./view/AddClientDialogView.fxml"));

            dialogVbox.getChildren().add(loader.load());
            Scene dialogScene = new Scene(dialogVbox, 300, 140);
            dialog.setScene(dialogScene);
            dialog.show();
            AddClientDialogController dialogController = loader.getController();
            dialogController.setDealershipId(dealership.getDealershipId());
            dialogController.addOnSuccessListener(onSuccessCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editClientDialog(Dealership dealership, Client client, Runnable onSuccessCallback){
        try {
            final Stage dialog = new Stage();
            dialog.setTitle("Редагування клієнта");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(ScreenManager.primaryStage);
            VBox dialogVbox = new VBox(20);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./view/AddClientDialogView.fxml"));

            dialogVbox.getChildren().add(loader.load());
            Scene dialogScene = new Scene(dialogVbox, 300, 140);
            dialog.setScene(dialogScene);
            dialog.show();
            AddClientDialogController dialogController = loader.getController();
            dialogController.setDealershipId(dealership.getDealershipId());
            dialogController.addOnSuccessListener(onSuccessCallback);
            dialogController.setEditMode(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addSaleDialog(Dealership dealership, Car car, Runnable onSuccessCallback){
        try {
            final Stage dialog = new Stage();
            dialog.setTitle("Продаж машини");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(ScreenManager.primaryStage);
            VBox dialogVbox = new VBox(20);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./view/AddSaleDialogView.fxml"));

            dialogVbox.getChildren().add(loader.load());
            Scene dialogScene = new Scene(dialogVbox, 300, 100);
            dialog.setScene(dialogScene);
            dialog.show();
            AddSaleDialogController dialogController = loader.getController();
            dialogController.setDealershipId(dealership.getDealershipId());
            dialogController.setCar(car);
            dialogController.addOnSuccessListener(onSuccessCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
