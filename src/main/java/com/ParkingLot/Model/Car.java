package com.ParkingLot.Model;

public class Car extends Vehicle{
    String regNo;
    String color;


    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

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
