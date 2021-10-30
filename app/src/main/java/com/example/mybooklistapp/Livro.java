package com.example.mybooklistapp;

import java.io.Serializable;

public class Livro implements Serializable {

    private Integer id;
    private String titulo;
    private String autor;
    private String colecao;
    private String editora;
    private Integer numpaginas;
    private Integer nota;
    private String recomendacao;
    private String recomendaria;
    private String genero;
    private String filme;
    private String personagemfavorito;
    private Integer capitulo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getColecao() {
        return colecao;
    }

    public void setColecao(String colecao) {
        this.colecao = colecao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getNumpaginas() {
        return numpaginas;
    }

    public void setNumpaginas(Integer numpaginas) {
        this.numpaginas = numpaginas;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

    public String getRecomendaria() {
        return recomendaria;
    }

    public void setRecomendaria(String recomendaria) {
        this.recomendaria = recomendaria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public String getPersonagemfavorito() {
        return personagemfavorito;
    }

    public void setPersonagemfavorito(String personagemfavorito) {
        this.personagemfavorito = personagemfavorito;
    }

    public Integer getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Integer capitulo) {
        this.capitulo = capitulo;
    }
}
