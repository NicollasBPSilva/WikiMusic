<%@ page import="br.com.carsoft.model.Album.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.carsoft.model.Album.Artista" %>
<%@ page import="br.com.carsoft.model.Album.Musica" %>
<%@ page import="java.util.Set" %>
<%@ page import="br.com.carsoft.model.Album.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.carsoft.model.Album.Artista" %>
<%@ page import="br.com.carsoft.model.Album.Musica" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WikiMusic</title>
    <link rel="stylesheet" href="css/album.css">
    <script src="js/albums.js"></script>
</head>

<body>
<header class="header">
    <div class="header-buttons">
        <button class="active">Home</button>
        <form action="/encontrar-albums" class="button" method="get">
            <select name="genero" id="genero">
                <option value="rock">Rock</option>
                <option value="sertanejo">Sertanejo</option>
                <option value="funk">Funk</option>
            </select>

            <label for="nome">Artista</label>
            <input type="text" name="nome" id="nome" class="-">

            <button type="submit">Buscar</button>
        </form>
        <button onclick="redirecionarArtista()">Artistas</button>

    </div>
    <a class="ancor-login" href="login.jsp">
        <button class="button-login">
            Login
        </button>
    </a>
</header>
<main>
    <aside class="sidebar">
        <h1 for="nome">ALBUMS</h1>
        <%
            List<Album> albums = (List<Album>) request.getAttribute("albums");
            String generoSelecionado = (String) request.getAttribute("genero");
            if (albums != null) {
                for (Album album : albums) {
                    if (generoSelecionado == null || generoSelecionado.isEmpty() || album.getGenero().equalsIgnoreCase(generoSelecionado)) {
        %>

        <div class="imagem">
            <img src="data:image/jpg;base64,<%= album.getImagemBase() %>" height="300px" width="500px">
        </div>
        <p>Nome: <%= album.getDescricao() %></p>
        <p>Ano: <%= album.getAno() %></p>
        <p>Gravadora: <%= album.getGravadora() %></p>
        <p>Gênero: <%= album.getGenero() %></p>
        <p>País: <%= album.getPais() %></p>
        <%
                    }
                }
            }
        %>
    </aside>
    <div class="main-content">
        <h2>ARTISTAS</h2>
        <%
            if (albums != null) {
                for (Album album : albums) {
                    if (generoSelecionado == null || generoSelecionado.isEmpty() || album.getGenero().equalsIgnoreCase(generoSelecionado)) {
                        Set<Artista> artistas = album.getArtistas();
                        if (artistas != null && !artistas.isEmpty()) {
                            for (Artista artista : artistas) {
        %>
        <div class="artista">
            <div class="container">
                <img src="data:image/jpg;base64,<%= artista.getArtistaImagemBase64() %>" height="500px">
                <p>Nome do Artista: <%= artista.getNomeArtista() %></p>
                <p>Descrição do Artista: <%= artista.getDescricaoArtista() %></p>
                <h3>Músicas</h3>
                <%
                    List<Musica> musicas = artista.getMusicas();
                    if (musicas != null && !musicas.isEmpty()) {
                        for (Musica musica : musicas) {
                %>
                <div class="musica">
                    <p>Nome da Música: <%= musica.getMusica() %></p>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%
                            }
                        }
                    }
                }
            }
        %>
    </div>
</main>
<footer>
    <button>WikiMusic</button>
    <p class="footer">Sua biblioteca de música</p>
</footer>
</body>
</html>


