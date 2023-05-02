package br.com.carsoft.servlet;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
    @WebServlet("/encontrar-albums")
    public class ListAlbumServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<Album> albums = new AlbumDao().encontrarAlbum();

            req.setAttribute("albums", albums);

            req.getRequestDispatcher("albums.jsp").forward(req, resp);


        }
    }
