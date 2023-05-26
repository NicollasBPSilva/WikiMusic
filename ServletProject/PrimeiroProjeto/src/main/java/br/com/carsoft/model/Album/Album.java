package br.com.carsoft.model.Album;

import java.util.*;

public class Album {
    private String id;
    private String gravadora;
    private String genero;
    private String pais;

    private String artista;
    private String descricaoArtista;

    private int ano;
    private String descricao;
    private byte[] imagem;

    private Base64[] base64imagem;

    private String imagemBase;

    private int ativo;
    private int artista_id;


    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    private Set<Artista> artistas;

    public String getDescricaoArtista() {
        return descricaoArtista;
    }

    public void setDescricaoArtista(String descricaoArtista) {
        this.descricaoArtista = descricaoArtista;
    }

    public Album(String getAlbumId) {
        this.id = getAlbumId;
    }

    public int getAno() {
        return ano;
    }

    public Album(String id, String gravadora, String genero, String pais, int ano, String descricao, byte[] imagem) {
        this.id = id;
        this.gravadora = gravadora;
        this.genero = genero;
        this.pais = pais;
        this.ano = ano;
        this.descricao = descricao;
        this.imagem = imagem;
        this.artistas = new HashSet<>();
    }


    public Album(String id, String gravadora, String genero, String pais, int ano, String descricao, String imagemBase) {
        this.id = id;
        this.gravadora = gravadora;
        this.genero = genero;
        this.pais = pais;
        this.ano = ano;
        this.descricao = descricao;
        this.imagemBase = imagemBase;

    }

    public Album(String gravadora, String genero, String pais, int ano, String descricao, byte[] imagem, int ativo) {
        this.gravadora = gravadora;
        this.genero = genero;
        this.pais = pais;
        this.ano = ano;
        this.descricao = descricao;
        this.imagem = imagem;
        this.ativo = ativo;

    }

    public Album(String id, String gravadora, String generoEditar, String pais, int ano, String descricao, String artista, String descricaoArtista, byte[] artistaImagem) {
        this.id = id;
        this.gravadora = gravadora;
        this.genero = generoEditar;
        this.pais = pais;
        this.ano = ano;
        this.descricao = descricao;
        this.artista = artista;
        this.descricaoArtista = descricaoArtista;
        this.imagem = artistaImagem;
    }


    public boolean containsArtista(Artista artista) {
        return artistas.contains(artista);
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public void setImagemBase(String imagemBase) {
        this.imagemBase = imagemBase;
    }

    public String getImagemBase() {
        return imagemBase;
    }

    public String getId() {
        return id;
    }

    public String getGravadora() {
        return gravadora;
    }

    public String getGenero() {
        return genero;
    }

    public String getPais() {
        return pais;
    }


    public String getDescricao() {
        return descricao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public int getArtista_id() {
        return artista_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public void setArtista_id(int artista_id) {
        this.artista_id = artista_id;
    }


    public Set<Artista> getArtistas() {
        return artistas;
    }

    public void addArtista(Artista artista) {
        if (artistas == null) {
            artistas = new HashSet<>();
        }
        artistas.add(artista);
    }
    public Artista getArtistaById(int artistId) {
        if (artistas != null) {
            for (Artista artista : artistas) {
                if (artista.getArtistaId() == artistId) {
                    return artista;
                }
            }
        }
        return null;
    }



}
