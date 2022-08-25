package br.com.sisnema.milionarios.dto;

import br.com.sisnema.milionarios.entities.Funcao;

import java.io.Serializable;

public class FuncaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String autoridade;

    public FuncaoDTO() {
    }

    public FuncaoDTO(Integer id, String autoridade) {
        this.id = id;
        this.autoridade = autoridade;
    }

    public FuncaoDTO(Funcao entidade) {
        id = entidade.getId();
        autoridade = entidade.getAutoridade();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutoridade() {
        return autoridade;
    }

    public void setAutoridade(String autoridade) {
        this.autoridade = autoridade;
    }
}
