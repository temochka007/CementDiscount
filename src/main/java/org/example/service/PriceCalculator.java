package org.example.service;

import org.example.model.Order;

public class PriceCalculator {
    private final double priceKg;
    private final DiscountBusiness discountBusiness;

    public PriceCalculator(double priceKg, DiscountBusiness discountBusiness) {
        this.priceKg = priceKg;
        this.discountBusiness = discountBusiness;
    }

    public double calculator(Order order, int positionInList){
        double discount = discountBusiness.getDiscountPosition(positionInList);
        return order.getQuantityKg() * priceKg * (1.0 - discount);
    }

}