package br.com.carsoft.servlet.Album.Admin;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album.Album;
import br.com.carsoft.model.Album.Artista;
import br.com.carsoft.model.Album.Musica;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@WebServlet({"/adicionaralbum", "/admin/adicionaralbum"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 80, // 80 MB
        maxFileSize = 1024 * 1024 * 400, // 400 MB
        maxRequestSize = 1024 * 1024 * 800 // 800 MB
)
public class CreateAlbumServlet extends HttpServlet {

        protected void doPost(HttpServletRequest servletRequest, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = servletRequest.getSession();
            String loggedUser = (String) session.getAttribute("loggedUser");
            if (loggedUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            //Informações do Album
            String albumId = servletRequest.getParameter("id");
            String gravadora = servletRequest.getParameter("gravadora");
            String pais = servletRequest.getParameter("pais");
            String ano = servletRequest.getParameter("ano");
            String descricaoAlbum = servletRequest.getParameter("descricaoAlbum");


            // Conversão do valor do parâmetro "ano" para o tipo correto
            int anoConversao = Integer.parseInt(ano);

             String genero = "";

            String generoInput = servletRequest.getParameter("genero");

            String generoConvertido = converterGenero(generoInput);


            Part imagemPart = servletRequest.getPart("imagemAlbum");
            InputStream imagemStream = imagemPart.getInputStream();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = imagemStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            byte[] imagemAlbum = output.toByteArray();

            ///////////////

            Part imagemPartArtista = servletRequest.getPart("imagemArtista");
            InputStream albumImagemStream = imagemPartArtista.getInputStream();

            ByteArrayOutputStream albumOutput = new ByteArrayOutputStream();
            byte[] albumBuffer = new byte[4096];
            int albumBytesRead;
            while ((albumBytesRead = albumImagemStream.read(albumBuffer)) != -1) {
                albumOutput.write(albumBuffer, 0, albumBytesRead);
            }
            byte[] artistaImagem = albumOutput.toByteArray();


            int ativo = 1;

            //Informações do Artista

            String nomeArtista = servletRequest.getParameter("nomeArtista");
            String descricaoArtista = servletRequest.getParameter("descricaoArtista");

            //Informações da musica

            String nomeMusica = servletRequest.getParameter("nomeMusica");


            Album albumClass = new Album(gravadora, generoConvertido, pais, anoConversao, descricaoAlbum, imagemAlbum, ativo);

            Artista artistaClass = new Artista(nomeArtista, descricaoArtista, artistaImagem, ativo);

            Musica musicaClass = new Musica(nomeMusica, ativo);

            new AlbumDao().albumAdicionar(albumClass, artistaClass, musicaClass);

            response.sendRedirect("albumsAdmin.jsp");
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


