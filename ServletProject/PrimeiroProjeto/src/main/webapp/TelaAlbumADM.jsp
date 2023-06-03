<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TelaAlbumADM</title>
    <link rel="stylesheet" href="./css/TelaAlbumADM.css">
    <script src="./js/script.js"></script>

</head>
<body>
<header>
<%--    <% if (session.getAttribute("loggedUser") != null) { %>
    <span class="user-text"><%= session.getAttribute("loggedUser") %></span>

    <% } %>--%>
    <form action="home.jsp">
        <button class="button-header" type="submit">HOME</button>
    </form>
    <form action="/encontrar-albums?">
        <button class="button-header" type="submit">ALBUMS</button>
    </form>
    <form action="/encontrar-artistas?">
        <button class="button-header" type="submit">ARTISTAS</button>
    </form>
    <form action="albums-admin?">
        <button class="button-header" type="submit">Administrar Albums</button>
    </form>

    <div class="sair">
        <a href="login.jsp"> <img src="img/BotaoSair.png" alt="Sair" width="157px"> </a>
    </div>
</header>



<section>
    <article>
        <h2>Album</h2>


        <form action="/adicionaralbum" method="post" class="formulario" id="form-adicionar" enctype="multipart/form-data">

            <div>
                <br><br>

    <h4>Genero: </h4>
    <br>
                <select id="genero" name="genero" onchange="filtrarPorGenero()">
                    <option value="1">Rock</option>
                    <option value="2">Sertanejo</option>
                    <option value="3">Funk</option>
                </select>

            </div>

    <br> <br>

    <input type="text" placeholder="Gravadora" id="gravadora" name="gravadora" required>

            <br><br>
            <input type="text" placeholder="pais" id="pais" name="pais" required>


            <br><br>
            <input placeholder="ano" type="number" id="ano" name="ano" required>

            <br><br>
            <input placeholder="descricao" id="descricaoAlbum" name="descricaoAlbum" required>

            <br><br>
            <input placeholder="nomeAlbum" id="nomeAlbum" name="nomeAlbum" required>
    <br><br>
    <input type="file" id="imagemAlbum" name="imagemAlbum" required>


            <br><br>
        <h2>Artista </h2>
        <br><br>


        <input type="text" placeholder="Nome do Artista:" id="nomeArtista" name="nomeArtista" required>
        <br><br>

        <input type="text" placeholder="Descricao do artista:" id="descricaoArtista" name="descricaoArtista" required>
        <br><br>

        <input type="text" placeholder="Musica" id="musica:" name="nomeMusica" required>
        <br><br>

        <input type="file" id="imagemArtista" name="imagemArtista" required>
        <br><br>

        <button class="button-estilo"><img src="img/Botao incluir.png" alt="botao entrar"></button>
    </article>

    </form>


</section>
<footer>
    <h1> WIKIMUSIC</h1>
</footer>


</body>

</html>