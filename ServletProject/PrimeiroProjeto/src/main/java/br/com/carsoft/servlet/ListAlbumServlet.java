package br.com.carsoft.servlet;

import br.com.carsoft.dao.MusicaDao;
import br.com.carsoft.model.Musica;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListAlbumServlet {

    @WebServlet("/encontrarAlbuns")
    public class ListCarServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<Musica> album = new MusicaDao().encontrarAlbum();

            req.setAttribute("albuns", album);

            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);


        }
    }
    }
