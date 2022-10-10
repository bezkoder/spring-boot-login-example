package br.com.douglas.aterrosystem.repository;

import br.com.douglas.aterrosystem.entity.TipoDescarte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDescarteRepository extends JpaRepository<TipoDescarte, Long> {

}
