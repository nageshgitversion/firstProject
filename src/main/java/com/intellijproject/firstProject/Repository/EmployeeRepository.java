package com.intellijproject.firstProject.Repository;

import com.intellijproject.firstProject.Dto.EmployeeDto;
import com.intellijproject.firstProject.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {

    public List<Employee> findByCity(String city);
}
