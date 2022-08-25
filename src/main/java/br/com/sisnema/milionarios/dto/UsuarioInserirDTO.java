package br.com.sisnema.milionarios.dto;

import java.io.Serializable;

public class UsuarioInserirDTO extends UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String senha;

    public UsuarioInserirDTO() {
        super();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
