package org.example.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileWriter {
    public void writeResult(Path outputPath, Map<String, Double> sorting) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            for (var entry : sorting.entrySet()) {
                String company = entry.getKey();
                double total = entry.getValue();
                writer.write(String.format("%s - %.2f", company, total));
                writer.newLine();
            }
        }
    }
}
