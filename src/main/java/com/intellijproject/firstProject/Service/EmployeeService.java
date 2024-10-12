package com.intellijproject.firstProject.Service;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import com.intellijproject.firstProject.Mapper.EmployeeMapper;
import com.intellijproject.firstProject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;


    public EmployeeDto saveEmployeeData(EmployeeDto employeeDto){
        Employee employee = empRepo.save(EmployeeMapper.INSTACNE.employeeDtoToEmployee(employeeDto));

        return EmployeeMapper.INSTACNE.employeeToEmployeeDto(employee);
    }


}
