package br.com.carsoft.model;

import java.awt.image.BufferedImage;
import java.util.Base64;

public class Album {
    private String id;
    private String titulo;
    private String artista;
    private String album;
    private byte[] imagem;

    private String imagemBase;
    public String getImagemBase() {
        return imagemBase;
    }

    public void setImagemBase(String imagemBase) {
        this.imagemBase = imagemBase;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    private String informacoes;
    public Album(String titulo, String artista, String album, String informacoes, byte[] imagem) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.informacoes = informacoes;
        this.imagem = imagem;
    }

    public Album(String id, String titulo, String artista, String album, String informacoes, String imagemBase) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.informacoes = informacoes;
        this.imagemBase = imagemBase;
    }

    public Album(String id,String titulo, String artista, String album, String informacoes, byte[] imagem) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.informacoes = informacoes;
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }

    public String getInformacoes() {
        return informacoes;
    }
    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }







}
