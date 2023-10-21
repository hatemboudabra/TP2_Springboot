package net.javaguides.springboot.springbootbackend.Repository;

import net.javaguides.springboot.springbootbackend.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long>{
}
