package br.com.carsoft.dao;

import br.com.carsoft.model.Album;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Base64;

public class AlbumDao {

    public static void albumAdicionar(Album album){
        String SQL = "INSERT INTO ALBUM (TITULO,ARTISTA,ALBUM, INFORMACOES, IMAGEM) VALUES ((?), (?), (?), (?), (?))";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, album.getTitulo());
            preparedStatement.setString(2, album.getArtista());
            preparedStatement.setString(3, album.getAlbum());
            preparedStatement.setString(4, album.getInformacoes());
            preparedStatement.setBytes(5, album.getImagem());

            preparedStatement.execute();

            connection.close();

            System.out.println("sucess in connection");


        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
    }

    public List<Album> encontrarAlbum(){
    String SQL = "SELECT * FROM ALBUM";
        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Album> albumList = new ArrayList<>();

            while (resultSet.next()) {

                String albumId = resultSet.getString("id");

                String album = resultSet.getString("titulo");
                String titulo= resultSet.getString("artista");
                String artista = resultSet.getString("album");
                String informacoes = resultSet.getString("informacoes");
                byte[] imagem = resultSet.getBytes("imagem");

                String base64Imagem = Base64.getEncoder().encodeToString(imagem);

                Album musica = new Album(albumId, album, titulo, artista, informacoes, base64Imagem);
                albumList.add(musica);


            }

            System.out.println("success in select * from album");

            connection.close();

            return albumList;

        } catch (Exception e) {

            System.out.println("fail in database connection");

            return Collections.emptyList();

        }

    }

    public void deleteAlbumById(String albumId){
        String SQL = "DELETE ALBUM WHERE ID = (?)";
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

    public static void atualizarAlbum(Album albumUpdate){
        String SQL = "UPDATE ALBUM SET TITULO = ?, ARTISTA = ?, ALBUM = ?, INFORMACOES = ?, IMAGEM = ? WHERE ID = ?";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, albumUpdate.getTitulo());
            preparedStatement.setString(2, albumUpdate.getArtista());
            preparedStatement.setString(3, albumUpdate.getAlbum());
            preparedStatement.setString(4, albumUpdate.getInformacoes());
            preparedStatement.setBytes(5, albumUpdate.getImagem());
            preparedStatement.setString(6, albumUpdate.getId());

            preparedStatement.execute();

            System.out.println("success in update album");

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");
            System.out.println("Error: " + e.getMessage());

        }
    }

}
