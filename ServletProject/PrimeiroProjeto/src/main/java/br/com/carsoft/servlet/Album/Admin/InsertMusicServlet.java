package br.com.carsoft.servlet.Album.Admin;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album.Album;
import br.com.carsoft.model.Album.Musica;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/adicionarMusica", "/admin/adicionarMusica"})
public class InsertMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

        HttpSession session = req.getSession();
        String loggedUser = (String) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int artistaId = Integer.parseInt(req.getParameter("artistaAdicionar"));
        String musica = req.getParameter("musicaArtista");

    int ativo = 1;
        Musica musicaClass = new Musica(musica, ativo);


        new AlbumDao().inserirMusicaRerefenteUltimoArtista(musicaClass, artistaId);
        resp.sendRedirect("albumsAdmin.jsp");


    }
}