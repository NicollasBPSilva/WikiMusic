package br.com.Wiki.servlet;

import br.com.Wiki.dao.WikiDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/delete-artista")
public class DeleteWikiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String artistaId = req.getParameter("id");

        new WikiDao().deleteArtistaById(artistaId);

        resp.sendRedirect("/find-all-artistas");

    }

}
