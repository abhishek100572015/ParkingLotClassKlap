package com.ParkingLot.Model;

public class Car extends Vehicle{

    public Car(String regNo, String color) {
        this.regNo = regNo;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNo='" + regNo + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
