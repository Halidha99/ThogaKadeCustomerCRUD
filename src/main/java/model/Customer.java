package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@ToString
public class Customer {
private String id;
private String tittle;
private String name;
private String address;
private String contact;
private LocalDate dob;

    public Customer(String id, String tittle, String name, String address, String contact,LocalDate dob) {
        this.id = id;
        this.name = tittle + " " +name;
        //this.name = name;
        this.address = address;
        this.contact=contact;
        this.dob = dob;
    }


}
