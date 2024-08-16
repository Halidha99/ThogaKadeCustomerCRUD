package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    @FXML
    private Label lblbox;

    @FXML
    private JFXComboBox<String> cmbtittle;

    @FXML
    private DatePicker dateob;

    @FXML
    private JFXTextField txtaddress;

    @FXML
    private JFXTextField txtcontact;

    @FXML
    private JFXTextField txtid;

    @FXML
    private JFXTextField txtname;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id=txtid.getText();
        String tittle=cmbtittle.getValue();
        String name=txtname.getText();
        String address=txtaddress.getText();
        String contact=txtcontact.getText();
        LocalDate dob=dateob.getValue();
        ArrayList<Customer> customerList = DBConnection.getInstance().getConnection();
        boolean isIdUnique = true;
        boolean isContactUnique = true;

        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                isIdUnique = false;
            }
            if (customer.getContact().equals(contact)) {
                isContactUnique = false;
            }
        }

        if (isIdUnique && isContactUnique) {
            Customer customer = new Customer(id,tittle, name, address, contact, dob);
            customerList.add(customer);
            clearText();
            lblbox.setText("Customer added successfully.");
        } else if (!isIdUnique) {
            lblbox.setText("Customer ID already exists. Please enter a unique ID.");
        } else if (!isContactUnique) {
            lblbox.setText("Customer contact number already exists. Please enter a unique Contact Number.");
        }
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearText();

    }
    private void clearText(){
        txtid.setText(null);
        cmbtittle.setValue(null);
        txtname.setText(null);
        txtaddress.setText(null);
        txtcontact.setText(null);
        dateob.setValue(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tittles =FXCollections.observableArrayList();
        tittles.add("MR");
        tittles.add("MISS");
        tittles.add("MRS");
        cmbtittle.setItems(tittles);
    }
}
