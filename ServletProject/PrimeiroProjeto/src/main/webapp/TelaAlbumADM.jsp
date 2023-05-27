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
    <div class="sair">
        <a href="logout.jsp"> <img src="img/BotaoSair.png" alt="Sair" width="157px"> </a>
    </div>

</header>

<h3>
    <%--    <a href="TelaArtistaADM.html"> <img src="IMG/Seta_esquerda.png" alt="seta esquerda" > </a>--%>
    Album
    <%--    <a href="TelaArtistaADM.html"> <img src="IMG/Seta_direita.png" alt="seta direita"></a>--%>

</h3>

<section>
    <article>
        <h2>Adicionar</h2>



        <form action="/adicionaralbum" method="post" class="formulario" id="form-adicionar" enctype="multipart/form-data">

<%--            <input type="text" id="gravadora" name="gravadora" required>--%>



            <div>
<%--                <label for="genero">Genero:</label>--%>
                <br><br>


    <h4>Genero: </h4>
    <br>
                <select id="genero" name="genero" onchange="filtrarPorGenero()">
                    <option value="1">Rock</option>
                    <option value="2">Sertanejo</option>
                    <option value="3">Funk</option>
                </select>

            </div>
    <br>

    <h4>Artista</h4>
    <br>
    <select id="Artista" name="Artista" onchange="filtrarPorGenero()">

    </select>

    <br> <br>

    <input type="text" placeholder="Gravadora" id="Gravadora" name="Gravadora" required>

<%--            <label for="pais">Pais</label>--%>
            <br><br>
            <input type="text" placeholder="pais" id="pais" name="pais" required>


<%--            <label for="ano">Ano de lancamento</label>--%>
            <br><br>
            <input placeholder="ano" type="number" id="ano" name="ano" required>

<%--            <label for="descricaoAlbum">Descricao</label>--%>
            <br><br>
            <input placeholder="descricao" id="descricaoAlbum" name="descricaoAlbum" required>

<%--            <label for="nome">Nome</label>--%>
            <br><br>
            <input placeholder="nome" id="nome" name="nome" required>

<%--            <label for="imagem">Imagem</label>--%>
            <br><br>
            <input type="file" id="imagem" placeholder="imagem" name="imagem" required>
            <br><br>
<%--    <button class="button-estilo"><img src="img/Botao incluir.png" alt="botao entrar"></button>--%>

    </article>

    <article>
        <h2>Informacoes do Artista: </h2>
        <br><br>

        <h4>Genero: </h4>
        <br>
<%--        <label for="nomeArtista">Nome do artista:</label>--%>

        <input type="text" placeholder="Nome do Artista:" id="nomeArtista" name="nomeArtista" required>
        <br><br>
<%--        <label for="descricaoArtista">Descricao:</label>--%>

        <input type="text" placeholder="Descricao:" id="descricaoArtista" name="descricaoArtista" required>
        <br><br>
<%--        <label for="imagemArtista">Imagem:</label>--%>
        <input type="text" placeholder="Nome da Musica:" id="nomeMusica" name="nomeMusica" required>

        <br><br>

<%--        <label>Informacoes da Musica: </label>--%>

        <input type="text" placeholder="Musica" id="Genero:" name="nomeMusica" required>
        <br><br>
<%--        <label for="nomeMusica">Nome da musica:</label>--%>



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