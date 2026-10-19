package org.example.adapter;

import org.example.model.Order;

import java.io.IOException;
import java.util.List;

public interface FileSource{
    List<Order> readOrders() throws IOException;
}
