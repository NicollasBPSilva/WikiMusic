package br.com.carsoft.servlet;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editar-album")
public class UpdateAlbumServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse response) throws ServletException, IOException {

        String albumId = servletRequest.getParameter("id");

        String gravadora  = servletRequest.getParameter("gravadora");
        String genero  = servletRequest.getParameter("genero");
        String pais  = servletRequest.getParameter("pais");
        String ano  = servletRequest.getParameter("ano");
        String descricao  = servletRequest.getParameter("descricao");
        byte[] imagem = servletRequest.getParameter("imagem").getBytes();

        int converterAno =  Integer.parseInt(ano);

        Album albumClass = new Album(albumId, gravadora, genero, pais, converterAno,descricao, imagem);


        new AlbumDao().atualizarAlbum(albumClass);

        response.sendRedirect("/encontrar-albums");
    }
}