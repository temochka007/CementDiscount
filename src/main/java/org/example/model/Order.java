package org.example.model;

import java.time.LocalDateTime;

public class Order {
    private final LocalDateTime timeStamp;
    private final String nameCompany;
    private final double quantityKg;

    public Order(LocalDateTime timeStamp, String nameCompany, double quantityKg) {
        this.timeStamp = timeStamp;
        this.nameCompany = nameCompany;
        this.quantityKg = quantityKg;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public String getNameCompany() {
        return nameCompany;
    }
    public double getQuantityKg() {
        return quantityKg;
    }
}
