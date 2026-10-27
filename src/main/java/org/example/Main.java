package org.example;

import org.example.adapter.ChoiceExpansionFactory;
import org.example.adapter.FileSource;
import org.example.adapter.GridFileAdapter;
import org.example.adapter.TxtFileAdapter;
import org.example.service.DiscountBusiness;
import org.example.service.OrderAlgorithm;
import org.example.service.PriceCalculator;
import org.example.service.SortingCompany;
import org.example.ui.ConsoleForUser;
import org.example.writer.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ConsoleForUser user = new ConsoleForUser();

        Map<String, FileSource> adapterExtension = new HashMap<>();
        adapterExtension.put(".txt", new TxtFileAdapter());
        adapterExtension.put("", new GridFileAdapter());

        ChoiceExpansionFactory factory = new ChoiceExpansionFactory(adapterExtension);

        DiscountBusiness discountBusiness = new DiscountBusiness(50.0, 5.0);
        PriceCalculator priceCalculator = new PriceCalculator(10.0, discountBusiness);
        SortingCompany sortingCompany = new SortingCompany();
        FileWriter fileWriter = new FileWriter();
        OrderAlgorithm algorithm = new OrderAlgorithm(factory, priceCalculator, sortingCompany);

        try {
            Path intputPath = user.getIntputPath();
            Path outputPath = user.getOutPath();

            Map<String, Double> sorting = algorithm.algorithmic(intputPath);

            fileWriter.writeResult(outputPath, sorting);

            user.message(outputPath);

        }catch (IOException e){
            System.err.println("Ошибка работы с файлами: ");
        }finally {
            user.close();
        }

    }
}

