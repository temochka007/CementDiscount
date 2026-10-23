package org.example.service;

import org.example.adapter.*;
import org.example.model.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class OrderAlgorithm {

    private final double startDiscount;
    private final double stepDiscount;
    private final double priceKg;

    public OrderAlgorithm(double startDiscount, double stepDiscount, double priceKg) {
        this.startDiscount = startDiscount;
        this.stepDiscount = stepDiscount;
        this.priceKg = priceKg;
    }

    public Map<String, Double> algorithmic(Path inputPath) throws IOException {

        ChoiceExpansionFactory factory = new ChoiceExpansionFactory(List.of());

        FileSource source = factory.create(inputPath);
        List<Order> orders = source.readOrders();

        orders.sort(Comparator.comparing(Order::getTimeStamp));

        DiscountBusiness discountBusiness = new DiscountBusiness(startDiscount, stepDiscount);
        PriceCalculator priceCalculator = new PriceCalculator(priceKg, discountBusiness);

        List<Map.Entry<Order, Double>> ordersWithPrice = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++){
            Order order = orders.get(i);
            double price = priceCalculator.calculator(order, i);
            ordersWithPrice.add(new AbstractMap.SimpleEntry<>(order, price));
        }
        SortingCompany sortingCompany = new SortingCompany();
        return sortingCompany.sortingCompany(ordersWithPrice);

    }

}


