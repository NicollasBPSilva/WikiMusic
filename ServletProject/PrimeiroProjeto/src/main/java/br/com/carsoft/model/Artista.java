package br.com.carsoft.model;

import java.util.List;

public class Artista {

    private int artistaId;
    private String nomeArtista;
    private String descricaoArtista;

    private String fkAlbumId;
    private int ativo;

    private List<Musica> musicas;

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public String getDescricaoArtista() {
        return descricaoArtista;
    }

    public void setDescricaoArtista(String descricaoArtista) {
        this.descricaoArtista = descricaoArtista;
    }

    public String getFkAlbumId() {
        return fkAlbumId;
    }

    public void setFkAlbumId(String fkAlbumId) {
        this.fkAlbumId = fkAlbumId;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public Artista(String nomeArtista, String descricaoArtista, int ativo) {
        this.nomeArtista = nomeArtista;
        this.descricaoArtista = descricaoArtista;
        this.ativo = ativo;
    }

    public Artista(int artistaId,String nomeArtista, String descricaoArtista) {
        this.artistaId = artistaId;
        this.nomeArtista = nomeArtista;
        this.descricaoArtista = descricaoArtista;
    }

}
