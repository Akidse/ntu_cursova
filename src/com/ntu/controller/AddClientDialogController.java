package com.ntu.controller;

import com.ntu.ScreenManager;
import com.ntu.dao.ClientDAOImpl;
import com.ntu.domain.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddClientDialogController {
    Client editableClient;
    boolean isEditMode;

    int dealershipId;
    Runnable onSuccessCallback;

    ClientDAOImpl clientsService;

    @FXML
    TextField firstNameInput;

    @FXML
    TextField lastNameInput;

    public void initialize() {
        clientsService = new ClientDAOImpl();
    }

    public void addOnSuccessListener(Runnable callback) {
        this.onSuccessCallback = callback;
    }

    public void setDealershipId(int id) {
        dealershipId = id;
    }

    public void submit() {
        if(firstNameInput.getText().length() == 0 || lastNameInput.getText().length() == 0)
            return;

        Client client = new Client();
        client.setFirstName(firstNameInput.getText());
        client.setLastName(lastNameInput.getText());
        client.setDealershipId(dealershipId);

        if(isEditMode){
            client.setClientId(editableClient.getClientId());

            if(clientsService.updateClient(client)) {
                if(onSuccessCallback != null)
                    onSuccessCallback.run();
                close();
            }
            return;
        }
        if(clientsService.insertClient(client)) {
            if(onSuccessCallback != null)
                onSuccessCallback.run();
            close();
        }
    }

    public void close() {
        Stage stage = (Stage) firstNameInput.getScene().getWindow();
        stage.close();
    }

    public void setEditMode(Client client) {
        this.editableClient = client;
        isEditMode = true;

        firstNameInput.setText(client.getFirstName());
        lastNameInput.setText(client.getLastName());
    }
}
