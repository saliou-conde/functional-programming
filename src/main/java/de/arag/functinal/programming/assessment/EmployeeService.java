package de.arag.functinal.programming.assessment;

import de.arag.functinal.programming.assessment.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void addEmployee(EmployeeDto employee);
    boolean removeEmployee(int id);
    Optional<EmployeeDto> findById(int id);
    List<EmployeeDto> findByName(String name);
    List<EmployeeDto> getAllEmployees();
    double getAverageSalary();
    List<EmployeeDto> sortByName();
    List<EmployeeDto> sortBySalary();

}
