package com.clubSpongeBob.Greb;

import android.view.View;

public class Customer extends Person{
    private String destination="none";
    private String emergencyContact="none";
    private String email="none";
    private boolean admin = false;

    Customer(){
        super();
    }

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

    public boolean isAdmin() {
        return admin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void clearCustomerOrder(){
        this.setDestination("");
        this.setLocation("");
        this.setEat("");
        this.setCapacity(1);
        this.setStatus(0);
    }
}
