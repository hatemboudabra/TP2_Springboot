package net.javaguides.springboot.springbootbackend.Repository;

import net.javaguides.springboot.springbootbackend.Model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MvtStkRepository extends JpaRepository<MvtStk,Integer> {
}
