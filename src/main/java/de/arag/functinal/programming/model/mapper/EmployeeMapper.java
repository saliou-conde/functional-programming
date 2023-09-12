package de.arag.functinal.programming.model.mapper;

import de.arag.functinal.programming.model.Employee;
import de.arag.functinal.programming.model.dto.EmployeeRequest;
import de.arag.functinal.programming.model.dto.EmployeeResponse;

import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

public class EmployeeMapper {

    public final Function<EmployeeRequest, Employee> mapEmployeeRequestToEmployee =
            employeeRequest -> Employee
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .avatar(employeeRequest.getAvatar())
                    .email(employeeRequest.getEmail())
                    .firstName(employeeRequest.getFirstName())
                    .lastName(employeeRequest.getLastName())
                    .jobTitle(employeeRequest.getJobTitle())
                    .createdAt(new Date())
                    .isActive(employeeRequest.getIsActive())
                    .build();

    public final Function<Employee, EmployeeRequest> mapEmployeeToEmployeeRequest =
            employeeRequest -> EmployeeRequest
                    .builder()
                    .avatar(employeeRequest.getAvatar())
                    .email(employeeRequest.getEmail())
                    .firstName(employeeRequest.getFirstName())
                    .lastName(employeeRequest.getLastName())
                    .jobTitle(employeeRequest.getJobTitle())
                    .isActive(employeeRequest.getIsActive())
                    .build();

    public final Function<Employee, EmployeeResponse> mapEmployeeToEmployeeResponse =
            employee -> EmployeeResponse
                    .builder()
                    .id(employee.getId())
                    .email(employee.getEmail())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .jobTitle(employee.getJobTitle())
                    .build();
}
