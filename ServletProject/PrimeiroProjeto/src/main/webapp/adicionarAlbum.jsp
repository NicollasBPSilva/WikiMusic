<!DOCTYPE html>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Gerenciador de Músicas</title>
    <style>
        .card {
            background-color: #f4f4f4;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: 0 auto;
        }

        .card h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .card label {
            display: block;
            margin-bottom: 10px;
        }

        .card input,
        .card select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 10px;
        }

        .card button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .card button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="card">
<h1>Gerenciador de Musicas</h1>

<form action="/adicionaralbum" method="post" class="formulario" id="form-adicionar" enctype="multipart/form-data">

    <label for="gravadora">Gravadora</label>
    <input type="text" id="gravadora" name="gravadora" required>

    <div>
        <label for="genero">Genero:</label>
        <select id="genero" name="genero" onchange="filtrarPorGenero()">
            <option value="1">Rock</option>
            <option value="2">Sertanejo</option>
            <option value="3">Funk</option>
        </select>
    </div>
    <h1>Informacoes do Album: </h1>
    <label for="pais">Pais</label>
    <input type="text" id="pais" name="pais" required>

    <label for="ano">Ano</label>
    <input type="number" id="ano" name="ano" required>

    <label for="descricaoAlbum">Descricao do Album</label>
    <input type="text" id="descricaoAlbum" name="descricaoAlbum" required>

    <label for="imagem">Imagem:</label>
    <input type="file" id="imagem" name="imagem" required>


    <h1>Informacoes do Artista: </h1>
    <label for="nomeArtista">Nome do artista:</label>
    <input type="text" id="nomeArtista" name="nomeArtista" required>

    <label for="descricaoArtista">Descricao:</label>
    <input type="text" id="descricaoArtista" name="descricaoArtista" required>


    <h1>Informacoes da Musica: </h1>
    <label for="nomeMusica">Nome da musica:</label>
    <input type="text" id="nomeMusica" name="nomeMusica" required>

    <label for="artista">Compositor:</label>
    <input type="text" id="artista" name="artista" required>

    <button type="submit">Enviar</button>

</form>
    </div>
    </body>
<script>
    function filtrarPorGenero() {
        var generoSelect = document.getElementById("genero");
        var generoSelecionado = generoSelect.options[generoSelect.selectedIndex].value;

        var generoInputAnterior = document.getElementById("generoInput");
        if (generoInputAnterior) {
            generoInputAnterior.remove();
        }

        var generoInput = document.createElement("input");
        generoInput.setAttribute("type", "hidden");
        generoInput.setAttribute("name", "genero");
        generoInput.setAttribute("value", generoSelecionado);
        generoInput.setAttribute("id", "generoInput");

        var form = document.getElementById("form-adicionar");
        form.appendChild(generoInput);
    }

</script>

</html>