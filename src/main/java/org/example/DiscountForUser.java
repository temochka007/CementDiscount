package org.example;

import org.example.adapter.ChoiceExpansion;
import org.example.businessLogic.DiscountBusiness;
import org.example.businessLogic.Order;
import org.example.businessLogic.PriceCalculator;
import org.example.businessLogic.SortingCompany;
import org.example.fileWorking.FileSource;
import org.example.fileWorking.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.example.adapter.ChoiceExpansion.create;

public class DiscountForUser {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу: ");
        String inputPath = scanner.nextLine();

        System.out.print("Введите путь к выходному файлу: ");
        String outputPath = scanner.nextLine();

        double startDiscount = 50.0;
        double stepDiscount = 5.0;
        double priceKg = 10.0;

        Path input = Paths.get(inputPath);
        Path output = Paths.get(outputPath);


        try {
            FileSource source = ChoiceExpansion.create(input);

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
            scanner.close();
        }





    }
}

