package br.com.carsoft.servlet;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/adicionaralbum")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 80, // 80 MB
        maxFileSize = 1024 * 1024 * 400, // 400 MB
        maxRequestSize = 1024 * 1024 * 800 // 800 MB
)
public class CreateAlbumServlet extends HttpServlet {

        protected void doPost(HttpServletRequest servletRequest, HttpServletResponse response) throws ServletException, IOException {

            String albumId = servletRequest.getParameter("id");

            String titulo = servletRequest.getParameter("titulo");
            String artista = servletRequest.getParameter("artista");
            String album = servletRequest.getParameter("album");
            String informacoes = servletRequest.getParameter("informacoes");


            Part imagemPart = servletRequest.getPart("imagem");
            InputStream imagemStream = imagemPart.getInputStream();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = imagemStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            byte[] imagemBytes = output.toByteArray();

            Album albumClass = new Album(titulo, artista, album, informacoes, imagemBytes);


            new AlbumDao().albumAdicionar(albumClass);

            response.sendRedirect("/encontrar-albums");
        }



}


