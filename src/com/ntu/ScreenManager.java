package com.ntu;

import com.ntu.controller.CarsListController;
import com.ntu.controller.ClientsListController;
import com.ntu.domain.Dealership;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {
    public static Stage primaryStage;

    public static void openDealershipChooserScreen() {
        primaryStage.setTitle("Автосалон -- Вибір салону");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("./view/DealershipChooserView.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openCarsListScreen(Dealership dealership) {
        primaryStage.setTitle("Автосалон -- Перегляд автомобілів");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("./view/CarsListView.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            CarsListController controller = loader.getController();
            controller.setDealership(dealership);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openClientsList(Dealership dealership) {
        primaryStage.setTitle("Автосалон -- Список клієнтів");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("./view/ClientsListView.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            ClientsListController controller = loader.getController();
            controller.setDealership(dealership);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connectionScreen() {
        primaryStage.setTitle("Автосалон -- Помилка з'єднання");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("./view/SqlConnectionCheckerView.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
