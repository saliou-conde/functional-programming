package de.arag.functinal.programming.service;

import de.arag.functinal.programming.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static de.arag.functinal.programming.service.EmployeeValidator.*;
import static de.arag.functinal.programming.service.EmployeeValidator.ValidationResult.*;

public interface EmployeeValidator extends Function<Employee, ValidationResult> {

    static EmployeeValidator isEmailValid() {
        return employee -> employee.getEmail().contains("@") ? VALID : EMAIL_NOT_VALID;
    }

    static EmployeeValidator isEmployeeActive() {
        return employee -> employee.getIsActive().booleanValue() ? VALID : EMPLOYEE_NOT_ACTIVE;
    }

    default EmployeeValidator and(EmployeeValidator other) {
        return employee -> {
            ValidationResult result = this.apply(employee);
            return result.equals(VALID) ? other.apply(employee) : result;
        };
    }

    private EmployeeValidator test() {
        List<String> list = new ArrayList<>();
        return null;
    }

     enum ValidationResult {
         VALID,
         EMAIL_NOT_VALID,
         EMPLOYEE_NOT_ACTIVE
    }

}
