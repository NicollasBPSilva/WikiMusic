package br.com.carsoft.dao;
import br.com.carsoft.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
public class LoginDao {
    public static void createUser(Login login){
        String SQL = "INSERT INTO LOGIN (USERNAME), (PASSWORD) VALUES (?), (?)";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sasa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, login.getName());
            preparedStatement.setString(2, login.getPassword());
            preparedStatement.execute();

            connection.close();

            System.out.println("sucess in connection");

        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
    }

    public static void loginUser(Login login){
        String SQL = "SELECT (USER) FROM LOGIN WHERE (USERNAME) = (?)";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sasa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, login.getName());
            preparedStatement.setString(1, login.getPassword());
            preparedStatement.execute();

            connection.close();

            System.out.println("sucess in connection");

        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
    }
}
