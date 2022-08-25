package br.com.sisnema.milionarios.repositories;

import br.com.sisnema.milionarios.entities.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Integer> {
    // Possui vários métodos prontos.
}
