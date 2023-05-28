package br.com.carsoft.servlet.UserServlet;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/encontrar-albums"})
    public class ListAlbumServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            String genero = req.getParameter("genero");
            String nomeAlbum = req.getParameter("nome");

            if(nomeAlbum == ""){
                List<Album> albums = new AlbumDao().encontrarAlbumsPorGenero();

                req.setAttribute("albums", albums);
                req.setAttribute("generoSelecionado", genero);

                req.setAttribute("nome", nomeAlbum);

                req.getRequestDispatcher("albums.jsp").forward(req, resp);

            }

                List<Album> albums = new AlbumDao().encontrarAlbumsPorGenero(genero, nomeAlbum);

                req.setAttribute("albums", albums);
                req.setAttribute("generoSelecionado", genero);

                req.setAttribute("nome", nomeAlbum);

                req.getRequestDispatcher("albums.jsp").forward(req, resp);
            }


    }

