<%@ page import="br.com.carsoft.model.Album.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="br.com.carsoft.model.Album.Artista" %>
<%@ page import="br.com.carsoft.model.Album.Musica" %>
<%@ page import="java.util.Set" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List" %>
<%@ page import="br.com.carsoft.model.Album.Artista" %>
<%@ page import="br.com.carsoft.model.Album.Musica" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WikiMusic</title>
    <link rel="stylesheet" href="css/artista.css">
    <script src="js/artistas.js"></script>
</head>

<body>
<header class="header">
    <div class="header-buttons">
        <form action="/encontrar-artistas" class="button" method="get">
            <select name="genero" id="genero">
                <option value="rock">Rock</option>
                <option value="sertanejo">Sertanejo</option>
                <option value="funk">Funk</option>
            </select>

            <label for="artistaNome">Artista</label>
            <input type="text" name="artistaNome" id="artistaNome" class="custom-input">

            <button type="submit">Buscar</button>
        </form>
        <form action="/encontrar-albums?" class="button" method="get">
            <button onclick="redirecionar()">Albums</button>
        </form>
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
        <% List<Artista> artistasList = (List<Artista>) request.getAttribute("artista");
            if (artistasList != null) {
                for (Artista artista : artistasList) { %>
        <img src="data:image/jpg;base64,<%= artista.getArtistaImagemBase64() %>" width="400" height="400" />
        <%   }
        } %>
    </aside>
    <div class="main-content">
        <h2 class="text">Artistas</h2>
        <% List<Artista> artistas = (List<Artista>) request.getAttribute("artista");
            if (artistas != null && !artistas.isEmpty()) {
                for (Artista artista : artistas) { %>
        <div class="artista">
            <p class="text">Nome</p>
            <p  class="text"><%= artista.getNomeArtista() %></p>
            <p  class="text">Descricao</p>
            <p  class="text"><%= artista.getDescricaoArtista() %></p>
            <p  class="text">Artistas</p>

                <p class="text">Musicas</p>
                <% List<Musica> musicas = artista.getMusicas();
                    if (musicas != null && !musicas.isEmpty()) {
                        for (Musica musica : musicas) { %>

                <p  class="text"><%= musica.getMusica() %></p>
                <%   }
                } else { %>
                <p  class="text">Nenhuma musica foi encontrada.</p>
                <% } %>

        </div>
        <% }
        } else { %>
        <p>Nenhum artista foi encontrado.</p>
        <% } %>
    </div>
</main>
<footer>
    <p class="footer-text">WikiMusic</p>
    <p class="footer-text">Sua biblioteca de musicas</p>
</footer>
</body>
</html>



