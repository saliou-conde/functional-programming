package de.arag.functinal.programming.assessment.dto.impl;

import de.arag.functinal.programming.assessment.EmployeeService;
import de.arag.functinal.programming.assessment.dto.EmployeeDto;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer, EmployeeDto> employeeMap = new HashMap<>();

    @Override
    public void addEmployee(EmployeeDto employee) {
        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public boolean removeEmployee(int id) {
        return employeeMap.remove(id) != null;
    }

    @Override
    public Optional<EmployeeDto> findById(int id) {
        return Optional.ofNullable(employeeMap.get(id));
    }

    @Override
    public List<EmployeeDto> findByName(String name) {
        return employeeMap.values().stream()
                .filter(emp -> emp.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public double getAverageSalary() {
        return employeeMap.values().stream()
                .mapToDouble(EmployeeDto::getSalary)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<EmployeeDto> sortByName() {
        return employeeMap.values().stream()
                .sorted(Comparator.comparing(EmployeeDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> sortBySalary() {
        return employeeMap.values().stream()
                .sorted(Comparator.comparingDouble(EmployeeDto::getSalary).reversed())
                .collect(Collectors.toList());
    }

}
