package de.arag.functinal.programming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String dept;
    private String city;
    private int rank;
    private List<String> contacts;
}
