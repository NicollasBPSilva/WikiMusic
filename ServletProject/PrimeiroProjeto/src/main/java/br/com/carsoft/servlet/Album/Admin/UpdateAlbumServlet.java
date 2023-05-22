package br.com.carsoft.servlet.Album.Admin;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet({"/editar-album", "/admin/find-all-cars"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 80, // 80 MB
        maxFileSize = 1024 * 1024 * 400, // 400 MB
        maxRequestSize = 1024 * 1024 * 800 // 800 MB
)
public class UpdateAlbumServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();
        String loggedUser = (String) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String albumId = servletRequest.getParameter("id");

        String gravadora  = servletRequest.getParameter("gravadora");
        String generoEditar  = servletRequest.getParameter("generoEditar");
        String pais  = servletRequest.getParameter("pais");
        String ano  = servletRequest.getParameter("ano");
        String descricaoAlbum  = servletRequest.getParameter("descricaoAlbum");


        //ARTISTA
        String artista  = servletRequest.getParameter("artista");
        String descricaoArtista  = servletRequest.getParameter("descricaoArtista");


        int converterAno =  Integer.parseInt(ano);


        String generoConvertido = converterGenero(generoEditar);

        Part imagemPart = servletRequest.getPart("imagem");
        InputStream imagemStream = imagemPart.getInputStream();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = imagemStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        byte[] imagemBytes = output.toByteArray();

        Album albumClass = new Album(albumId, gravadora, generoConvertido, pais, converterAno,descricaoAlbum, artista,descricaoArtista, imagemBytes);


        new AlbumDao().atualizarAlbum(albumClass);

        response.sendRedirect("/encontrar-albums");
    }

    private String converterGenero (String generoInput) {
        String genero = "";

        int generoValue = Integer.parseInt(generoInput);

        switch (generoValue) {
            case 1:
                genero = "rock";
                break;
            case 2:
                genero = "sertanejo";
                break;
            case 3:
                genero = "funk";
                break;
        }
        return genero;
    }


}