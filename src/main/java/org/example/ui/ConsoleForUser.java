package org.example.ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleForUser {
    private final Scanner scanner;

    public ConsoleForUser() {
        this.scanner = new Scanner(System.in);
    }

    public Path getIntputPath(){
        System.out.print("Введите путь к файлу: ");
        String inputPath = scanner.nextLine();

        Path path = Paths.get(inputPath);

        return path;
    }

    public Path getOutPath(){
        System.out.print("Введите путь к выходному файлу: ");
        String outputPath = scanner.nextLine();

        Path path = Paths.get(outputPath);

        return path;
    }
    public void message (Path output){
        System.out.println("Готово, результат записан.");
    }

    public void close(){
        scanner.close();
    }
}

