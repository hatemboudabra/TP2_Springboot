package net.javaguides.springboot.springbootbackend.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name="employees")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
}
