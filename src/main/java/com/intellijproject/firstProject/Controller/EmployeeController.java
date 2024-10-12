package com.intellijproject.firstProject.Controller;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import com.intellijproject.firstProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveuser")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.saveEmployeeData(employeeDto);

        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }
}
