package net.javaguides.springboot.springbootbackend.Repository;

import net.javaguides.springboot.springbootbackend.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
}
