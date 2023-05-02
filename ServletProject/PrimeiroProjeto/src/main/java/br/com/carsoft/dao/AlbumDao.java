package br.com.carsoft.dao;

import br.com.carsoft.model.Album;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumDao {

    public static void albumAdicionar(Album album){
        String SQL = "INSERT INTO ALBUM (TITULO,ARTISTA,ALBUM, INFORMACOES) VALUES ((?), (?), (?), (?))";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, album.getTitulo());
            preparedStatement.setString(2, album.getArtista());
            preparedStatement.setString(3, album.getAlbum());
            preparedStatement.setString(4, album.getInformacoes());
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


                Album musica = new Album(albumId, album, titulo, artista, informacoes);

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

            System.out.println("success on delete car with id: " + albumId);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }

    }

}
