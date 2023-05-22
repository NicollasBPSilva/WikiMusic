package br.com.carsoft.dao;

import br.com.carsoft.model.Album.Album;
import br.com.carsoft.model.Album.Artista;
import br.com.carsoft.model.Album.Musica;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Base64;

public class AlbumDao {

    public static void albumAdicionar(Album album, Artista artista, Musica musica) {
        //Inserir Album
        String SQL = "INSERT INTO ALBUM (GRAVADORA, GENERO, PAIS, ANO, DESCRICAO, IMAGEM, ATIVO) VALUES ((?), (?), (?), (?), (?), (?), (?))";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, album.getGravadora());
            preparedStatement.setString(2, album.getGenero());
            preparedStatement.setString(3, album.getPais());
            preparedStatement.setInt(4, album.getAno());
            preparedStatement.setString(5, album.getDescricao());
            preparedStatement.setBytes(6, album.getImagem());
            preparedStatement.setInt(7, album.getAtivo());

            preparedStatement.execute();

            connection.close();

            int albumId = encontrarUltimoAlbumAdicionado(album);

            inserirArtistaRerefenteUltimoAlbum(artista, albumId);

            int artistaId = encontrarIdArtista(artista);

            inserirMusicaRerefenteUltimoArtista(musica, artistaId);

