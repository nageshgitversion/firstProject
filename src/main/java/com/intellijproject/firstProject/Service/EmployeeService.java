package com.intellijproject.firstProject.Service;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import com.intellijproject.firstProject.Mapper.EmployeeMapper;
import com.intellijproject.firstProject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;


    public EmployeeDto saveEmployeeData(EmployeeDto employeeDto){

        Employee employee = empRepo.save(EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto));



        return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);
    }

    public List<EmployeeDto> getEmployeesList(){

        List<EmployeeDto> list = empRepo.findAll().stream().map(EmployeeMapper.INSTANCE::employeeToEmployeeDto).collect(Collectors.toList());

        return  list;
    }



}
