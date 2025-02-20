package com.example.ems_backendapi.service;


import java.util.List;
import com.example.ems_backendapi.entity.EmployeeEntity;

public interface EmployeeService {

	EmployeeEntity createEmployee(EmployeeEntity employee);

    List<EmployeeEntity> getAllEmployees();

    boolean deleteEmployee(Long id);

    EmployeeEntity getEmployeeById(Long id);

    EmployeeEntity updateEmployee(Long id, EmployeeEntity employee);

}
