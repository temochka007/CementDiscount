package org.example.fileWorking;

import org.example.businessLogic.Order;

import java.io.IOException;
import java.util.List;

public interface FileSource{
    List<Order> readOrders() throws IOException;
}
