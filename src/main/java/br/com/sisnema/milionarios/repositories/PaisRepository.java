package br.com.sisnema.milionarios.repositories;

import br.com.sisnema.milionarios.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    // Possui vários métodos prontos.
}