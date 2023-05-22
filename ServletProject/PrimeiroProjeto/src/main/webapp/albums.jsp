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
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>WikiMusic</title>
  <link rel="stylesheet" href="infoAlbuns.css">
</head>
<body>
<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  html, body {
    height: 100%;
  }

  .container {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    position: relative;
  }

  .header {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 55px;
    background-color: #515151;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 2;
    color: #fff;
  }

  .img_login {
    width: 36px;
    height: 36px;
    display: flex;
  }

  .footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 50px;
    background-color: #515151;
    color: #fff;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 200;
    padding-right: 1vw;
  }

  .header-buttons {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-grow: 1;
  }

  .header button,
  .footer button {
    padding: 10px;
    margin: 10px;
    font-size: 18px;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    background-color: transparent;
  }

  button:hover {
    background: rgb(65, 65, 66);
    background: linear-gradient(0deg, #515151 0%, #909090 100%);
    scale: 1.02;
    transition-duration: 100ms;
  }

  .header .button-login {
    display: flex;
    font-size: 40px;
    padding: 0 2%;
    margin-left: auto;
    background-color: #8C3F23;
    border-radius: 0;
    color: white;
  }

  .header button.active {
    background-color: darkgrey;
  }

  .header .button-login:hover {
    background: rgb(62, 20, 20);
    background: linear-gradient(0deg, #652b2b 0%, #561212 100%);
  }

  .wrapper {
    display: flex;
    flex-grow: 1;
    margin-top: 0px;
    padding-bottom: 0px;
  }

  .sidebar {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 300px;
    background-color: #b5b5b5;
    padding: 20px;
    margin-bottom: 0;
  }

  .sidebar img {
    width: 250px;
    height: 250px;
    border-radius: 5%;
    margin-bottom: 20px;
    margin-top: 60px;
  }

  .main-content {
    flex-grow: 1;
    max-width: 60%;
    text-align: left;
    padding: 80px;
    margin-left: 5%;
  }

  div.faixas {
    margin-top: 6%;
    padding: 2%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 50%;
  }

  @media (max-width: 768px) {
    .sidebar img {
      width: 100px;
      height: 100px;
    }

    .header .button-login {
      font-size: 20px;
      padding: auto;
    }

    .container .main-content {
      padding: 40px;
      padding-top: 80px;
      margin: 0;
    }
  }

  .navigation-buttons {
    margin-top: 10px;
    text-align: center;
  }

  .navigation-buttons a {
    display: inline-block;
    padding: 8px 16px;
    background-color: #f1f1f1;
    color: #333;
    text-decoration: none;
    border-radius: 4px;
    margin-right: 5px;
  }

  .navigation-buttons a:hover {
    background-color: #ddd;
  }


</style>

<div class="container">
  <div class="header">
    <div class="header-buttons">
      <button class="active">Home</button>

      <form  onload action="/encontrar-albuns" method="get">
        <select name="genero" id="genero">
          <option value="rock">Rock</option>
          <option value="sertanejo">Sertanejo</option>
          <option value="funk">Funk</option>
        </select>

        <label for="nome">Artista</label>
        <input type="text" name="nome" id="nome">

        <button type="submit">Buscar</button>
      </form>
      <button>Músicas</button>
      <button>Gêneros</button>
    </div>
    <button class="button-login">Login</button>
  </div>

  <%
    List<Album> albums = (List<Album>) request.getAttribute("albums");
    String generoSelecionado = (String) request.getAttribute("genero");
    if (albums != null) {
      for (Album album : albums) {
        if (generoSelecionado == null || generoSelecionado.isEmpty() || album.getGenero().equalsIgnoreCase(generoSelecionado)) {
  %>

  <div class="wrapper">
    <div class="sidebar">
      <div class="container">
        <img src="data:image/jpg;base64,<%= album.getImagemBase() %>">
        <p>Nome</p>
        <p><%= album.getDescricao() %></p>
        <p>Ano</p>
        <p><%= album.getAno() %></p>
        <p>Gravadora</p>
        <p><%= album.getGravadora() %></p>
        <p>Gênero</p>
        <p><%= album.getGenero() %></p>
        <p>País</p>
        <p><%= album.getPais() %></p>
      </div>
      </div>

    <div class="main-content">
      <%
        List<Artista> artistas = album.getArtistas();
        if (artistas != null) {
          for (Artista artista : artistas) {
      %>
      <div class="artista">
        <div class="container">
          <img src="data:image/jpg;base64,<%= artista.getArtistaImagemBase64() %>">
          <p><%= artista.getNomeArtista() %></p>
          <p>Descrição</p>
          <h3>Músicas</h3>
          <%
            List<Musica> musicas = artista.getMusicas();
            if (musicas != null) {
              for (Musica musica : musicas) {
          %>
          <div class="musica">
            <p>Nome da Música</p>
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
      </div>
    </div>
    <%
          }
        }
      }
    %>
</div>

</div>


<div class="footer">
  <button>WikiMusic</button>
  <p class="footer">Sua biblioteca de música</p>
</div>

<script>
  function filtrarPorGenero() {
    var generoSelect = document.getElementById("genero");
    var generoSelecionado = generoSelect.options[generoSelect.selectedIndex].value;

    var nomeAlbumInput = document.getElementById("nome");
    var nome = nomeAlbumInput.value;

    var url = "/encontrar-albums";
    var params = [];

    if (generoSelecionado) {
      params.push("genero=" + generoSelecionado);
    }

    if (nome) {
      params.push("nome=" + encodeURIComponent(nome));
    }

    if (params.length > 0) {
      url += "?" + params.join("&");
    }

    window.location.href = url;
  }



</script>
</body>
</html>
