package com.intellijproject.firstProject.Controller;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import com.intellijproject.firstProject.Exception.UserNotFoundException;
import com.intellijproject.firstProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getlist")
    public ResponseEntity<List<EmployeeDto>> getEmployeesList(){

        List<EmployeeDto> employeesList = employeeService.getEmployeesList();

        return  new ResponseEntity<>(employeesList,HttpStatus.OK);
    }


    @GetMapping("/getbyid/{empId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer empId) throws UserNotFoundException {
        EmployeeDto employeeDto = employeeService.getEmployeeId(empId);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }


}
