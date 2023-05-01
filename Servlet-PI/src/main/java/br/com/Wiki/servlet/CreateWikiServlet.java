package br.com.Wiki.servlet;

import br.com.Wiki.dao.WikiDao;
import br.com.Wiki.model.Artista;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-artista")
public class CreateWikiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String nomeArtista = req.getParameter("artist-name");
        String nomeMusica = req.getParameter("music-name");

        Artista artista = new Artista(nomeArtista, nomeMusica);

        new WikiDao().createArtista(artista);

        req.getRequestDispatcher("index.html").forward(req, resp);

    }
}
