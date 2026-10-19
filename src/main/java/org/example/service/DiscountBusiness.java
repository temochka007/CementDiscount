package org.example.service;

public class DiscountBusiness {
    private double startDiscount;
    private double stepDiscount;

    public DiscountBusiness(double startDiscount, double stepDiscount) {
        this.startDiscount = startDiscount;
        this.stepDiscount = stepDiscount;
    }

    public double getDiscountPosition(int position){
        double discount = startDiscount - (position * stepDiscount);
        if (discount < 0) {
            discount = 0;
        }
        return  discount / 100.0;
    }
}

