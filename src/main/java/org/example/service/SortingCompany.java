package org.example.service;

import org.example.model.Order;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SortingCompany {
    public Map<String, Double> sortingCompany(List<Map.Entry<Order, Double>> ordersWithPrice) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (var entry : ordersWithPrice) {
            Order order = entry.getKey();
            double price = entry.getValue();
            result.merge(order.getNameCompany(), price, Double::sum);
        }
        return result;
    }
}
