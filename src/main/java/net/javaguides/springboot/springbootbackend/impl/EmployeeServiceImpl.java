package net.javaguides.springboot.springbootbackend.impl;

import net.javaguides.springboot.springbootbackend.Model.Employee;
import net.javaguides.springboot.springbootbackend.Repository.EmployeeRepository;
import net.javaguides.springboot.springbootbackend.Services.EmployeeService;
import net.javaguides.springboot.springbootbackend.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).get();
    }
    @Override
    public Employee updateEmployee(Employee employee, long id) {

        Employee existingEmployee =
                employeeRepository.findById(id).orElseThrow(
                        () -> new ResourceAccessException("EmployeeId"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }
    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }
}

