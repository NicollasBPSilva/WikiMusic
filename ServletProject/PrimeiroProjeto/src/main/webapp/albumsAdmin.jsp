<%@ page import="br.com.carsoft.model.Album.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="br.com.carsoft.model.Album.Artista" %>
<%@ page import="br.com.carsoft.model.Album.Musica" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
</head>
<body>
<style>

    #album-list {
        margin-bottom: 20px;
    }

    #album-list ul {
        display: flex;
        flex-direction: row;
        list-style-type: none;
        padding: 0;
        margin: 0;
    }

    #album-list li {
        margin-bottom: 20px;
    }

    .album {
        border: 1px solid #ccc;
        padding: 10px;
        background-color: #f9f9f9;
        width: 50%;
    }

    .album p {
        margin: 0;
        line-height: 1.5;
    }


    .container {
        margin-top: 10px;
    }

    .container img {
        max-width: 100%;
        height: auto;
    }

    form button[type="submit"] {
        background-color: #e74c3c;
        color: #fff;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
    }

    .container {
        width: 100%;
        height: auto;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .edit-form {
        display: none;
        flex-direction: column;
        margin-bottom: 20px;
    }

    .edit-form label {
        margin-bottom: 5px;
    }

    .edit-form input[type="text"] {
        padding: 5px;
        margin-bottom: 10px;
    }

    .edit-form button[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
    }

    .edit-form p {
        color: red;
        font-style: italic;
    }




</style>
<div>
    <% if (session.getAttribute("loggedUser") != null) { %>
    <span><%= session.getAttribute("loggedUser") %></span>
    <a href="/logout">Logout</a>
    <% } %>


    <h1>Albums</h1>

    <div id="album-list">
        <div>
            <label for="genero">Genero:</label>
            <select id="genero" >
                <option value="">Escolha o Genero</option>
                <option value="rock">Rock</option>
                <option value="sertanejo">Sertanejo</option>
                <option value="funk">Funk</option>
            </select>
            <p>Nome do Album</p>
            <input type="text" name="nomeAlbum" id="nomeAlbum">
        </div>
        <button onclick="filtrarPorGenero()">Buscar</button>

        <%
            List<Album> albums = (List<Album>) request.getAttribute("albums");
            String generoSelecionado = (String) request.getAttribute("genero");
            if (albums != null) {
                for (Album album : albums) {
                    if (generoSelecionado == null || generoSelecionado.isEmpty() || album.getGenero().equalsIgnoreCase(generoSelecionado)) {
        %>
        <div class="album">
            <div class="container">
                <img src="data:image/jpg;base64,<%= album.getImagemBase() %>">
            </div>
            <p>Nome</p>
            <p><%= album.getDescricao() %></p>
            <p>Ano</p>
            <p><%= album.getAno() %></p>
            <p>Gravadora</p>
            <p><%= album.getGravadora() %></p>
            <p>Genero</p>
            <p><%= album.getGenero() %></p>
            <p>Pais</p>
            <p><%= album.getPais() %></p>

            <h2>Artista</h2>
            <%
                List<Artista> artistas = album.getArtistas();
                if (artistas != null) {
                    for (Artista artista : artistas) {
            %>
            <div class="artista">
                <div class="container">
                    <img src="data:image/jpg;base64,<%= artista.getArtistaImagemBase64() %>">
                </div>
                <p>Nome do Artista</p>
                <p><%= artista.getNomeArtista() %></p>
                <p>Descrição do artista</p>
                <p><%= artista.getDescricaoArtista() %></p>


                <h3>Musicas</h3>
                <%
                    List<Musica> musicas = artista.getMusicas();
                    if (musicas != null) {
                        for (Musica musica : musicas) {
                %>
                <div class="musica">
                    <p>Nome da musica</p>
                    <p><%= musica.getMusica() %></p>
                </div>
                <%
                        }
                    }
                %>
            </div>
            <%
                    }
                }
            %>

            <button type="button" onclick="toggleEditForm(this)">Edit</button>
            <form action="/editar-album" method="post" class="edit-form" enctype="multipart/form-data">
                <input type="hidden" name="id" value="<%= album.getId() %>">
                <h1>ALBUM</h1>
                <label for="descricaoAlbum">Descrição:</label>
                <input type="text" id="descricaoAlbum" name="descricaoAlbum">

                <label for="ano">Ano:</label>
                <input type="int" id="ano" name="ano">

                <label for="gravadora">Gravadora:</label>
                <input type="text" id="gravadora" name="gravadora">

                <div>
                    <label for="generoEditar">Genero:</label>
                    <select id="generoEditar" name="generoEditar" onchange="filtroGeneroEditar()">
                        <option value="1">Rock</option>
                        <option value="2">Sertanejo</option>
                        <option value="3">Funk</option>
                    </select>
                </div>

                <label for="pais">Pais:</label>
                <input type="text" id="pais" name="pais">

                <h1>ARTISTA</h1>
                <label for="artista">Artista</label>
                <input type="text" id="artista" name="artista">

                <label for="descricaoArtista">Descrição</label>
                <input type="text" id="descricaoArtista" name="descricaoArtista">

                <label for="imagem">Imagem</label>
                <input type="file" id="imagem" name="imagem" required>

                <button type="submit">Update Album</button>

            </form>



            <form action="/delete-album" method="post">
                <input type="hidden" name="albumId" value="<%= album.getId() %>">
                <button type="submit">Delete</button>
            </form>

        </div>
        <%
                    }
                }
            }
        %>
    </div>
</div>

<script>
    function filtrarPorGenero() {
        var generoSelect = document.getElementById("genero");
        var generoSelecionado = generoSelect.options[generoSelect.selectedIndex].value;

        var nomeAlbumInput = document.getElementById("nomeAlbum");
        var nomeAlbum = nomeAlbumInput.value;

        var url = "/albums-admin";
        var params = [];

        if (generoSelecionado) {
            params.push("genero=" + generoSelecionado);
        }

        if (nomeAlbum) {
            params.push("nomeAlbum=" + encodeURIComponent(nomeAlbum));
        }

        if (params.length > 0) {
            url += "?" + params.join("&");
        }

        window.location.href = url;
    }


    function filtroGeneroEditar() {
        var generoSelect = document.getElementById("generoEditar");
        var generoSelecionado = generoSelect.options[generoSelect.selectedIndex].value;

    }

    function toggleEditForm(button) {
        var editForm = button.nextElementSibling;
        editForm.style.display = (editForm.style.display === "none") ? "flex" : "none";
    }




</script>
</div>
</body>
</html>