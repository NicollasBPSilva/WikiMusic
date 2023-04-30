package br.com.carsoft.dao;

import br.com.carsoft.model.Album;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicaDao {

    public static void albumAdicionar(Album album){
        String SQL = "INSERT INTO ALBUM (TITULO,ARTISTA,ALBUM) VALUES ((?), (?), (?))";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, album.getTitulo());
            preparedStatement.setString(2, album.getArtista());
            preparedStatement.setString(3, album.getAlbum());
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

                String album = resultSet.getString("titulo");
                String titulo= resultSet.getString("artista");
                String artista = resultSet.getString("album");

                Album musica = new Album(album, titulo, artista);

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

}
