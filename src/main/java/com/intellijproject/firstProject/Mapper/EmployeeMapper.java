package com.intellijproject.firstProject.Mapper;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

   EmployeeMapper INSTACNE =  Mappers.getMapper(EmployeeMapper.class);

   Employee employeeDtoToEmployee(EmployeeDto employeeDto);

   EmployeeDto employeeToEmployeeDto(Employee employee);

}
