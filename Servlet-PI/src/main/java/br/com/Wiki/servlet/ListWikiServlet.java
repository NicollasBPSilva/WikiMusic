package br.com.Wiki.servlet;

import br.com.Wiki.dao.WikiDao;
import br.com.Wiki.model.Artista;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListWikiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException{

        List<Artista> artistas = new WikiDao().findAllArtistas();

        servletRequest.setAttribute("artistas", artistas);
        servletRequest.getRequestDispatcher("dashboard.html").forward(servletRequest, servletResponse);

        super.doGet(servletRequest, servletResponse);
    }
}
