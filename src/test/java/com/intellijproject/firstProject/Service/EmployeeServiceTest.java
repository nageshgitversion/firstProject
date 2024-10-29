package com.intellijproject.firstProject.Service;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import com.intellijproject.firstProject.Exception.UserNotFoundException;
import com.intellijproject.firstProject.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository empRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void getEmployeeIdTest() throws UserNotFoundException {

        Integer empId = 1;
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setName("Simha");
        employee1.setCity("Bezawada");
        employee1.setSalary(35000);

        Employee employee = new Employee();
        employee.setName("Simha");
        employee.setCity("Bezawada");
        employee.setSalary(35000);

        when(empRepo.findById(empId)).thenReturn(Optional.of(employee));

        EmployeeDto result = employeeService.getEmployeeId(empId);

        assertNotNull(result);
        assertEquals(employee1.getEmpId(), result.getEmpId());
        assertEquals(employee1.getName(), result.getName());
        assertEquals(employee1.getCity(), result.getCity());
        assertEquals(employee1.getSalary(), result.getSalary());
    }

}
