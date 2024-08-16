package db;

import model.Customer;

import java.util.ArrayList;


public class DBConnection {
    private static  DBConnection instance;
    private ArrayList<Customer> CustomerList;
    public ArrayList<Customer> getConnection(){
        return CustomerList;
    }
    private  DBConnection(){
        this.CustomerList=new ArrayList<>();

    }
    public static DBConnection getInstance(){
        if(instance==null){
            return  instance=new DBConnection();
        }
        return  instance;
    }
}
