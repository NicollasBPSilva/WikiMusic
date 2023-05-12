<%@ page import="br.com.carsoft.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
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
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    h1 {
        text-align: center;
        margin: 50px 0 20px;
    }

    #album-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .album {
        background-color: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        border-radius: 4px;
        width: 300px;
        margin: 20px;
        padding: 20px;
        text-align: center;
        position: relative;
    }

    .album h2 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
        color: #333;
    }

    .album p {
        margin: 10px 0 0;
        font-size: 16px;
        color: #666;
    }

    .album form {
        display: flex;
        flex-direction: column;
        margin-top: 20px;
    }

    .album label {
        font-size: 16px;
        color: #333;
        margin-bottom: 10px;
    }

    .album input {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
        margin-bottom: 20px;
    }

    .album button {
        padding: 10px 20px;
        background-color: #333;
        color: #fff;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .album button:hover {
        background-color: #555;
    }

    .album .delete {
        position: absolute;
        top: 10px;
        right: 10px;
        background-color: #ff4136;
        color: #fff;
        border: none;
        border-radius: 50%;
        width: 30px;
        height: 30px;
        font-size: 16px;
        line-height: 1;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .album .delete:hover {
        background-color: #c70000;
    }

    .edit-form {
        display:none;
    }

    .album {
        margin-bottom: 20px;
    }

    .edit-button {
        margin-top: 10px;
    }

    .container {
        width: 100%;
        height: auto;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .container img {
        max-width: 100%;
        max-height: 100%;
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
            <div class="container">
                <img src="data:image/jpg;base64,<%= album.getImagemBase() %>">
            </div>
            <form action="/delete-album" method="post">
                <input type="hidden" id="id" name="id" value="<%= album.getId() %>">
                <button type="submit">Delete</button>
            </form>
            <button class="edit-button" onclick="mostrarEdicao(<%= album.getId() %>)">Editar</button>

            <form action="/update-album" method="post" class="edit-form">
                <input type="hidden" name="id" value="<%= album.getId() %>">
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo">

                <label for="artista">Artista:</label>
                <input type="text" id="artista" name="artista">

                <label for="album">Álbum:</label>
                <input type="text" id="album" name="album">

                <label for="album">Artista:</label>
                <input type="text" id="informacoes" name="informacoes">

                <label for="album">Imagem:</label>
                <input type="file" id="imagem" name="imagem">

                <button type="submit">Update Album</button>
            </form>

        </div>
        <% } %>
    </div>
    <button id="anterior-album">Álbum anterior</button>
    <button id="proximo-album">Próximo Álbum</button>

    <script>

        const albums = document.querySelectorAll(".album");
        let indexAtual = 0;

        for (let i = 1; i < albums.length; i++) {
            albums[i].style.display = "none";
        }

        document.querySelector("#proximo-album").addEventListener("click", () => {
            albums[indexAtual].style.display = "none";

            indexAtual++;

            if (indexAtual >= albums.length) {
                indexAtual = 0;
            }

            albums[indexAtual].style.display = "block";
        });

        document.querySelector("#anterior-album").addEventListener("click", () => {
            albums[indexAtual].style.display = "none";

            indexAtual--;

            if (indexAtual < 0) {
                indexAtual = albums.length - 1;
            }

            albums[indexAtual].style.display = "block";
        });


    </script>
</div>
</body>
</html>