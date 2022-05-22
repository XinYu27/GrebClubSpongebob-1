package com.clubSpongeBob.Greb;

public class Person {
    private String name="none";
    private String location="none";
    private int capacity=0;
    private int status=0;
    private String eat="none";

    Person(String name, int status){
        this.name = name;
        this.status = status;
    }

    Person(String name, int status, int capacity, String location){
        this.name=name;
        this.status = status;
        this.capacity=capacity;
        this.location=location;
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

    public void setEat(String eat){this.eat = eat;}

    public String getEat() {
        return eat;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
