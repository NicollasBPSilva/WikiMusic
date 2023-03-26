package br.com.carsoft;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-car")
public class CreacCarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse resp) throws ServletException, IOException {

        String carName = servletRequest.getParameter("car");

        System.out.println(carName);

        servletRequest.getRequestDispatcher("index.html").forward(servletRequest, resp);
    }
}
