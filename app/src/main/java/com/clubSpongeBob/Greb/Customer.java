package com.clubSpongeBob.Greb;

public class Customer extends Person{
    private String destination;
    private String emergencyContact;
    private String email;

    Customer(String name, String location, int capacity, String destination, String emergency){
        super(name, 0);
        this.destination = destination;
        this.emergencyContact = emergency;
    }

    Customer(String email, String name, String emergency){
        super(name, 0);
        this.emergencyContact = emergency;
        this.email = email;
    }
}
