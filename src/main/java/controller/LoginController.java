package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label txtSucess;

    private String name;
    private String password;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        Stage stage=new Stage();
        String name=txtName.getText();
        String password=txtPassword.getText();

        if(name.equalsIgnoreCase("Admin") && password.equalsIgnoreCase("123")){
            txtSucess.setText("Sucessfully  Login....");
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard_Form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("DashBoard Form");
            stage.show();

        }else {
            txtSucess.setText("Login Fail Please try again.....");
        }
        Customer customer=new Customer();
        //System.out.println(customer);
    }

}
