package com.clubSpongeBob.Greb;

public class ModelforDriver {

    String name,carColour,carModel,carPlate,capacity,location,status;

    ModelforDriver()
    {

    }

    public ModelforDriver(String name, String carColour, String carModel, String carPlate, String capacity, String location, String status) {
        this.name = name;
        this.carColour = carColour;
        this.carModel = carModel;
        this.carPlate = carPlate;
        this.capacity = capacity;
        this.location = location;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
