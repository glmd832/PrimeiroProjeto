package br.com.sisnema.milionarios.repositories;

import br.com.sisnema.milionarios.entities.Pais;
import br.com.sisnema.milionarios.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.PreparedStatement;
import java.util.Optional;

@DataJpaTest
public class PaisRepositoryTests {

    @Autowired
    private PaisRepository repository;

    private Integer existingId;
    private Integer nonExistingId;
    private Integer countTotalPais;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1;
        nonExistingId = 99;
        countTotalPais = 7;
    }

    @Test
    public void deleteDeveriaDeletarOObjetoQuandoOIdExistir() {
        repository.deleteById(existingId);
        Optional<Pais> result = repository.findById(existingId); // Vai retornar null
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteDeveriaLancarExcecaoSeIdNaoExistir() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

    @Test
    public void saveDeveriaSalvarComAutoincrementoQuandoOIdFornulo() {
        Pais pais = Factory.criarPais();
        pais.setId(null);
        pais = repository.save(pais);
        Assertions.assertNotNull(pais.getId());
        Assertions.assertEquals(countTotalPais + 1, pais.getId());
    }

    @Test
    public void procurarPorIdERetornarUmOptionalNãoVazio() {
        Optional<Pais> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void procurarPorIdERetornarUmOptionalVazio() {
        Optional<Pais> result = repository.findById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }

}
