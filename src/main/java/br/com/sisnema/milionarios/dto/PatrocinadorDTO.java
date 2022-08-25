package br.com.sisnema.milionarios.dto;

import br.com.sisnema.milionarios.entities.Patrocinador;

import java.io.Serializable;

public class PatrocinadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public PatrocinadorDTO() {
    }

    public PatrocinadorDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public PatrocinadorDTO(Patrocinador entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
