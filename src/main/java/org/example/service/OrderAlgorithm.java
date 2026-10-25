package org.example.service;

import org.example.adapter.*;
import org.example.model.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class OrderAlgorithm {

    private final ChoiceExpansionFactory factory;
    private final PriceCalculator priceCalculator;
    private final SortingCompany sortingCompany;

    public OrderAlgorithm(ChoiceExpansionFactory factory, PriceCalculator priceCalculator, SortingCompany sortingCompany) {
        this.factory = factory;
        this.priceCalculator = priceCalculator;
        this.sortingCompany = sortingCompany;
    }

    public Map<String, Double> algorithmic(Path inputPath) throws IOException {
        FileSource source = factory.getAdapter(inputPath);
        List<Order> orders = source.readOrders(inputPath);

        orders.sort(Comparator.comparing(Order::getTimeStamp));

        List<AbstractMap.SimpleEntry<Order, Double>> ordersWithPrice = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++){
            double price = priceCalculator.calculator(orders.get(i), i);
            ordersWithPrice.add(new AbstractMap.SimpleEntry<>(orders.get(i), price));
        }

        return sortingCompany.sortingForCompany(ordersWithPrice);
    }

}


