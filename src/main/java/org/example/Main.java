package org.example;

import org.example.adapter.*;
import org.example.model.Order;
import org.example.ui.ConsoleForUser;
import org.example.service.DiscountBusiness;
import org.example.service.PriceCalculator;
import org.example.service.SortingCompany;
import org.example.writer.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ConsoleForUser user = new ConsoleForUser();
        try {
            Path input = user.getIntputPath();
            Path output = user.getOutPath();

            double startDiscount = 50.0;
            double stepDiscount = 5.0;
            double priceKg = 10.0;

            //FileSource source = ChoiceExpansion.create(input);
            List<ChoiceExpansion> strategies = new LinkedList<>();
            strategies.add(new TxtFileRead());
            strategies.add(new GridFileRead());

            ChoiceExpansionFactory factory = new ChoiceExpansionFactory(strategies);
            FileSource source = factory.create(input);

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
            Map<String, Double> sorting = sortingCompany.sortingCompany(ordersWithPrice);

            FileWriter fileWriter = new FileWriter();
            fileWriter.writeResult(output, sorting);

        }catch (IOException e){
            System.err.println("Ошибка работы с файлами: ");
        }finally {
            user.close();
        }





    }
}

