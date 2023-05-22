<!DOCTYPE html>
<html lang="pt-br">
<head>
    <style>

    </style>
</head>
    <body>


    <span><%= session.getAttribute("username") %></span>
    <a href="/logout">Logout</a>
    <%
        if (session.getAttribute("loggedUser") != null) {
    %>
    <span><%= session.getAttribute("loggedUser") %></span>
    <a href="/logout">Logout</a>
    <%
        }
    %>
        <form action="/login" method="post">
            <div class="Test">

                <h1 class="imgFrequencia"> <img src="img/frequencia.png" alt="frequência"> </h1>

            </div>

            <div class="login">
                <label><b>Usuário</b></label>
                <input type="text" placeholder="Insira o nome de usuario" name="username" id="name" required>

                <label><b>Senha</b></label>
                <input type="password" placeholder="Insira a senha" name="password" id="password" required>

                <br><br><br>
                <button><img src="img/botaoEntrar.png" alt="entrar">Entrar </button>

            </div>

            <h3> <img src="IMG/logoWikimusic.png" alt="WIKIMUSIC"> </h3>
            <!--  -->

            <button><a href="albums.jsp">Ver albums</a></button>
            <button><a href="adicionarAlbum.jsp">Adicionar album</a></button>
        </form>
        <button><a href="adicionarAlbum.jsp">Adicionar album</a></button>
    </body>


</html>

