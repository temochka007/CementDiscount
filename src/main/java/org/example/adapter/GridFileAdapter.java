package org.example.adapter;

import org.example.model.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GridFileAdapter implements FileSource {

    private final Path path;

    public GridFileAdapter(Path path) {
        this.path = path;
    }

    @Override
    public List<Order> readOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            String[] parts = line.split("#");
            LocalDateTime ts = LocalDateTime.parse(parts[0]);
            String company = parts[1];
            double qty = Double.parseDouble(parts[2]);

            orders.add(new Order(ts, company, qty));
        }

        return orders;
    }

}
