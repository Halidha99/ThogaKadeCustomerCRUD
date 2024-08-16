package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class DashboardController {

    @FXML
    void btnExitOnAction(ActionEvent event) {
        Stage stage=new Stage();
        stage.close();

    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        Stage stage=new Stage();
        stage.setTitle("Add Customer Form");
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/add_Customer_Form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnViewCustomerOnAction(ActionEvent event) {
        Stage stage=new Stage();
        stage.setTitle("View Customer Form");
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_Customer_Form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

}
