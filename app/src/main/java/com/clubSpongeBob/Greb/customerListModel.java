package com.clubSpongeBob.Greb;

public class customerListModel {
    String driverName;
    String carCapacity;
    String eatTime;
    String startingPoint;
    String destination;
    int driverStatus;

    public customerListModel(String driverName, String carCapacity, String eatTime,
                           String startingPoint, String destination,
                           int driverStatus) {
        this.driverName = driverName;
        this.carCapacity = carCapacity;
        this.eatTime = eatTime;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.driverStatus = driverStatus;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getCarCapacity() {
        return carCapacity;
    }

    public String getEatTime() {
        return eatTime;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public int getDriverStatus() {
        return driverStatus;
    }
}