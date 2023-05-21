package br.com.carsoft.servlet;

import br.com.carsoft.dao.AlbumDao;
import br.com.carsoft.model.Album;
import br.com.carsoft.model.Artista;
import br.com.carsoft.model.Musica;

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
            //Informações do Album
            String albumId = servletRequest.getParameter("id");
            String gravadora = servletRequest.getParameter("gravadora");
            String pais = servletRequest.getParameter("pais");
            String ano = servletRequest.getParameter("ano");
            String descricaoAlbum = servletRequest.getParameter("descricaoAlbum");


            // Conversão do valor do parâmetro "ano" para o tipo correto
            int anoConversao = Integer.parseInt(ano);

             String genero = "";

            int generoInput = Integer.parseInt(servletRequest.getParameter("genero"));

            switch(generoInput){
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


            Part imagemPart = servletRequest.getPart("imagem");
            InputStream imagemStream = imagemPart.getInputStream();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = imagemStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            byte[] imagemBytes = output.toByteArray();

            int ativo = 1;

            //Informações do Artista

            String nomeArtista = servletRequest.getParameter("nomeArtista");
            String descricaoArtista = servletRequest.getParameter("descricaoArtista");

            //Informações da musica

            String nomeMusica = servletRequest.getParameter("nomeMusica");


            Album albumClass = new Album(gravadora, genero, pais, anoConversao, descricaoAlbum, imagemBytes, ativo);

            Artista artistaClass = new Artista(nomeArtista, descricaoArtista, ativo);

            Musica musicaClass = new Musica(nomeMusica, ativo);

            new AlbumDao().albumAdicionar(albumClass, artistaClass, musicaClass);

            response.sendRedirect("/encontrar-albums");
        }



}


