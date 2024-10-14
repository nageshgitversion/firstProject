package com.intellijproject.firstProject;

import com.intellijproject.firstProject.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Employee,Integer> {
}
