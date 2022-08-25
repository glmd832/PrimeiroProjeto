package br.com.sisnema.milionarios.service;

import br.com.sisnema.milionarios.dto.FuncaoDTO;
import br.com.sisnema.milionarios.dto.UsuarioDTO;
import br.com.sisnema.milionarios.dto.PatrocinadorDTO;
import br.com.sisnema.milionarios.dto.UsuarioInserirDTO;
import br.com.sisnema.milionarios.entities.Funcao;
import br.com.sisnema.milionarios.entities.Usuario;
import br.com.sisnema.milionarios.entities.Patrocinador;
import br.com.sisnema.milionarios.repositories.FuncaoRepository;
import br.com.sisnema.milionarios.repositories.UsuarioRepository;
import br.com.sisnema.milionarios.repositories.PatrocinadorRepository;
import br.com.sisnema.milionarios.service.exceptions.BancoDeDadosException;
import br.com.sisnema.milionarios.service.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder senhaEnconder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private FuncaoRepository funcaoRepository;

    @Transactional(readOnly = true) // Melhora a performance
    public List<UsuarioDTO> buscarTodos() {
        List<Usuario> lista = repository.findAll();
        return lista.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscarPorId(Integer id) {
        Optional<Usuario> objeto = repository.findById(id); // Optional evita o null
        //Usuario entidade = objeto.get();
       Usuario entidade = objeto.orElseThrow(() ->
                new EntidadeNaoEncontradaException("Entidade não encontrada."));
        return new UsuarioDTO(entidade);
    }

    @Transactional
    public UsuarioDTO inserirUsuario(UsuarioInserirDTO dto) {
        Usuario entity = new Usuario();
        copiarDtoParaEntidade(dto, entity);
        entity.setSenha(senhaEnconder.encode(dto.getSenha()));
        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO dto) { // Não vai no banco de dados
        try {
            Usuario entity = repository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entity);
            entity = repository.save(entity);
            return new UsuarioDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(
                    "Id " + id + " não encontrado para atualização.");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Integer id) {
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

    private void copiarDtoParaEntidade(UsuarioDTO dto, Usuario entidade) {
        entidade.setNome(dto.getNome());
        entidade.setSobrenome(dto.getSobrenome());
        entidade.setEmail(dto.getEmail());

        entidade.getFuncoes().clear();
        for (FuncaoDTO funcDto : dto.getFuncoes()) {
            Funcao funcao = funcaoRepository.getReferenceById(funcDto.getId());
            entidade.getFuncoes().add(funcao);
        }
    }
}
