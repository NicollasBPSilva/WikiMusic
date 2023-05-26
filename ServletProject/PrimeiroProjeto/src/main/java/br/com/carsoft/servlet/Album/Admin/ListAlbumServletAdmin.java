package br.com.carsoft.servlet.Album.Admin;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/albums-admin", "/admin/albums-admin"})
    public class ListAlbumServletAdmin extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            HttpSession session = req.getSession();
            String loggedUser = (String) session.getAttribute("loggedUser");
            if (loggedUser == null) {
                resp.sendRedirect("login.jsp");
                return;
            }

            String genero = req.getParameter("genero");
            String nomeAlbum = req.getParameter("nomeAlbum");

            List<Album> albums = new AlbumDao().encontrarAlbumsPorGenero(genero, nomeAlbum);

            Map<String, Album> albumMap = new HashMap<>();

            for (Album album : albums) {
                String albumId = album.getId();
                if (!albumMap.containsKey(albumId)) {
                    albumMap.put(albumId, album);
                }
            }

            req.setAttribute("albums", albums);
            req.setAttribute("generoSelecionado", genero);
            req.setAttribute("nomeAlbum", nomeAlbum);

            String destino = "albumsAdmin.jsp";
            req.getRequestDispatcher(destino).forward(req, resp);

        }

    }