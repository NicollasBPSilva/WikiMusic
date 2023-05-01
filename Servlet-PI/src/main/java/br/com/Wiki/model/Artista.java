package br.com.Wiki.model;

public class Artista {

    private String id;
    private String artista;
    private String musica;

    public Artista(String name, String musica) {
        this.artista = name;
        this.musica = musica;
    }

    public Artista(String id, String name, String musica) {
        this.id = id;
        this.artista = name;
        this.musica = musica;
    }

    public String getId() {
        return id;
    }

    public String getArtista() {
        return artista;
    }

    public String getMusica() {
        return musica;
    }

}
