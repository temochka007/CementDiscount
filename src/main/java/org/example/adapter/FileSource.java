package org.example.adapter;

import org.example.model.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileSource{
    List<Order> readOrders(Path path) throws IOException;

    String getSupportedExtension();

}
