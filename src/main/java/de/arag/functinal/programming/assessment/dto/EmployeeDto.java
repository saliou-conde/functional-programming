package de.arag.functinal.programming.assessment.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeDto {
    private Integer id;
    private String name;
    private Double salary;
}
