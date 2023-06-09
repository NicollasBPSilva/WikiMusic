<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TelaLogin</title>
    <link rel="stylesheet" href="./css/TelaLogin.css">
</head>
    <body>

    <aside>
        <form action="/encontrar-albums?">
            <button class="button-estilo-aside" type="submit">ALBUMS</button>
        </form>

        <form action="/encontrar-artistas?">
            <button class="button-estilo-aside" type="submit">ARTISTAS</button>
        </form>

        <form action="home.jsp">
            <button class="button-estilo-aside" type="submit">HOME</button>
        </form>
    </aside>
        <form action="/login" method="post">
            <div class="Test">
                <div class="img">
                    <img src="img/frequencia.png" alt="frequência">


                </div>
            </div>

            <div class="login">
                <input type="text" placeholder="Usuario" name="username">
                <br><br>
                <input type="password" placeholder="Senha" name="password">

                <br><br><br>
            </div>
            <h2><button class="button-position"><img src="img/botao_entrar.png" alt="entrar"</button></h2>

            <h3> <img src="img/LOGO WIKIMUSIC.png" alt="WIKIMUSIC"> </h3>

        </form>
    </body>


</html>

