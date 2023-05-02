package br.com.carsoft.servlet;

import br.com.carsoft.dao.AlbumDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-album")
public class DeleteAlbumServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String albumId = req.getParameter("id");

       new AlbumDao().deleteAlbumById(albumId);

       resp.sendRedirect("/encontrar-albums");
    }

}
