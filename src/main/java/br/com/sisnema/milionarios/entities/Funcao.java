package br.com.sisnema.milionarios.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tablefuncao")
public class Funcao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String autoridade;

    public Funcao() {
    }

    public Funcao(Integer id, String autoridade) {
        this.id = id;
        this.autoridade = autoridade;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Funcao funcao = (Funcao) o;

        return Objects.equals(id, funcao.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
