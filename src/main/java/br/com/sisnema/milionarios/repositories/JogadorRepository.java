package br.com.sisnema.milionarios.repositories;

import br.com.sisnema.milionarios.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    // Possui vários métodos prontos.
}
