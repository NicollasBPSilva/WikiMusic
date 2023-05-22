package br.com.carsoft.model.Album;

public class Musica {

    private int id;
    private String musica;

    private int ativo;
    private int fkArtistaId;

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getFkArtistaId() {
        return fkArtistaId;
    }

    public void setFkArtistaId(int fkArtistaId) {
        this.fkArtistaId = fkArtistaId;
    }

    public Musica(String musica, int ativo) {
        this.musica = musica;
        this.ativo = ativo;
    }

    public Musica(String musica) {
        this.musica = musica;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }
}