            System.out.println("sucess in connection");


        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
    }


    public static int encontrarUltimoAlbumAdicionado(Album album) {

        //Buscar ID do album inserido
        String SQL = "SELECT TOP 1 ID FROM ALBUM WHERE GRAVADORA = (?) AND GENERO = (?) AND PAIS = (?) AND ANO = (?) AND DESCRICAO = (?) AND IMAGEM = (?) AND ATIVO = (?) ORDER BY 1 DESC";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, album.getGravadora());
            preparedStatement.setString(2, album.getGenero());
            preparedStatement.setString(3, album.getPais());
            preparedStatement.setInt(4, album.getAno());
            preparedStatement.setString(5, album.getDescricao());
            preparedStatement.setBytes(6, album.getImagem());
            preparedStatement.setInt(7, album.getAtivo());


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                  return resultSet.getInt("ID");
                }
            }

        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
    return -404;
    }

    public static int encontrarIdArtista(Artista artista){
        //Buscar ID do artista inserido
        String SQL = "SELECT TOP 1 ID FROM ARTISTA WHERE NOME = (?) AND DESCRICAO = (?) AND ATIVO = (?) AND ALBUM_ID = (?) ORDER BY 1 DESC";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, artista.getNomeArtista());
            preparedStatement.setString(2, artista.getDescricaoArtista());
            preparedStatement.setInt(3, artista.getAtivo());
            preparedStatement.setString(4, artista.getFkAlbumId());


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID");
                }
            }

        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
        return -404;
    }
        public static void inserirArtistaRerefenteUltimoAlbum(Artista artista, int albumId) {
            String SQL = "INSERT INTO ARTISTA(NOME, DESCRICAO, ATIVO, IMAGEM, ALBUM_ID) VALUES ((?), (?), (?), (?), (?))";
            try {
                Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                artista.setFkAlbumId(String.valueOf(albumId));

                preparedStatement.setString(1, artista.getNomeArtista());
                preparedStatement.setString(2, artista.getDescricaoArtista());
                preparedStatement.setInt(3, artista.getAtivo());
                preparedStatement.setBytes(4, artista.getArtistaImagem());
                preparedStatement.setString(5, artista.getFkAlbumId());

                preparedStatement.execute();

                connection.close();

                System.out.println("Sucesso ao adicionar artista");
            } catch (Exception e) {

                System.out.println("fail in connection");
                System.out.println(e);

            }

        }

    private static void inserirMusicaRerefenteUltimoArtista(Musica musica, int artistaId) {
        String SQL = "INSERT INTO MUSICA(NOME, ARTISTA_ID, ATIVO) VALUES ((?), (?), (?))";
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            musica.setFkArtistaId(artistaId);

            preparedStatement.setString(1, musica.getMusica());
            preparedStatement.setInt(2, musica.getFkArtistaId());
            preparedStatement.setInt(3, musica.getAtivo());

            preparedStatement.execute();

            connection.close();

            System.out.println("Sucesso ao adicionar musica");
        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);

        }
    }

    public List<Album> encontrarAlbumsPorGenero(String generoBusca, String nomeAlbum){

        String SQL = "SELECT alb.id, alb.gravadora, alb.genero, alb.pais, alb.ano, alb.descricao, alb.imagem, art.nome, art.descricao, music.nome AS music_nome, music.ativo " +
                "FROM ALBUM alb " +
                "JOIN ARTISTA art ON alb.id = art.album_id " +
                "JOIN MUSICA music ON art.id = music.artista_id " +
                "WHERE alb.GENERO LIKE CONCAT('%', ?, '%') AND alb.DESCRICAO LIKE CONCAT('%', ?, '%') AND alb.ATIVO = 1 AND art.ativo = 1 AND music.ativo = 1";



        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            if(nomeAlbum == null){
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            }
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);


            preparedStatement.setString(1, generoBusca);
            preparedStatement.setString(2, nomeAlbum);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Album> albumList = new ArrayList<>();
            List<Artista> artistaList = new ArrayList<>();
            List<Musica> musicaList = new ArrayList<>();

            while (resultSet.next()) {

                String albumId = resultSet.getString("id");

                String gravadora = resultSet.getString("gravadora");
                String genero = resultSet.getString("genero");
                String pais = resultSet.getString("pais");
                String ano = resultSet.getString("ano");
                String descricao = resultSet.getString("descricao");
                byte[] imagem = resultSet.getBytes("imagem");

                String base64Imagem = Base64.getEncoder().encodeToString(imagem);

                int converterAno = Integer.parseInt(ano);

                Album album = new Album(albumId, gravadora, genero, pais,converterAno ,descricao, base64Imagem);
                albumList.add(album);

                int artistaId = resultSet.getInt("id");

                String nomeArtista = resultSet.getString("nome");
                String descricaoArtista = resultSet.getString("descricao");

                byte[] imagemArtista = resultSet.getBytes("imagem");

                String  base64ImagemArtista = Base64.getEncoder().encodeToString(imagemArtista);

                Artista artista = new Artista(artistaId,nomeArtista,descricaoArtista, base64ImagemArtista);
                artistaList.add(artista);

                String nomeMusica = resultSet.getString("descricao");
                Musica musica = new Musica(nomeMusica);
                musicaList.add(musica);

                album.setArtistas(artistaList);
                artista.setMusicas(musicaList);
            }


            System.out.println("success in select * from album and joins");

            connection.close();

            return albumList;

        } catch (Exception e) {

            System.out.println("fail in database connection");

            return Collections.emptyList();

        }

    }


    public List<Album> encontrarAlbumsPorGenero(String generoBusca){

        String SQL = "SELECT alb.id, alb.gravadora, alb.genero, alb.pais, alb.ano, alb.descricao, alb.imagem, art.nome, art.descricao, music.nome AS music_nome, music.ativo " +
                "FROM ALBUM alb " +
                "JOIN ARTISTA art ON alb.id = art.album_id " +
                "JOIN MUSICA music ON art.id = music.artista_id " +
                "WHERE alb.GENERO LIKE CONCAT('%', ?, '%') AND alb.ATIVO = 1 AND art.ativo = 1 AND music.ativo = 1";



        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, generoBusca);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Album> albumList = new ArrayList<>();
            List<Artista> artistaList = new ArrayList<>();
            List<Musica> musicaList = new ArrayList<>();

            while (resultSet.next()) {

                String albumId = resultSet.getString("id");

                String gravadora = resultSet.getString("gravadora");
                String genero = resultSet.getString("genero");
                String pais = resultSet.getString("pais");
                String ano = resultSet.getString("ano");
                String descricao = resultSet.getString("descricao");
                byte[] imagem = resultSet.getBytes("imagem");

                String base64Imagem = Base64.getEncoder().encodeToString(imagem);

                int converterAno = Integer.parseInt(ano);

                Album album = new Album(albumId, gravadora, genero, pais,converterAno ,descricao, base64Imagem);
                albumList.add(album);

                int artistaId = resultSet.getInt("id");

                String nomeArtista = resultSet.getString("nome");
                String descricaoArtista = resultSet.getString("descricao");

                byte[] imagemArtista = resultSet.getBytes("imagem");

                String  base64ImagemArtista = Base64.getEncoder().encodeToString(imagemArtista);

                Artista artista = new Artista(artistaId,nomeArtista,descricaoArtista, base64ImagemArtista);
                artistaList.add(artista);

                String nomeMusica = resultSet.getString("descricao");
                Musica musica = new Musica(nomeMusica);
                musicaList.add(musica);

                album.setArtistas(artistaList);
                artista.setMusicas(musicaList);
            }


            System.out.println("success in select * from album and joins");

            connection.close();

            return albumList;

        } catch (Exception e) {

            System.out.println("fail in database connection");

            return Collections.emptyList();

        }

    }



    public void deleteAlbumById(String albumId){
        String SQL = "UPDATE ALBUM SET ATIVO = 0 WHERE ID = (?)";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, albumId);
            preparedStatement.execute();

            System.out.println("success on delete album with id: " + albumId);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }

    }

    public static void atualizarAlbum(Album albumUpdate) {
        String albumUpdateSQL = "UPDATE ALBUM SET GRAVADORA = ?, GENERO = ?, PAIS = ?, ANO = ?, DESCRICAO = ? WHERE ATIVO = 1 AND ID = ?";
        String artistUpdateSQL = "UPDATE ARTISTA SET NOME = ?, DESCRICAO = ?, IMAGEM = ? WHERE ATIVO = 1 AND ALBUM_ID = ?";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            System.out.println("Success in database connection");

            PreparedStatement albumStatement = connection.prepareStatement(albumUpdateSQL);
            albumStatement.setString(1, albumUpdate.getGravadora());
            albumStatement.setString(2, albumUpdate.getGenero());
            albumStatement.setString(3, albumUpdate.getPais());
            albumStatement.setInt(4, albumUpdate.getAno());
            albumStatement.setString(5, albumUpdate.getDescricao());
            albumStatement.setString(6, albumUpdate.getId()); // ALBUM ID PK

            albumStatement.executeUpdate();
            System.out.println("Success in updating album");

            PreparedStatement artistStatement = connection.prepareStatement(artistUpdateSQL);
            artistStatement.setString(1, albumUpdate.getArtista());
            artistStatement.setString(2, albumUpdate.getDescricao());
            artistStatement.setBytes(3, albumUpdate.getImagem());
            artistStatement.setString(4, albumUpdate.getId()); // ALBUM ID FK

            artistStatement.executeUpdate();
            System.out.println("Success in updating artist");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Fail in database connection");
            System.out.println("Error: " + e.getMessage());
        }
    }

}


