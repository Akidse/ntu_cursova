package com.ntu;


import com.ntu.domain.Dealership;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("favicon.png")));

        ScreenManager.primaryStage = primaryStage;

        if(!ConnectionFactory.isConnectionValid()){
            ScreenManager.connectionScreen();
            return;
        }


        ScreenManager.openDealershipChooserScreen();
    }
}
