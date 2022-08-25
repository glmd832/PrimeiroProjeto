package br.com.sisnema.milionarios.service;

import br.com.sisnema.milionarios.dto.PaisDTO;
import br.com.sisnema.milionarios.entities.Pais;
import br.com.sisnema.milionarios.repositories.PaisRepository;
import br.com.sisnema.milionarios.service.exceptions.BancoDeDadosException;
import br.com.sisnema.milionarios.service.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    @Transactional(readOnly = true) // Melhora a performance
    public List<PaisDTO> buscarTodos() {
        List<Pais> lista = repository.findAll();
        return lista.stream().map(x -> new PaisDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaisDTO buscarPorId(Integer id) {
        Optional<Pais> objeto = repository.findById(id); // Optional evita o null
        //Pais entidade = objeto.get();
        Pais entidade = objeto.orElseThrow(() ->
                new EntidadeNaoEncontradaException("Entidade não encontrada."));
        return new PaisDTO(entidade);
    } // E se não existir o ID? Erro 500!

    @Transactional
    public PaisDTO inserirPais(PaisDTO dto) {
        Pais entity = new Pais();
        entity.setNome(dto.getNome());
        entity = repository.save(entity);
        return new PaisDTO(entity);
    }

    @Transactional
    public PaisDTO atualizarPais(Integer id, PaisDTO dto) { // Não vai no banco de dados
        try {
            // Ele instancia um objeto em memória
            Pais entity = repository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity = repository.save(entity);
            return new PaisDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(
                    "Id " + id + " não encontrado para atualização.");
        }
    }

    public void excluirPais(Integer id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException("Id " + id + " não encontrado para deleção.");
        }
        catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException("Violação de integridade no banco de dados.");
        }
    }
}
