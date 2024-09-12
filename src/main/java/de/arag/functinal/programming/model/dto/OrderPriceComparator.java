package de.arag.functinal.programming.model.dto;

import java.util.Comparator;

public class OrderPriceComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
