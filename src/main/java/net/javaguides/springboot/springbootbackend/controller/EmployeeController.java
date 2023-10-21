package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Model.Employee;
import net.javaguides.springboot.springbootbackend.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/getAllemlpyee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @PostMapping(path = "/addEmploy")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new
                ResponseEntity<Employee>(employeeService.saveEmployee(employee),
                HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")
                                                    long employeeId){
        return new
                ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),
                HttpStatus.OK);
    }
@PutMapping("{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
    return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id),
            HttpStatus.OK);
}

@DeleteMapping("{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
    employeeService.deleteEmployee(id);
    return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
}
}
