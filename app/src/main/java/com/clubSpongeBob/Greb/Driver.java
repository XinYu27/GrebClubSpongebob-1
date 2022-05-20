package com.clubSpongeBob.Greb;

public class Driver extends Person{
    String carPlate, carModel, carColour;
    int rating, numOfRating;

    Driver(String uid, String name, String location, int capacity, String carPlate, String carModel, String carColour, int rating, int numOfRating){
        super(name, 0);
        this.carPlate = carPlate;
        this.carModel = carModel;
        this.carColour = carColour;
        this.rating = rating;
        this.numOfRating = numOfRating;
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
