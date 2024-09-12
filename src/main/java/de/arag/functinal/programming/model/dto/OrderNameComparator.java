package de.arag.functinal.programming.model.dto;

import java.util.Comparator;

public class OrderNameComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
