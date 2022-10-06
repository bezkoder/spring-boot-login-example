package br.com.douglas.aterrosystem.repository;

import java.util.Optional;

import br.com.douglas.aterrosystem.models.ERole;
import br.com.douglas.aterrosystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
