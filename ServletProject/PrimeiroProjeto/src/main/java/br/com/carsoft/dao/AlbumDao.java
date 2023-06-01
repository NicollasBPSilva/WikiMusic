package br.com.carsoft.dao;

import br.com.carsoft.model.Album.Album;
import br.com.carsoft.model.Album.Artista;
import br.com.carsoft.model.Album.Musica;

import java.sql.*;
import java.util.*;

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

    public static void inserirMusicaRerefenteUltimoArtista(Musica musica, int artistaId) {
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

    public List<Artista> encontrarArtistaPorGenero(String generoArtista, String artista) {
        String SQL = "SELECT art.id AS artista_id, art.nome AS artista_nome, art.descricao AS artista_descricao, art.imagem AS artista_imagem, music.nome AS musica_nome, music.ativo AS musica_ativo " +
                "FROM ARTISTA art " +
                "JOIN ALBUM alb ON alb.id = art.album_id " +
                "JOIN MUSICA music ON music.artista_id = art.id " +
                "WHERE alb.GENERO LIKE CONCAT('%', ?, '%') AND art.nome LIKE CONCAT('%', ?, '%') AND art.ativo = 1 AND music.ativo = 1";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            System.out.println("Success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, generoArtista);
            preparedStatement.setString(2, artista);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Integer, Artista> artistaMap = new HashMap<>();

            while (resultSet.next()) {
                int artistaId = resultSet.getInt("artista_id");
                String nomeArtista = resultSet.getString("artista_nome");
                String descricaoArtista = resultSet.getString("artista_descricao");
                byte[] imagemArtista = resultSet.getBytes("artista_imagem");
                String base64ImagemArtista = Base64.getEncoder().encodeToString(imagemArtista);

                Artista artistaAdd = artistaMap.get(artistaId);
                if (artistaAdd == null) {
                    artistaAdd = new Artista(artistaId, nomeArtista, descricaoArtista, base64ImagemArtista);
                    artistaMap.put(artistaId, artistaAdd);
                }

                String nomeMusica = resultSet.getString("musica_nome");
                int ativoMusica = resultSet.getInt("musica_ativo");
                Musica musica = new Musica(nomeMusica, ativoMusica);
                artistaAdd.addMusica(musica);
            }

            System.out.println("Success in executing the SQL query");

            connection.close();

            return new ArrayList<>(artistaMap.values());
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
            return Collections.emptyList();
        }
    }

    public List<Artista> encontrarArtistaPorGenero() {
        String SQL = "SELECT art.id AS artista_id, art.nome AS artista_nome, art.descricao AS artista_descricao, art.imagem AS artista_imagem, music.nome AS musica_nome, music.ativo AS musica_ativo " +
                "FROM ARTISTA art " +
                "JOIN ALBUM alb ON alb.id = art.album_id " +
                "JOIN MUSICA music ON music.artista_id = art.id " +
                "WHERE art.ativo = 1 AND music.ativo = 1";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            System.out.println("Success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);



            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Integer, Artista> artistaMap = new HashMap<>();

            while (resultSet.next()) {
                int artistaId = resultSet.getInt("artista_id");
                String nomeArtista = resultSet.getString("artista_nome");
                String descricaoArtista = resultSet.getString("artista_descricao");
                byte[] imagemArtista = resultSet.getBytes("artista_imagem");
                String base64ImagemArtista = Base64.getEncoder().encodeToString(imagemArtista);

                Artista artista = artistaMap.get(artistaId);
                if (artista == null) {
                    artista = new Artista(artistaId, nomeArtista, descricaoArtista, base64ImagemArtista);
                    artistaMap.put(artistaId, artista);
                }

                String nomeMusica = resultSet.getString("musica_nome");
                int ativoMusica = resultSet.getInt("musica_ativo");
                Musica musica = new Musica(nomeMusica, ativoMusica);
                artista.addMusica(musica);
            }

            System.out.println("Success in executing the SQL query");

            connection.close();

            return new ArrayList<>(artistaMap.values());
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
            return Collections.emptyList();
        }
    }




    public List<Album> encontrarAlbumsPorGenero(String generoBusca, String nomeAlbum) {
        String SQL = "SELECT alb.id, alb.gravadora, alb.genero, alb.pais, alb.ano, alb.descricao, alb.imagem, art.id AS artista_id, art.nome, art.descricao, art.imagem AS artista_imagem, music.nome AS music_nome, music.ativo " +
                "FROM ALBUM alb " +
                "JOIN ARTISTA art ON alb.id = art.album_id " +
                "JOIN MUSICA music ON art.id = music.artista_id " +
                "WHERE alb.GENERO LIKE CONCAT('%', ?, '%') AND alb.DESCRICAO LIKE CONCAT('%', ?, '%') AND alb.ATIVO = 1 AND art.ativo = 1 AND music.ativo = 1";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            System.out.println("Success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, generoBusca);
            preparedStatement.setString(2, nomeAlbum);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String, Album> albumMap = new HashMap<>();

            while (resultSet.next()) {
                String albumId = resultSet.getString("id");
                Album album = albumMap.get(albumId);
                if (album == null) {
                    String gravadora = resultSet.getString("gravadora");
                    String genero = resultSet.getString("genero");
                    String pais = resultSet.getString("pais");
                    String ano = resultSet.getString("ano");
                    String descricao = resultSet.getString("descricao");
                    byte[] imagem = resultSet.getBytes("imagem");
                    String base64Imagem = Base64.getEncoder().encodeToString(imagem);
                    int converterAno = Integer.parseInt(ano);

                    album = new Album(albumId, gravadora, genero, pais, converterAno, descricao, base64Imagem);
                    albumMap.put(albumId, album);
                }

                int artistaId = resultSet.getInt("artista_id");
                String nomeArtista = resultSet.getString("nome");
                String descricaoArtista = resultSet.getString("descricaoArtista");
                byte[] imagemArtista = resultSet.getBytes("artista_imagem");
                String base64ImagemArtista = Base64.getEncoder().encodeToString(imagemArtista);

                Artista artista = album.getArtistaById(artistaId);
                if (artista == null) {
                    artista = new Artista(artistaId, nomeArtista, descricaoArtista, base64ImagemArtista);
                    album.addArtista(artista);
                }

                String nomeMusica = resultSet.getString("music_nome");
                int ativoMusica = resultSet.getInt("ativo");
                Musica musica = new Musica(nomeMusica, ativoMusica);
                artista.addMusica(musica);
            }

            System.out.println("Success in executing the SQL query");

            connection.close();

            return new ArrayList<>(albumMap.values());
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
            return Collections.emptyList();
        }
    }


    public List<Album> encontrarAlbumsPorGenero() {
        String SQL = "SELECT alb.id, alb.gravadora, alb.genero, alb.pais, alb.ano, alb.descricao, alb.imagem, art.id AS artista_id, art.nome, art.descricao AS art_descricao, art.imagem AS artista_imagem, music.nome AS music_nome, music.ativo " +
                "FROM ALBUM alb " +
                "JOIN ARTISTA art ON alb.id = art.album_id " +
                "JOIN MUSICA music ON art.id = music.artista_id " +
                " WHERE alb.ATIVO = 1 AND art.ativo = 1 AND music.ativo = 1";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            System.out.println("Success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String, Album> albumMap = new HashMap<>();

            while (resultSet.next()) {
                String albumId = resultSet.getString("id");
                Album album = albumMap.get(albumId);
                if (album == null) {
                    String gravadora = resultSet.getString("gravadora");
                    String genero = resultSet.getString("genero");
                    String pais = resultSet.getString("pais");
                    String ano = resultSet.getString("ano");
                    String descricao = resultSet.getString("descricao");
                    byte[] imagem = resultSet.getBytes("imagem");
                    String base64Imagem = Base64.getEncoder().encodeToString(imagem);
                    int converterAno = Integer.parseInt(ano);

                    album = new Album(albumId, gravadora, genero, pais, converterAno, descricao, base64Imagem);
                    albumMap.put(albumId, album);
                }

                int artistaId = resultSet.getInt("artista_id");
                String nomeArtista = resultSet.getString("nome");
                String descricaoArtista = resultSet.getString("art_descricao");
                byte[] imagemArtista = resultSet.getBytes("artista_imagem");
                String base64ImagemArtista = Base64.getEncoder().encodeToString(imagemArtista);

                Artista artista = album.getArtistaById(artistaId);
                if (artista == null) {
                    artista = new Artista(artistaId, nomeArtista, descricaoArtista, base64ImagemArtista);
                    album.addArtista(artista);
                }

                String nomeMusica = resultSet.getString("music_nome");
                int ativoMusica = resultSet.getInt("ativo");
                Musica musica = new Musica(nomeMusica, ativoMusica);
                artista.addMusica(musica);
            }

            System.out.println("Success in executing the SQL query");

            connection.close();

            return new ArrayList<>(albumMap.values());
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
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
//        String artistUpdateSQL = "UPDATE ARTISTA SET NOME = ?, DESCRICAO = ?, IMAGEM = ? WHERE ATIVO = 1 AND ALBUM_ID = ?";

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

//            PreparedStatement artistStatement = connection.prepareStatement(artistUpdateSQL);
//            artistStatement.setString(1, albumUpdate.getArtista());
//            artistStatement.setString(2, albumUpdate.getDescricao());
//            artistStatement.setBytes(3, albumUpdate.getImagem());
//            artistStatement.setString(4, albumUpdate.getId()); // ALBUM ID FK
//
//            artistStatement.executeUpdate();
//            System.out.println("Success in updating artist");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Fail in database connection");
            System.out.println("Error: " + e.getMessage());
        }
    }


}


