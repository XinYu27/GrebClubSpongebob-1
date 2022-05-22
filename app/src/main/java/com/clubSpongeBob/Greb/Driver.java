package com.clubSpongeBob.Greb;

public class Driver extends Person{
    private String carPlate, carModel, carColour;
    private int rating, numOfRating;
    // status 0: unavailable, 1: available

    Driver(String name, String location, int capacity, String carPlate, String carModel, String carColour, int rating, int numOfRating, int status, String eat){
        super(name, status, capacity, location);
        this.carPlate = carPlate;
        this.carModel = carModel;
        this.carColour = carColour;
        this.rating = rating;
        this.numOfRating = numOfRating;
        super.setEat(eat);
    }

    Driver(String name, String carModel, String carPlate, String carColour, int capacity, String location){
        // Creating new driver
        super(name, 0, capacity, location);
        this.carPlate = carPlate;
        this.carModel = carModel;
        this.carColour = carColour;
        this.rating = 3;
        this.numOfRating = 0;
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


}
