package br.com.carsoft.servlet;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adicionaralbum")
public class CreateAlbumServlet extends HttpServlet {

        protected void doPost(HttpServletRequest servletRequest, HttpServletResponse response) throws ServletException, IOException {

            String albumId = servletRequest.getParameter("id");

            String titulo = servletRequest.getParameter("titulo");
            String artista = servletRequest.getParameter("artista");
            String album = servletRequest.getParameter("album");
            String informacoes = servletRequest.getParameter("informacoes");


            Album albumClass = new Album(titulo, artista, album, informacoes);


            new AlbumDao().albumAdicionar(albumClass);

            response.sendRedirect("/encontrar-Albums");
        }



}


