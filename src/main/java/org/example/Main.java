package org.example;

import org.example.service.OrderAlgorithm;
import org.example.ui.ConsoleForUser;
import org.example.writer.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ConsoleForUser user = new ConsoleForUser();


        try {
            Path intputPath = user.getIntputPath();
            Path outputPath = user.getOutPath();

            double startDiscount = 50.0;
            double stepDiscount = 5.0;
            double priceKg = 10.0;

            OrderAlgorithm algorithm = new OrderAlgorithm(startDiscount, stepDiscount, priceKg);
            Map<String, Double> sorting = algorithm.algorithmic(intputPath);

            FileWriter fileWriter = new FileWriter();
            fileWriter.writeResult(outputPath, sorting);

        }catch (IOException e){
            System.err.println("Ошибка работы с файлами: ");
        }finally {
            user.close();
        }

    }
}

