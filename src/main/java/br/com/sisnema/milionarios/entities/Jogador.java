package br.com.sisnema.milionarios.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tablejogador")
public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String posicao;
    private double peso;
    private double altura;
    private double salario;
    private double patrocinio;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

    @ManyToMany
    @JoinTable(name = "tablejogadorpatrocinador",
            joinColumns = @JoinColumn(name = "jogador_id"),
            inverseJoinColumns = @JoinColumn(name = "patrocinador_id"))
    Set<Patrocinador> patrocinadores = new HashSet<>();

    public Jogador() {
    }

    public Jogador(int id, String nome, String posicao, double peso, double altura, double salario) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.peso = peso;
        this.altura = altura;
        this.salario = salario;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getPatrocinio() {
        return patrocinio;
    }

    public void setPatrocinio(double patrocinio) {
        this.patrocinio = patrocinio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

}
