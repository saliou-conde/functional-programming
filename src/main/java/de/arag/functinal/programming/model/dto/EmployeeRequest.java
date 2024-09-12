package de.arag.functinal.programming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeRequest {

    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private Boolean isActive;

}
