package br.com.carsoft.servlet;

import br.com.carsoft.dao.LoginDao;

import br.com.carsoft.model.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class CreateLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse response) throws ServletException, IOException {

        String loginUser = servletRequest.getParameter("username");
        String loginPassword = servletRequest.getParameter("password");
        Login login = new Login();

        login.setName(loginUser);
        login.setPassword(loginPassword);

        LoginDao loginDao = new LoginDao();
        loginDao.createUser(login);
        loginDao.loginUser(login);


        servletRequest.getRequestDispatcher("index.html").forward(servletRequest, response);
    }
}