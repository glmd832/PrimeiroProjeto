package br.com.sisnema.milionarios.service;

import br.com.sisnema.milionarios.dto.JogadorDTO;
import br.com.sisnema.milionarios.dto.PatrocinadorDTO;
import br.com.sisnema.milionarios.dto.TimeDTO;
import br.com.sisnema.milionarios.entities.Jogador;
import br.com.sisnema.milionarios.entities.Patrocinador;
import br.com.sisnema.milionarios.entities.Time;
import br.com.sisnema.milionarios.repositories.TimeRepository;
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
public class TimeService {

    @Autowired
    private TimeRepository repository;

    @Transactional(readOnly = true) // Melhora a performance
    public List<TimeDTO> buscarTodos() {
        List<Time> lista = repository.findAll();
        return lista.stream().map(x -> new TimeDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TimeDTO buscarPorId(Integer id) {
        Optional<Time> objeto = repository.findById(id); // Optional evita o null
        //Time entidade = objeto.get();
        Time entidade = objeto.orElseThrow(() ->
                new EntidadeNaoEncontradaException("Entidade não encontrada."));
        return new TimeDTO(entidade);
    } // E se não existir o ID? Erro 500!

    @Transactional
    public TimeDTO inserirTime(TimeDTO dto) {
        Time entity = new Time();
        copiarDtoParaEntidade(dto, entity);
        entity = repository.save(entity);
        return new TimeDTO(entity);
    }

    @Transactional
    public TimeDTO atualizarTime(Integer id, TimeDTO dto) { // Não vai no banco de dados
        try {
            // Ele instancia um objeto em memória
            Time entity = repository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entity);
            entity = repository.save(entity);
            return new TimeDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(
                    "Id " + id + " não encontrado para atualização.");
        }
    }

    public void excluirTime(Integer id) {
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

    private void copiarDtoParaEntidade(TimeDTO dto, Time entidade) {
        entidade.setNome(dto.getNome());
        entidade.setCidade(dto.getCidade());
        entidade.setEstado(dto.getEstado());
        entidade.getPais(dto.getPais().getId());
    }
}
