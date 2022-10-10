package br.com.douglas.aterrosystem.repository;

import br.com.douglas.aterrosystem.entity.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
    
}
