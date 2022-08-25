package br.com.sisnema.milionarios.controllers;

import br.com.sisnema.milionarios.dto.PaisDTO;
import br.com.sisnema.milionarios.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/paises") // Endpoint
public class PaisController {

    @Autowired
    PaisService service;

    @GetMapping
    public ResponseEntity<List<PaisDTO>> buscarTodos() {
        List<PaisDTO> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaisDTO> buscarPaisPorId(@PathVariable Integer id) {
        PaisDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping // deve retornar 201 http - recurso criado
    public ResponseEntity<PaisDTO> inserirPais(@RequestBody PaisDTO dto) {
        dto = service.inserirPais(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
        // Para inserir no cabeçalho da resposta o id
    }

    @PutMapping(value = "/{id}") // deve retornar 201 http - recurso criado
    public ResponseEntity<PaisDTO> atualizarPais(@PathVariable Integer id, @RequestBody PaisDTO dto) {
        dto = service.atualizarPais(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirPais(@PathVariable Integer id) {
        service.excluirPais(id);
        // Retorna sem conteúdo no corpo 204
        return ResponseEntity.noContent().build();
    }
}
