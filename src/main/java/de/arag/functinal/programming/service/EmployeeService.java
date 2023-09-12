package de.arag.functinal.programming.service;

import de.arag.functinal.programming.model.Employee;
import de.arag.functinal.programming.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static de.arag.functinal.programming.service.EmployeeValidator.isEmailValid;
import static de.arag.functinal.programming.service.EmployeeValidator.isEmployeeActive;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private List<Employee> employees;

    @PostConstruct
    public void intEmployees() {
        employees = employeeRepository.findAll();
    }

    Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * The method gets an employee as argument and stores it to the database
     * return: void
     */
    Consumer<Employee> addEmployee = employeeToAdd -> {
        addEmployee(employeeToAdd);
        employees.add(employeeToAdd);
    };

    /**
     * The method gets an employee as argument and deletes it from the database
     * return: void
     */
    Consumer<Employee> deleteEmployee = employeeToDelete -> employees.remove(employeeToDelete);

    /**
     * The method gets an employeeId as argument and finds by ID in the database
     * return: found employee by employeeId
     */
    Function<String, Employee> findEmployeeById = employeeId -> employees
            .stream()
            .filter(employeeToFind -> employeeToFind.getId().equals(employeeId))
            .findFirst().get();

    Function<Employee, EmployeeValidator.ValidationResult> validateEmployee = employee ->
            isEmailValid().and(isEmployeeActive()).apply(employee);

    /**
     * The method gets an employee as argument and check if employee is active
     * return: TRUE/FALSE
     */
    Predicate<Employee> isEmployeeActive = employee -> employee.getIsActive() == Boolean.TRUE;

    /**
     * return: All employees from the database
     */
    Supplier<List<Employee>> getEmployees = () -> employees;
}
