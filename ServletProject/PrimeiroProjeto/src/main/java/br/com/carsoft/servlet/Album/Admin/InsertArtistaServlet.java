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
@WebServlet({"/adicionarArtista", "/admin/adicionarArtista"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 80, // 80 MB
        maxFileSize = 1024 * 1024 * 400, // 400 MB
        maxRequestSize = 1024 * 1024 * 800 // 800 MB
)
public class InsertArtistaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

        HttpSession session = req.getSession();
        String loggedUser = (String) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }


        Part imagemPartArtista = req.getPart("imagemArtista");
        String artista = req.getParameter("artistaAdicionar");
        String descricao = req.getParameter("descricaoArtistaAdd");



        InputStream albumImagemStream = imagemPartArtista.getInputStream();

        ByteArrayOutputStream albumOutput = new ByteArrayOutputStream();
        byte[] albumBuffer = new byte[4096];
        int albumBytesRead;
        while ((albumBytesRead = albumImagemStream.read(albumBuffer)) != -1) {
            albumOutput.write(albumBuffer, 0, albumBytesRead);
        }
        byte[] artistaImagem = albumOutput.toByteArray();


        int artistaId = Integer.parseInt(req.getParameter("artistaId"));


        int ativo = 1;

        if(artista != null && descricao != null && descricao != null){
            Artista artistaClass  = new Artista(artista, descricao, artistaImagem, ativo);


            new AlbumDao().inserirArtistaRerefenteUltimoAlbum(artistaClass, artistaId);
        }


        resp.sendRedirect("albumsAdmin.jsp");


    }
}