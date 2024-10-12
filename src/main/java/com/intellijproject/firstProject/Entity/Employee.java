package com.intellijproject.firstProject.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employeeData")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer empId;
   private String name;
   private String city;
   private Integer salary;
}
