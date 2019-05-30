package com.ntu.controller;

import com.ntu.ConnectionFactory;
import com.ntu.ScreenManager;
import com.ntu.components.Toast;

public class SqlConnectionCheckerController {

    public void connectOnline() {
        ConnectionFactory.DB_URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7293902?useSSL=false";
        ConnectionFactory.DB_PASSWORD = "bYujTrzmas";
        ConnectionFactory.DB_USER = "sql7293902";

        if(ConnectionFactory.isConnectionValid()) {
            ScreenManager.openDealershipChooserScreen();
        }
        else Toast.makeText(ScreenManager.primaryStage, "Не вдалось з'єднатись, попробуйте ще раз");
    }

    public void giveAnotherTry() {
        if(ConnectionFactory.isConnectionValid()) {
            ScreenManager.openDealershipChooserScreen();
        }
        else Toast.makeText(ScreenManager.primaryStage, "Не вдалось з'єднатись, попробуйте ще раз");
    }
}
