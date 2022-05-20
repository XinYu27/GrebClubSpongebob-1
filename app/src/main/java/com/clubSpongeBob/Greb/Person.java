package com.clubSpongeBob.Greb;

public class Person {
    private String name="";
    private String location="";
    private int capacity=0;
    private int status=0;

    Person(String name, int status){
        this.name = name;
        this.status = status;
    }

    Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
