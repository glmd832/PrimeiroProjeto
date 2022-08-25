package br.com.sisnema.milionarios.dto;

import br.com.sisnema.milionarios.entities.Jogador;
import br.com.sisnema.milionarios.entities.Patrocinador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JogadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String posicao;
    private double peso;
    private double altura;
    private double salario;
    private double patrocinio;
    private String descricao;

    private List<PatrocinadorDTO> patrocinadores = new ArrayList<>();

    public JogadorDTO() {
    }

    public JogadorDTO(Integer id, String nome, String posicao, double peso, double altura, double salario, double patrocinio, String descricao) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.peso = peso;
        this.altura = altura;
        this.salario = salario;
        this.patrocinio = patrocinio;
        this.descricao = descricao;
    }

    public JogadorDTO(Jogador entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.posicao = entidade.getPosicao();
        this.peso = entidade.getPeso();
        this.altura = entidade.getAltura();
        this.salario = entidade.getSalario();
        this.patrocinio = entidade.getPatrocinio();
        this.descricao = entidade.getDescricao();
    }

    public JogadorDTO(Jogador entidade, Set<Patrocinador> patrocinadores) {
        this(entidade);
        patrocinadores.forEach(pat -> this.patrocinadores.add(new PatrocinadorDTO(pat)));
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

    public List<PatrocinadorDTO> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(List<PatrocinadorDTO> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }
}
