package br.com.carsoft.dao;
import br.com.carsoft.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
public class LoginDao {
    public static void loginUser(Login login){
        String SQL = "SELECT USERNAME, PASSWORD FROM LOGIN WHERE USERNAME = (?) AND PASSWORD = (?)";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, login.getName());
            preparedStatement.setString(2, login.getPassword());
            preparedStatement.execute();

            System.out.println(preparedStatement.getResultSet());
            connection.close();

            System.out.println("sucess in connection");


        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println(e);
        }
    }


}
