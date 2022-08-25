package br.com.sisnema.milionarios.repositories;

import br.com.sisnema.milionarios.entities.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer> {
    // Possui vários métodos prontos.
}