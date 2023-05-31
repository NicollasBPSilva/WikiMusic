<%@ page import="br.com.carsoft.model.Album.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="br.com.carsoft.model.Album.Artista" %>
<%@ page import="br.com.carsoft.model.Album.Musica" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TelaAlbumADM</title>
    <link rel="stylesheet" href="./css/albumADM.css">
    <script src="js/albumsAdmin.js"></script>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
</head>
<body>
<div>
    <header>
        <% if (session.getAttribute("loggedUser") != null) { %>
        <span class="user-text"><%= session.getAttribute("loggedUser") %></span>

        <% } %>
        <form action="home.jsp">
            <button class="button-header" type="submit">HOME</button>
        </form>
        <form action="/encontrar-albums?">
            <button class="button-header" type="submit">ALBUMS</button>
        </form>
        <form action="/encontrar-artistas?">
            <button class="button-header" type="submit">ARTISTAS</button>
        </form>
        <form action="TelaAlbumADM.jsp">
            <button class="button-header" type="submit">Adicionar Album</button>
        </form>
        <div class="sair">
            <% if (session.getAttribute("loggedUser") != null) { %>
            <a href="/logout"> <img src="img/BotaoSair.png" alt="Sair" width="157px"></a>
            <% } %>
        </div>
    </header>

    </h3>

    <section>
    <article>
        <div>
            <label for="genero">Genero</label>
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
            <div class="imagem">
                <img src="data:image/jpg;base64,<%= album.getImagemBase() %>" height="300px" width="500px">
            </div>
            <br>
            <article class="album-detalhes">
                <h3>Nome</h3>
            <p><%= album.getDescricao() %></p>
                <h3>Ano</h3>
                <p><%= album.getAno() %></p>
                <h3>Gravadora</h3>
                <p><%= album.getGravadora() %></p>
                <h3>Gênero</h3>
                <p><%= album.getGenero() %></p>
                <h3>País</h3>
                <p><%= album.getPais() %></p>

            <h3>Artistas</h3>
            <%
                Set<Artista> artistas = album.getArtistas();
                if (artistas != null && !artistas.isEmpty()) {
                    for (Artista artista : artistas) {
            %>
            <div class="artista">
                <div class="container">
                    <img src="data:image/jpg;base64,<%= artista.getArtistaImagemBase64() %>"  height="300px" width="500px">
                </div>
                <h3> Nome do Artista</h3>
                <p> <%= artista.getNomeArtista() %></p>
                <h3> Descricao do Artista</h3>
                <p> <%= artista.getDescricaoArtista() %></p>

                <h3>Músicas</h3>
                <%
                    List<Musica> musicas = artista.getMusicas();
                    if (musicas != null && !musicas.isEmpty()) {
                        for (Musica musica : musicas) {
                %>
                <div class="musica">
                    <p><%= musica.getMusica() %></p>
                </div>
                <%
                        }
                    }
                    }}
                %>
            </div>

            <form action="/adicionarArtista" method="post" enctype="multipart/form-data">
                <input type="hidden" name="artistaId" value="<%= album.getId() %>">
                <label for="artistaAdicionar">Adicionar Artista</label>
                <input type="text" id="artistaAdicionar" name="artistaAdicionar">
                <label for="descricaoArtistaAdd">Descricao</label>
                <input type="text" id="descricaoArtistaAdd" name="descricaoArtistaAdd">
                <label for="imagemArtista">Adicionar Imagem</label>
                <input type="file" id="imagemArtista" name="imagemArtista">
                <button class="button-corpo" type="submit">Adicionar</button>
            </form>
            <form action="/adicionarMusica" method="post">
                <input type="hidden" name="artistaAdicionar" value="<%= album.getId() %>">
                <label for="musicaArtista">Adicionar música</label>
                <input type="text" id="musicaArtista" name="musicaArtista">
                <button class="button-corpo" type="submit">Adicionar</button>
            </form>


            <button class="button-corpo" type="button" onclick="toggleEditForm(this)">Editar</button>
            <form action="/editar-album" method="post" class="edit-form" enctype="multipart/form-data">
                <input type="hidden" name="id" value="<%= album.getId() %>">
                <h1>ALBUM</h1>
                <label for="descricaoAlbum">Descrição</label>
                <input type="text" id="descricaoAlbum" name="descricaoAlbum">

                <label for="ano">Ano</label>
                <input type="int" id="ano" name="ano">

                <label for="gravadora">Gravadora</label>
                <input type="text" id="gravadora" name="gravadora">

                <div>
                    <label for="generoEditar">Gênero</label>
                    <select id="generoEditar" name="generoEditar" onchange="filtroGeneroEditar()">
                        <option value="1">Rock</option>
                        <option value="2">Sertanejo</option>
                        <option value="3">Funk</option>
                    </select>
                </div>

                <label for="pais">País</label>
                <input type="text" id="pais" name="pais">

                <h1>ARTISTA</h1>
                <label for="artista">Artista</label>
                <input type="text" id="artista" name="artista">

                <label for="descricaoArtista">Descrição</label>
                <input type="text" id="descricaoArtista" name="descricaoArtista">

                <label for="imagem">Imagem</label>
                <input type="file" id="imagem" name="imagem" required>

                <button class="button-corpo" type="submit">Update Album</button>
            </form>

            <form action="/delete-album" method="post">
                <input type="hidden" name="albumId" value="<%= album.getId() %>">
                <button class="button-corpo" type="submit">Delete</button>
            </form>
        </div>
        <%
                    }
                }
            }
        %>
</article>
</section>
<footer>
    <h1> WIKIMUSIC</h1>
</footer>
</div>
</body>
</html>