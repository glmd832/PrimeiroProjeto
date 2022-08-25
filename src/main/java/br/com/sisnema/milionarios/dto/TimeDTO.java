package br.com.sisnema.milionarios.dto;

import br.com.sisnema.milionarios.entities.Pais;
import br.com.sisnema.milionarios.entities.Time;

import java.io.Serializable;

public class TimeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String cidade;
    private String estado;
    private Pais pais;

    public TimeDTO() {
    }

    public TimeDTO(Integer id, String nome, String cidade, String estado, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public TimeDTO(Time entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.cidade = entidade.getCidade();
        this.estado = entidade.getEstado();
        this.pais = entidade.getPais(getId());
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
