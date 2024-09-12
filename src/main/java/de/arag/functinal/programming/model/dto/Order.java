package de.arag.functinal.programming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order implements Comparable<Order> {
    private String id;
    private String name;
    private  Double price;

    @Override
    public int compareTo(Order o) {
        return this.id.compareTo(o.id);
    }
}
