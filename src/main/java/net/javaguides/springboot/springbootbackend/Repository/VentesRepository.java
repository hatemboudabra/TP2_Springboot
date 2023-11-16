package net.javaguides.springboot.springbootbackend.Repository;

import net.javaguides.springboot.springbootbackend.Model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentesRepository extends JpaRepository<Ventes,Integer> {
}
