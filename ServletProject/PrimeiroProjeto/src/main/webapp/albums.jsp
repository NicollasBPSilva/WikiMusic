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
        <form action="/encontrar-albums" class="button" method="get">
            <select name="genero" id="genero">
                <option value="rock">Rock</option>
                <option value="sertanejo">Sertanejo</option>
                <option value="funk">Funk</option>
            </select>

            <label for="nome">Album</label>
            <input type="text" name="nome" id="nome" class="-">


            <button type="submit">Buscar</button>
        </form>
        <button onclick="redirecionarArtista()">Artistas</button>
        <button onclick="redirecionarHome()">Home</button>
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
        <p>Nome</p>
        <p> <%= album.getDescricao() %></p>
        <p>Ano</p>
        <p> <%= album.getAno() %></p>
        <p>Gravadora</p>
        <p> <%= album.getGravadora() %></p>
        <p>Genero</p>
        <p><%= album.getGenero() %></p>
        <p>País</p>
        <p> <%= album.getPais() %></p>
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
                boolean artistsFound = false;
                for (Album album : albums) {
                    if (generoSelecionado == null || generoSelecionado.isEmpty() || album.getGenero().equalsIgnoreCase(generoSelecionado)) {
                        Set<Artista> artistas = album.getArtistas();
                        if (artistas != null && !artistas.isEmpty()) {
                            for (Artista artista : artistas) {
                                artistsFound = true;
        %>
        <div class="artista">
            <div class="container">
                <img src="data:image/jpg;base64,<%= artista.getArtistaImagemBase64() %>" height="500px">
                <p>Nome</p>
                <p> <%= artista.getNomeArtista() %></p>
                <p>Descrição do Artista</p>
                <p> <%= artista.getDescricaoArtista() %></p>
                <h3>Músicas</h3>
                <%
                    List<Musica> musicas = artista.getMusicas();
                    if (musicas != null && !musicas.isEmpty()) {
                        for (Musica musica : musicas) {
                %>
                <div class="musica">
                    Música
                    <p> <%= musica.getMusica() %></p>
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
            if (!artistsFound) {
        %>
        <div class="no-results">
            <p>Nenhum artista encontrado.</p>
        </div>
        <%
            }
        } else {
        %>
        <div class="no-results">
            <p>Nenhum álbum encontrado.</p>
        </div>
        <%
            }
        %>
    </div>
</main>
<footer>
    <p class="footer-text">WikiMusic</p>
    <p class="footer-text">Sua biblioteca de música</p>
</footer>
</body>
</html>


