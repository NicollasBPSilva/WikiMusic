package br.com.carsoft.servlet.UserServlet;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album.Album;
import br.com.carsoft.model.Album.Artista;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/encontrar-artistas"})
public class ListArtistaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genero = req.getParameter("genero");
        String artista = req.getParameter("artistaNome");

        List<Artista> artistaList;
        if (artista == null) {
            artistaList = new AlbumDao().encontrarArtistaPorGenero(genero);
        } else {
            artistaList = new AlbumDao().encontrarArtistaPorGenero(genero, artista);
        }

        req.setAttribute("artista", artistaList);
        req.setAttribute("generoSelecionado", genero);
        req.setAttribute("nome", artista);

        req.getRequestDispatcher("artistas.jsp").forward(req, resp);
    }
}
