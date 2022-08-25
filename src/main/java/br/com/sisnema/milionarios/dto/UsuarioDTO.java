package br.com.sisnema.milionarios.dto;

import br.com.sisnema.milionarios.entities.Usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;

    Set<FuncaoDTO> funcoes = new HashSet<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id, String nome, String sobrenome, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public UsuarioDTO(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        sobrenome = entidade.getSobrenome();
        email = entidade.getEmail();
        entidade.getFuncoes().forEach(func -> this.funcoes.add(new FuncaoDTO(func)));
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<FuncaoDTO> getFuncoes() {
        return funcoes;
    }
}
