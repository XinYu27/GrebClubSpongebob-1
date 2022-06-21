package com.clubSpongeBob.Greb;

import java.util.UUID;

public class Driver extends Person implements Comparable<Driver> {
    private String carPlate, carModel, carColour;
    private int rating, numOfRating;
    private String[] eatArr;
    private long totalDuration = 0;
    private String uid, customer;
    // status 0: unavailable, 1: available

    Driver() {
        super();
    }

    Driver(String name, String location, int capacity, String carPlate, String carModel, String carColour, int rating,
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
        this.numOfRating = 0;
        this.setStatus(1);
        setUid();
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumOfRating() {
        return numOfRating;
    }

    public void setNumOfRating(int numOfRating) {
        this.numOfRating = numOfRating;
    }

    public String[] getEatArr() {
        return eatArr;
    }

    public void setEatArr(String[] eatArr) {
        this.eatArr = eatArr;
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
