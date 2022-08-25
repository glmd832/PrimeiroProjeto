package br.com.sisnema.milionarios.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tabletime")
public class Time implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cidade;
    private String estado;

    @OneToMany(mappedBy = "time", fetch = FetchType.EAGER)
    private Set<Jogador> jogadores = new HashSet<>();
    // Coleção Set não aceita repetição - conjunto

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = true)
    private Pais pais;

    public Time() {
    }

    public Time(Integer id, String nome, String cidade, String estado, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
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

    public Set<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(Set<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Pais getPais(Integer id) {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
