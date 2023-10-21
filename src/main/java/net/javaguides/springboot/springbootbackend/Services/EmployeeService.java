package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
