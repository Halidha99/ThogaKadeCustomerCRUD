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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewCustomerController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbtittle;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colcontact;

    @FXML
    private TableColumn<?, ?> coldob;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private DatePicker dateob;
    @FXML
    private TableColumn<?, ?> colid;


    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private JFXTextField txtaddress;

    @FXML
    private JFXTextField txtcontact;

    @FXML
    private JFXTextField txtid;

    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtSearch;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        Customer selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            DBConnection.getInstance().getConnection().remove(selectedCustomer);
            loadTable();
        }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchText = txtSearch.getText().trim();
        if (!searchText.isEmpty()) {
            ObservableList<Customer>list = FXCollections.observableArrayList();
            for (Customer customer : DBConnection.getInstance().getConnection()) {
                if (customer.getId().contains(searchText) ||
                        customer.getName().contains(searchText)) {
                    list.add(customer);
                }
            }
            tblCustomer.setItems(list);
        } else {
            loadTable();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {

            selectedCustomer.setId(txtid.getText());
            selectedCustomer.setName(txtname.getText());
            selectedCustomer.setTittle(cmbtittle.getValue());
            selectedCustomer.setAddress(txtaddress.getText());
            selectedCustomer.setContact(txtcontact.getText());
            selectedCustomer.setDob(dateob.getValue());


            ArrayList<Customer> customerList = DBConnection.getInstance().getConnection();
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getId().equals(selectedCustomer.getId())) {
                    customerList.set(i, selectedCustomer);
                    break;
                }
            }


            loadTable();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tittles = FXCollections.observableArrayList();
        tittles.add("MR");
        tittles.add("MISS");
        tittles.add("MRS");
        cmbtittle.setItems(tittles);

       tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setValues((Customer) newValue);
            }
        });
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colname.setCellValueFactory(new PropertyValueFactory<>("name"));
       coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
       colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
       coldob.setCellValueFactory(new PropertyValueFactory<>("dob"));
       loadTable();
    }
    public  void setValues(Customer customer){
        txtid.setText(customer .getId());
        txtname.setText(customer.getName());
        cmbtittle.setValue(customer.getTittle());
        txtaddress.setText(customer.getAddress());
        txtcontact.setText(customer.getContact());
        dateob.setValue(customer.getDob());

    }
    private void loadTable(){
        ObservableList<Customer> customerObservableList=FXCollections.observableArrayList();
        ArrayList<Customer> dbList= DBConnection.getInstance().getConnection();
        dbList.forEach(ob->{
            customerObservableList.add(ob);
        });
        tblCustomer.setItems(customerObservableList);

    }
}
