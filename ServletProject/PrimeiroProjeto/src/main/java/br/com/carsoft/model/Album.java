package br.com.carsoft.model;

public class Album {
    private String id;
    private String titulo;
    private String artista;
    private String album;

    private String informacoes;
    public Album(String titulo, String artista, String album, String informacoes) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.informacoes = informacoes;
    }

    public Album(String id,String titulo, String artista, String album, String informacoes) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.informacoes = informacoes;
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
