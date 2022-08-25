package br.com.sisnema.milionarios.repositories;

import br.com.sisnema.milionarios.entities.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {
    // Possui vários métodos prontos.
}