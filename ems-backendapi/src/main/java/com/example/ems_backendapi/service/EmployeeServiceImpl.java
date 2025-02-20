package com.example.ems_backendapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ems_backendapi.entity.EmployeeEntity;
import com.example.ems_backendapi.exception.BusinessException;
import com.example.ems_backendapi.respository.EmployeeRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private final EmployeeRepository employeeRepository;
	@Autowired
	private final BusinessException businessException;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,BusinessException businessException) {
		//super();
		this.employeeRepository = employeeRepository;
		this.businessException =businessException;
	}

	@Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);

        if(employee.getName().isEmpty() || employee.getName().length() == 0) {
            throw new BusinessException("601", "Please send proper name, It is blank");
        }
        try {
            employeeRepository.save(employeeEntity);
        }
        catch (IllegalArgumentException e) {
            throw new BusinessException("602", "This employee is null " + e.getMessage());
        }
        catch (Exception e) {
            throw new BusinessException("603", "Something went wrong in service layer while saving the employee" + e.getMessage());
        }
        return employee;
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = null;
        try {
            employeeEntities = employeeRepository.findAll();
        }
        catch (Exception e) {
            throw new BusinessException("605", "Something went wrong in service layer while fetching all employee records " + e.getMessage());
        }
        if(employeeEntities.isEmpty()) {
            throw new BusinessException("604", "There are no Employee records");
        }
        return employeeEntities.stream()
                .map(employee -> new EmployeeEntity(employee.getId(), employee.getName(), employee.getAge(), employee.getDepartment(),employee.getPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteEmployee(Long id) {
        try {
           Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(id);
        	if(employeeEntity.isPresent()) {
        		 employeeRepository.delete(employeeEntity.get());
        		 return true;
        	}
            return false;
        }
        catch (IllegalArgumentException e) {
           throw new BusinessException("608", "given employee id is null, try with another id " + e.getMessage());
        }
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        try {
            EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
            EmployeeEntity employee = new EmployeeEntity();
            BeanUtils.copyProperties(employeeEntity, employee);
            return employee;
        }
        catch (IllegalArgumentException e) {
            throw new BusinessException("606", "given employee id is null, try with another id " + e.getMessage());
        }
        catch (NoSuchElementException e) {
           throw new BusinessException("607", "given employee id does not exist " + e.getMessage());
        }
    }

    @Override
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setName(employee.getName());
        employeeEntity.setAge(employee.getAge());
        employeeEntity.setDepartment(employee.getDepartment());
        employeeEntity.setPosition(employee.getPosition());
        employeeRepository.save(employeeEntity);
        return employee;
    }


}
