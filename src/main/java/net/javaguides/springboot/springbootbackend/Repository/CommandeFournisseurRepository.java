package net.javaguides.springboot.springbootbackend.Repository;

import net.javaguides.springboot.springbootbackend.Model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur,Integer> {
}
