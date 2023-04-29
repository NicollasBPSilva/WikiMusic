package br.com.Wiki.dao;

import br.com.Wiki.model.Artista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WikiDao {

    public void createArtista(Artista artista){
        String SQL = "INSERT INTO MUSICA (ARTISTA, MUSICA) VALUES (?, ?)";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, artista.getArtista());
            preparedStatement.setString(2, artista.getMusica());
            preparedStatement.execute();

            connection.close();

            System.out.println("sucess in connection");

        } catch (Exception e) {
            System.out.println("fail in connection");
            System.out.println(e.getMessage());
        }
    }

    public List<Artista> findAllArtistas(){
        String SQL = "SELECT * FROM ARTISTA, MUSICA";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Artista> artistas = new ArrayList<>();

            while (resultSet.next()) {
                String artistaId = resultSet.getString("id");
                String artistaNome = resultSet.getString("name");
                String musica = resultSet.getString("musica");

                Artista artista = new Artista(artistaId, artistaNome, musica);

                artistas.add(artista);
            }


            System.out.println("sucess in select * car");

            connection.close();

            return artistas;
        } catch (Exception e){
            System.out.println("fail in databese connection");

            return Collections.emptyList();
        }
    }

    public void deleteArtistaById(String artistaId) {

        String SQL = "DELETE ARTISTA WHERE ID = ?";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, artistaId);
            preparedStatement.execute();

            System.out.println("success on delete car with id: " + artistaId);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }
    }

}

