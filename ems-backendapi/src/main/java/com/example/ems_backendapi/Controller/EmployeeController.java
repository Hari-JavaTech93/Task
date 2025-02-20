package com.example.ems_backendapi.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ems_backendapi.entity.EmployeeEntity;
import com.example.ems_backendapi.exception.BusinessException;
import com.example.ems_backendapi.exception.ControllerException;
import com.example.ems_backendapi.service.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EmployeeController {
	
	@Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeEntity employee) {
        try {
            return new ResponseEntity<EmployeeEntity>(employeeService.createEmployee(employee), HttpStatus.CREATED);
        }
        catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST); 
            }
        catch (Exception e) {
            ControllerException ce = new ControllerException("608", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees(){
        try {
            return new ResponseEntity<List<EmployeeEntity>>(employeeService.getAllEmployees(), HttpStatus.OK);
        }
        catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);       }
        catch (Exception e) {
            ControllerException ce = new ControllerException("609", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        boolean deleted = false;
        Map<String, Boolean> response = new HashMap<>();
        try {
            deleted = employeeService.deleteEmployee(id);
            response.put("deleted", deleted);
            return ResponseEntity.ok(response);
        }
        catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            ControllerException ce = new ControllerException("610", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try {
        	EmployeeEntity employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        }
        catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            ControllerException ce = new ControllerException("611", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id,
                                                   @RequestBody EmployeeEntity employee) {
        employee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(employee);

    }}
