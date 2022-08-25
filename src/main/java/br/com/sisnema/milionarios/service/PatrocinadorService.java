package br.com.sisnema.milionarios.service;

import br.com.sisnema.milionarios.dto.PatrocinadorDTO;
import br.com.sisnema.milionarios.entities.Patrocinador;
import br.com.sisnema.milionarios.repositories.PatrocinadorRepository;
import br.com.sisnema.milionarios.service.exceptions.BancoDeDadosException;
import br.com.sisnema.milionarios.service.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatrocinadorService {

    @Autowired
    private PatrocinadorRepository repository;

    @Transactional(readOnly = true) // Melhora a performance
    public List<PatrocinadorDTO> buscarTodos() {
        List<Patrocinador> lista = repository.findAll();
        return lista.stream().map(x -> new PatrocinadorDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PatrocinadorDTO buscarPorId(Integer id) {
        Optional<Patrocinador> objeto = repository.findById(id); // Optional evita o null
        //Patrocinador entidade = objeto.get();
        Patrocinador entidade = objeto.orElseThrow(() ->
                new EntidadeNaoEncontradaException("Entidade não encontrada."));
        return new PatrocinadorDTO(entidade);
    } // E se não existir o ID? Erro 500!

    @Transactional
    public PatrocinadorDTO inserirPatrocinador(PatrocinadorDTO dto) {
        Patrocinador entity = new Patrocinador();
        entity.setNome(dto.getNome());
        entity = repository.save(entity);
        return new PatrocinadorDTO(entity);
    }

    @Transactional
    public PatrocinadorDTO atualizarPatrocinador(Integer id, PatrocinadorDTO dto) { // Não vai no banco de dados
        try {
            // Ele instancia um objeto em memória
            Patrocinador entity = repository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity = repository.save(entity);
            return new PatrocinadorDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(
                    "Id " + id + " não encontrado para atualização.");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirPatrocinador(@PathVariable Integer id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException("Id não encontrado: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException("Violação de integridade no banco de dados.");
        }
        return ResponseEntity.noContent().build();
    }
}
