package br.com.sisnema.milionarios.controllers;

import br.com.sisnema.milionarios.dto.PatrocinadorDTO;
import br.com.sisnema.milionarios.service.PatrocinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/patrocinadores") // Endpoint
public class PatrocinadorController {

    @Autowired
    PatrocinadorService service;

    @GetMapping
    public ResponseEntity<List<PatrocinadorDTO>> buscarPatrocinadores() {
        List<PatrocinadorDTO> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatrocinadorDTO> buscarPatrocinadorPorId(@PathVariable Integer id) {
        PatrocinadorDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping // deve retornar 201 http - recurso criado
    public ResponseEntity<PatrocinadorDTO> inserir(@RequestBody PatrocinadorDTO dto) {
        dto = service.inserirPatrocinador(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
        // Para inserir no cabeçalho da resposta o id
    }

    @PutMapping(value = "/{id}") // deve retornar 201 http - recurso criado
    public ResponseEntity<PatrocinadorDTO> atualizar(@PathVariable Integer id, @RequestBody PatrocinadorDTO dto) {
        dto = service.atualizarPatrocinador(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluirPatrocinador(id);
        return ResponseEntity.noContent().build();
    }
}
