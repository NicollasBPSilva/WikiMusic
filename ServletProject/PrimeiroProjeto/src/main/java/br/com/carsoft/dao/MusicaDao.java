package br.com.carsoft.dao;

import br.com.carsoft.model.Login;
import br.com.carsoft.model.Musica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicaDao {

    public static void albumAdicionar(Musica musica){
        String SQL = "INSERT INTO ALBUM (TITULO,ARTISTA,ALBUM) VALUES ((?), (?), (?))";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, musica.getTitulo());
            preparedStatement.setString(2, musica.getArtista());
            preparedStatement.setString(3, musica.getAlbum());
            preparedStatement.execute();

            connection.close();

            System.out.println("sucess in connection");


        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
    }

    public List<Musica> encontrarAlbum(){
    String SQL = "SELECT * FROM ALBUM";
        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Musica> musicaList = new ArrayList<>();

            while (resultSet.next()) {

                String albumName = resultSet.getString("albumName");
                String tituloName= resultSet.getString("tituloName");
                String artistaName = resultSet.getString("artistaName");

                Musica musica = new Musica(albumName, tituloName, artistaName);

                musicaList.add(musica);

            }

            System.out.println("success in select * car");

            connection.close();

            return musicaList;

        } catch (Exception e) {

            System.out.println("fail in database connection");

            return Collections.emptyList();

        }

    }

}
