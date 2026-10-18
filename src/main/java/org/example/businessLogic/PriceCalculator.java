package org.example.businessLogic;

public class PriceCalculator {
    private double priceKg;
    private DiscountBusiness discountBusiness;

    public PriceCalculator(double priceKg, DiscountBusiness discountBusiness) {
        this.priceKg = priceKg;
        this.discountBusiness = discountBusiness;
    }

    public double calculator(Order order, int positionInList){
        double discount = discountBusiness.getDiscountPosition(positionInList);
        return order.getQuantityKg() * priceKg * (1.0 - discount);
    }

}