package br.com.carsoft.servlet;

import br.com.carsoft.dao.LoginDao;

import br.com.carsoft.dao.MusicaDao;
import br.com.carsoft.model.Login;
import br.com.carsoft.model.Musica;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adicionarAlbum")
public class CreateAlbumServlet extends HttpServlet {

        protected void doPost(HttpServletRequest servletRequest, HttpServletResponse response) throws ServletException, IOException {


            String titulo = servletRequest.getParameter("titulo");
            String artista = servletRequest.getParameter("artista");
            String album = servletRequest.getParameter("album");

            Musica musicaClass = new Musica(titulo, artista, album);


            new MusicaDao().albumAdicionar(musicaClass);


            response.sendRedirect("/encontrarAlbuns");
        }



}