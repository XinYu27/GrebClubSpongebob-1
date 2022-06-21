package com.clubSpongeBob.Greb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Driver extends Person implements Comparable<Driver> {
    private String carPlate, carModel, carColour;
    private int numOfRating;
    private float rating;
    private List<String> eatArr = new ArrayList<>();
    private String uid, customer;
    private long totalDistance = 0;
    // status 0: unavailable, 1: available

    Driver() {
        super();
    }

    Driver(String name, String location, int capacity, String carPlate, String carModel, String carColour, float rating,
            int numOfRating, int status, String eat) {
        super(name, status, capacity, location);
        this.carPlate = carPlate;
        this.carModel = carModel;
        this.carColour = carColour;
        this.rating = rating;
        this.numOfRating = numOfRating;
        super.setEat(eat);
        setUid();
    }

    Driver(String name, String carModel, String carPlate, String carColour, int capacity, String location) {
        // Creating new driver
        super(name, 0, capacity, location);
        this.carPlate = carPlate;
        this.carModel = carModel;
        this.carColour = carColour;
        this.rating = 3;
        this.numOfRating = 1;
        this.setStatus(1);
        setUid();
    }

    public long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(long totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUid() {
        return uid;
    }

    private void setUid() {
        uid = createUID();
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String createUID() {
        return UUID.randomUUID().toString();
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumOfRating() {
        return numOfRating;
    }

    public void setNumOfRating(int numOfRating) {
        this.numOfRating = numOfRating;
    }

    public List<String> getEatArr() {
        return eatArr;
    }

    public void setEatArr(List<String> eatArr) {
        this.eatArr = eatArr;
    }

    public void resetOrder(){
        eatArr.clear();
        customer = null;
        setStatus(1);
    }

    @Override
    public int compareTo(Driver driver) {
        return this.getEat().compareTo(driver.getEat());
    }

    @Override
    public String toString() {
        return String.format("Driver: %s %s,Location: %s", this.uid, this.getName(), this.getLocation());
    }
}
