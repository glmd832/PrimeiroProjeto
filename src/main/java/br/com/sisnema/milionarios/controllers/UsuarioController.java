package br.com.sisnema.milionarios.controllers;

import br.com.sisnema.milionarios.dto.UsuarioDTO;
import br.com.sisnema.milionarios.dto.UsuarioInserirDTO;
import br.com.sisnema.milionarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios") // Endpoint
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarUsuarios() {
        List<UsuarioDTO> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping // deve retornar 201 http - recurso criado
    public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO dto) {
        UsuarioDTO novoDto = service.inserirUsuario(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoDto);
    }

    @PutMapping(value = "/{id}") // deve retornar 201 http - recurso criado
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Integer id, @RequestBody UsuarioDTO dto) {
        dto = service.atualizarUsuario(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluirUsuario(id);
        // Retorna sem conteúdo no corpo 204
        return ResponseEntity.noContent().build();
    }
}
