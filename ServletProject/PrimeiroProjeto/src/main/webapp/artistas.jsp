<%@ page import="br.com.carsoft.model.Album.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
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
  <link rel="stylesheet" href="css/artista.css">
  <script src="js/artistas.js"></script>
</head>

<body>
<header class="header">
  <div class="header-buttons">
    <button class="active">Home</button>
    <form action="/encontrar-artistas" class="button" method="get">
      <select name="genero" id="genero">
        <option value="rock">Rock</option>
        <option value="sertanejo">Sertanejo</option>
        <option value="funk">Funk</option>
      </select>

      <label for="nome">Artista</label>
      <input type="text" name="nome" id="nome" class="custom-input">

      <button type="submit">Buscar</button>
    </form>
  </div>
  <form action="/encontrar-albums?" class="button" method="get">
    <button onclick="redirecionar()">Albums</button>
  </form>
  <a class="ancor-login" href="login.jsp">
    <button class="button-login">
      Login
    </button>
  </a>
</header>
<main>
  <aside class="sidebar">
    <% List<Artista> artistas = (List<Artista>) request.getAttribute("artista");
      if (artistas != null) {
        for (Artista artista : artistas) { %>
    <img src="data:image/jpg;base64,<%= artista.getArtistaImagemBase64() %>" width="100" height="100">
  </aside>
  <div class="main-content">
<%--    <p>Nome do artista</p>--%>
    <p><%= artista.getNomeArtista() %></p>
    <p>Descrição</p>

    <p> <%= artista.getDescricaoArtista() %></p>

    <% List<Musica> musicas = artista.getMusicas();
      if (musicas != null) {
        for (Musica musica : musicas) { %>
    <p>Nome da musica</p>
    <p><%= musica.getMusica() %></p>
    <%   }
    } %>
    </t>
    </tr>
    <%       }
    } %>
  </div>
  </div>
  </div>
</main>
<footer>
  <button>WikiMusic</button>
  <p class="footer">Sua biblioteca de música</p>
</footer>
</body>
</html>

