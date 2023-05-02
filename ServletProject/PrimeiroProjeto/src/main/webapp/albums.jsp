<%@ page import="br.com.carsoft.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>
<style>
    body {
        background-color: #f4f4f4;
        font-family: Arial, sans-serif;
        text-align: center;
    }

    h1 {
        color: #444;
        font-size: 36px;
        margin-top: 40px;
    }

    #album-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        margin-top: 40px;
    }

    .album {
        background-color: #fff;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0px 0px 5px #ccc;
        margin: 20px;
        padding: 20px;
        text-align: left;
        width: 400px;
    }

    .album h2 {
        color: #444;
        font-size: 28px;
        margin-top: 0;
    }

    .album p {
        color: #666;
        font-size: 20px;
        margin-bottom: 10px;
        margin-top: 0;
    }

    .album strong {
        color: #444;
        font-weight: bold;
    }
</style>
<div>
    <h1>Albums</h1>

    <div id="album-list">
        <% List<Album> albums = (List<Album>) request.getAttribute("albums");
            for (Album album : albums) { %>
        <div class="album">
            <h2><%= album.getId() %></h2>
            <h2><%= album.getTitulo() %></h2>
            <p><strong>Artista:</strong> <%= album.getArtista() %></p>
            <p><strong>Album:</strong> <%= album.getAlbum() %></p>
            <p><strong>Informacoes:</strong> <%= album.getInformacoes() %></p>
            <form action="/delete-album" method="post">
                <input type="hidden" id="id" name="id" value="  <%= album.getId() %>">
                <button type="submit">Delete</button>
            </form>

        </div>
        <% } %>
    </div>

    <button id="next-album">Próximo Álbum</button>

    <script>
        // seleciona todos os elementos com a classe "album"
        const albums = document.querySelectorAll(".album");

        // mantém um registro do índice do álbum atualmente visível
        let currentIndex = 0;

        // esconde todos os álbuns, exceto o primeiro
        for (let i = 1; i < albums.length; i++) {
            albums[i].style.display = "none";
        }

        // adiciona um ouvinte de eventos ao botão "Próximo Álbum"
        document.querySelector("#next-album").addEventListener("click", () => {
            // oculta o álbum atual
            albums[currentIndex].style.display = "none";

            // atualiza o índice para o próximo álbum
            currentIndex++;

            // se chegou ao final da lista de álbuns, volta para o primeiro
            if (currentIndex >= albums.length) {
                currentIndex = 0;
            }

            // exibe o álbum atual
            albums[currentIndex].style.display = "block";
        });
    </script>
</div>
</body>
</html>