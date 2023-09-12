package de.arag.functinal.programming.service;

import de.arag.functinal.programming.FunctionalProgrammingApplication;
import de.arag.functinal.programming.model.Employee;
import de.arag.functinal.programming.model.dto.EmployeeRequest;
import de.arag.functinal.programming.model.dto.EmployeeResponse;
import de.arag.functinal.programming.model.mapper.EmployeeMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static de.arag.functinal.programming.service.EmployeeValidator.ValidationResult;
import static de.arag.functinal.programming.service.EmployeeValidator.ValidationResult.VALID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {FunctionalProgrammingApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    private String employeeId;
    private EmployeeMapper employeeMapper = new EmployeeMapper();
    private Employee findEmployee;
    private EmployeeRequest employeeRequest;

    @BeforeAll
    public void setUp() {
        employeeId = UUID.randomUUID().toString();

        Employee employee = Employee.builder()
                .avatar("https://bootdey.com/img/Content/avatar/avatar1.png")
                .id(employeeId)
                .firstName("Aliou")
                .lastName("Condé")
                .email("aliou.conde@arag.de")
                .jobTitle("Software developer")
                .isActive(true)
                .createdAt(new Date())
                .departmentName("Consulting and Sales")
                .build();
        employeeService.addEmployee.accept(employee);
        //employeeService.addEmployee(employee);

        employee = Employee.builder()
                .avatar("https://bootdey.com/img/Content/avatar/avatar2.png")
                .id(UUID.randomUUID().toString())
                .firstName("Babak")
                .lastName("Ghavidel")
                .email("babak-ghavidel@arag.de")
                .jobTitle("Software developer")
                .isActive(false)
                .createdAt(new Date())
                .departmentName("Consulting and Sales")
                .build();
        employeeService.addEmployee.accept(employee);
        //employeeService.addEmployee(employee);

        findEmployee = employeeService.findEmployeeById.apply(employeeId);

        employeeRequest = employeeMapper.mapEmployeeToEmployeeRequest.apply(findEmployee);
    }

    @Test
    void findEmployeeById() {
        //Then
        assertThat(findEmployee).isNotNull();
    }

    @Test
    void findEmployees() {
        //Given
        int count = 12;

        //When
        int size = employeeService.getEmployees.get().size();

        //Then
        assertThat(count).isEqualTo(size);
    }

    @Test
    void employeeEmployeeRequestMapperTest() {
        //Then
        assertThat(employeeRequest).isNotNull();
    }

    @Test
    void employeeRequestEmployeeMapperTest() {
        //Given
        String id = employeeId;

        //When
        Employee employee = employeeMapper.mapEmployeeRequestToEmployee.apply(employeeRequest);
        employee.setId(findEmployee.getId());
        employee.setCreatedAt(findEmployee.getCreatedAt());

        //Then
        assertThat(findEmployee).isEqualTo(employee);
    }

    @Test
    void employeeEmployeeResponseMapperTest() {
        //When
        EmployeeResponse response = employeeMapper.mapEmployeeToEmployeeResponse.apply(findEmployee);

        //Then
        assertThat(response).isNotNull();
    }

    @Test
    void isEmployedActivated() {
        //Given
        Boolean isActive = Boolean.TRUE;

        //When
        Boolean actual = employeeService.isEmployeeActive.test(findEmployee);

        //When
        assertThat(isActive.booleanValue()).isEqualTo(actual.booleanValue());
    }

    @Test
    void employeeValidatorTest() {
        //Given
        ValidationResult expected = VALID;

        //When
        ValidationResult result = employeeService.validateEmployee.apply(findEmployee);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @AfterAll
    void deleteEmployee() {
        //Given
        int count = 11;
        String id = employeeId;
        Employee findEmployee = employeeService.findEmployeeById.apply(id);

        //When
       employeeService.deleteEmployee.accept(findEmployee);
        int size = employeeService.getEmployees.get().size();

        //Then
        assertThat(count).isEqualTo(size);
    }

}