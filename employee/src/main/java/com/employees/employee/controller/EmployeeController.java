package com.employees.employee.controller;

import com.employees.employee.mapper.EmployeeMapper;
import com.employees.employee.utils.ControllerUtils;
import com.employees.employee.entity.Employee;
import com.employees.employee.dto.EmployeeDto;
import com.employees.employee.response.EmployeeResponse;
import com.employees.employee.service.EmployeeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Resource
    private EmployeeMapper employeeMapper;

    // GET API
    @GetMapping
    public ResponseEntity<EmployeeResponse<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Direction dir
    ) {
        return ControllerUtils.buildOkResponse(
                employeeService.findTeamWithPagination(offset, pageSize, sortBy, dir));


    }

    // GET API
    @GetMapping("/{id}")
    public ResponseEntity
            <Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);

        } else {
            System.out.println("Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // CREATE API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeDto));
    }

    // DELETE API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    // PUT API
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}