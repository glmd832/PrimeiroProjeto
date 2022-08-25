package br.com.sisnema.milionarios.controllers;

import br.com.sisnema.milionarios.dto.TimeDTO;
import br.com.sisnema.milionarios.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/times") // Endpoint
public class TimeController {

    @Autowired
    TimeService service;

    @GetMapping
    public ResponseEntity<List<TimeDTO>> buscarTodos() {
        List<TimeDTO> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TimeDTO> buscarTimePorId(@PathVariable Integer id) {
        TimeDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping // deve retornar 201 http - recurso criado
    public ResponseEntity<TimeDTO> inserirTime(@RequestBody TimeDTO dto) {
        dto = service.inserirTime(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
        // Para inserir no cabeçalho da resposta o id
    }

    @PutMapping(value = "/{id}") // deve retornar 201 http - recurso criado
    public ResponseEntity<TimeDTO> atualizarTime(@PathVariable Integer id, @RequestBody TimeDTO dto) {
        dto = service.atualizarTime(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirTime(@PathVariable Integer id) {
        service.excluirTime(id);
        // Retorna sem conteúdo no corpo 204
        return ResponseEntity.noContent().build();
    }
}
