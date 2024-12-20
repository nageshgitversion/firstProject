package com.intellijproject.firstProject.Service;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import com.intellijproject.firstProject.Exception.UserNotFoundException;
import com.intellijproject.firstProject.Mapper.EmployeeMapper;
import com.intellijproject.firstProject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    private String name;


    public EmployeeDto saveEmployeeData(EmployeeDto employeeDto){

        Employee employee = empRepo.save(EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto));



        return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);
    }

    public List<EmployeeDto> getEmployeesList(){

        List<EmployeeDto> list = empRepo.findAll().stream().map(EmployeeMapper.INSTANCE::employeeToEmployeeDto).collect(Collectors.toList());

        return  list;
    }

    public EmployeeDto getEmployeeId(Integer empId) throws UserNotFoundException {
        Optional<Employee> optionalEmployee =  empRepo.findById(empId);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);
        }else {
             throw new UserNotFoundException("Employee not found with id: " + empId);
        }
    }


public List<EmployeeDto> findCity(String city){
    List<Employee> empList = empRepo.findByCity(city);

    List<EmployeeDto> list = empList.stream().map(EmployeeMapper.INSTANCE::employeeToEmployeeDto).collect(Collectors.toList());


    return list;
}




}
