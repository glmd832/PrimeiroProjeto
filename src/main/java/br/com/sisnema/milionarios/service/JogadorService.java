package br.com.sisnema.milionarios.service;

import br.com.sisnema.milionarios.dto.JogadorDTO;
import br.com.sisnema.milionarios.dto.PatrocinadorDTO;
import br.com.sisnema.milionarios.entities.Jogador;
import br.com.sisnema.milionarios.entities.Patrocinador;
import br.com.sisnema.milionarios.repositories.JogadorRepository;
import br.com.sisnema.milionarios.repositories.PatrocinadorRepository;
import br.com.sisnema.milionarios.service.exceptions.BancoDeDadosException;
import br.com.sisnema.milionarios.service.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    @Transactional(readOnly = true) // Melhora a performance
    public List<JogadorDTO> buscarTodos() {
        List<Jogador> lista = repository.findAll();
        return lista.stream().map(x -> new JogadorDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public JogadorDTO buscarPorId(Integer id) {
        Optional<Jogador> objeto = repository.findById(id); // Optional evita o null
        //Jogador entidade = objeto.get();
       Jogador entidade = objeto.orElseThrow(() ->
                new EntidadeNaoEncontradaException("Entidade não encontrada."));
        return new JogadorDTO(entidade, entidade.getPatrocinadores());
    } // E se não existir o ID? Erro 500!

    @Transactional
    public JogadorDTO inserirJogador(JogadorDTO dto) {
        Jogador entity = new Jogador();
        copiarDtoParaEntidade(dto, entity);
        entity = repository.save(entity);
        return new JogadorDTO(entity);
    }

    @Transactional
    public JogadorDTO atualizarJogador(Integer id, JogadorDTO dto) { // Não vai no banco de dados
        try {
            Jogador entity = repository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entity);
            entity = repository.save(entity);
            return new JogadorDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(
                    "Id " + id + " não encontrado para atualização.");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirJogador(@PathVariable Integer id) {
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

    private void copiarDtoParaEntidade(JogadorDTO dto, Jogador entidade) {
        entidade.setNome(dto.getNome());
        entidade.setPosicao(dto.getPosicao());
        entidade.setPeso(dto.getPeso());
        entidade.setAltura(dto.getAltura());
        entidade.setSalario(dto.getSalario());
        entidade.setPatrocinio(dto.getPatrocinio());
        entidade.setDescricao(dto.getDescricao());

        entidade.getPatrocinadores().clear();
        for (PatrocinadorDTO patDto : dto.getPatrocinadores()) {
            Patrocinador patrocinador = patrocinadorRepository.getReferenceById(patDto.getId());
            entidade.getPatrocinadores().add(patrocinador);
        }
    }
}
