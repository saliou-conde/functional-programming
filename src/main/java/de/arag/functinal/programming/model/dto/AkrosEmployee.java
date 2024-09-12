package de.arag.functinal.programming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AkrosEmployee {
    private String name;
    private String department;
    private Integer salary;
}
