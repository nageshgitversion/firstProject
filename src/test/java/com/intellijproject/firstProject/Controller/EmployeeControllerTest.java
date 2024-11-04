package com.intellijproject.firstProject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Exception.UserNotFoundException;
import com.intellijproject.firstProject.FirstProjectApplication;
import com.intellijproject.firstProject.Service.EmployeeService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = FirstProjectApplication.class)
@Execution(ExecutionMode.SAME_THREAD)
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected MockMvc mockMvc;


    EmployeeControllerTest() throws Exception {
    }


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void saveUserTest() throws Exception {
        EmployeeDto newEmployee = new EmployeeDto();
        newEmployee.setEmpId(1);
        newEmployee.setName("Mahesh");
        newEmployee.setCity("Vijayawada");
        newEmployee.setSalary(25000);
        when(employeeService.saveEmployeeData(any())).thenReturn(newEmployee);
        MvcResult mvcResult = mockMvc.perform(post("/employee/saveuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newEmployee)))
                .andReturn();

        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getEmployeesTest() throws Exception {
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setEmpId(1);
        employee1.setName("Mahesh");
        employee1.setCity("Vijayawada");
        employee1.setSalary(25000);

        EmployeeDto employee2 = new EmployeeDto();
        employee2.setEmpId(2);
        employee2.setName("Suresh");
        employee2.setCity("Ongole");
        employee2.setSalary(30000);

        ArrayList<EmployeeDto> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);

        String jsonContent = new ObjectMapper().writeValueAsString(list);

        when(employeeService.getEmployeesList()).thenReturn(list);

        MvcResult mvcResult = mockMvc.perform(get("/employee/getlist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), jsonContent);


    }


    @Test
    public void getEmployeeByIdTest() throws Exception {
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setEmpId(1);
        employee1.setName("Mahesh");
        employee1.setCity("Vijayawada");
        employee1.setSalary(25000);

        String jsonContent = new ObjectMapper().writeValueAsString(employee1);

        when(employeeService.getEmployeeId(employee1.getEmpId())).thenReturn(employee1);

        MvcResult mvcResult = mockMvc.perform(get("/employee/getbyid/"+employee1.getEmpId()).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assertions.assertEquals(200,mvcResult.getResponse().getStatus());
    }

@Test
    public void getEmployeeByCityTest() throws Exception {

        String city = "vijayawada";

        EmployeeDto employee1 = new EmployeeDto();
        employee1.setEmpId(1);
        employee1.setName("Mahesh");
        employee1.setCity("vijayawada");
        employee1.setSalary(25000);

        EmployeeDto employee2 = new EmployeeDto();
        employee2.setEmpId(2);
        employee2.setName("Suresh");
        employee2.setCity("vijayawada");
        employee2.setSalary(30000);

        ArrayList<EmployeeDto> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);

        when(employeeService.findCity(employee1.getCity())).thenReturn(list);

        MvcResult mvcResult = mockMvc.perform(get("/employee/getbycity/" + employee1.getCity()).contentType(MediaType.APPLICATION_JSON)).andReturn();

        Assertions.assertEquals(200,mvcResult.getResponse().getStatus());
    }


}