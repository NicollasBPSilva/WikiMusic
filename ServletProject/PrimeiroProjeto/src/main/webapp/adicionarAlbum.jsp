<!DOCTYPE html>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Gerenciador de Músicas</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .formulario {
            margin-top: 20px;
        }

        .formulario label {
            display: block;
            margin-bottom: 8px;
        }

        .formulario input[type=text] {
            padding: 8px;
            width: 100%;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            margin-bottom: 20px;
        }

        .formulario input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .formulario input[type=submit]:hover {
            background-color: #45a049;
        }

        .deletar {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 8px 16px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }

        .deletar:hover {
            background-color: #da190b;
        }
    </style>
</head>
<body>
<h1>Gerenciador de Músicas</h1>

<table>
    <tr>
        <th>Título</th>
        <th>Artista</th>
        <th>Álbum</th>
        <th>Ano</th>
        <th>Ações</th>
    </tr>
    <tr>
        <td>Bohemian Rhapsody</td>
        <td>Queen</td>
        <td>A Night at the Opera</td>
        <td>1975</td>
        <td><button class="deletar">Deletar</button></td>
    </tr>
    <tr>
        <td>Stairway to Heaven</td>
        <td>Led Zeppelin</td>
        <td>Led Zeppelin IV</td>
        <td>1971</td>
        <td><button class="deletar">Deletar</button></td>
    </tr>
</table>

<form action="/adicionaralbum" method="post" class="formulario" id="form-adicionar" enctype="multipart/form-data">

    <label for="titulo">Título:</label>
    <input type="text" id="titulo" name="titulo" required>

    <label for="artista">Artista:</label>
    <input type="text" id="artista" name="artista" required>

    <label for="album">Álbum:</label>
    <input type="text" id="album" name="album" required>

    <label for="album">Informacoes do artista:</label>
    <input type="text" id="informacoes" name="informacoes" required>
    <button type="submit">Enviar</button>

    <label for="album">Imagem:</label>
    <input type="file" id="imagem" name="imagem">

    <button type="submit">Save</button>
    </body>
</html>