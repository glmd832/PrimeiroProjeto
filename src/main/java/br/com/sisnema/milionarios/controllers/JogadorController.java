package br.com.sisnema.milionarios.controllers;

import br.com.sisnema.milionarios.dto.JogadorDTO;
import br.com.sisnema.milionarios.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/jogadores") // Endpoint
public class JogadorController {

    @Autowired
    JogadorService service;

    @GetMapping
    public ResponseEntity<List<JogadorDTO>> buscarJogadores() {
        List<JogadorDTO> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JogadorDTO> buscarPorId(@PathVariable Integer id) {
        JogadorDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping // deve retornar 201 http - recurso criado
    public ResponseEntity<JogadorDTO> inserir(@RequestBody JogadorDTO dto) {
        dto = service.inserirJogador(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}") // deve retornar 201 http - recurso criado
    public ResponseEntity<JogadorDTO> atualizar(@PathVariable Integer id, @RequestBody JogadorDTO dto) {
        dto = service.atualizarJogador(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluirJogador(id);
        // Retorna sem conteúdo no corpo 204
        return ResponseEntity.noContent().build();
    }
}
