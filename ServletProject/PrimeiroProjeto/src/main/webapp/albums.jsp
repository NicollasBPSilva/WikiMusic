<%@ page import="br.com.carsoft.model.Album" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>
<style>
    body {
        background-color: #F5F5F5;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    h1 {
        color: #404040;
        text-align: center;
        margin-top: 40px;
    }

    table {
        width: 80%;
        margin: 50px auto;
        border-collapse: collapse;
        box-shadow: 0 0 20px rgba(0,0,0,0.1);
        background-color: #FFFFFF;
    }

    th, td {
        text-align: left;
        padding: 10px;
        border-bottom: 1px solid #E1E1E1;
    }

    th {
        background-color: #404040;
        color: #FFFFFF;
    }

    tr:nth-child(even) {
        background-color: #F5F5F5;
    }
</style>
<div>
    <h1>Albums</h1>
    <table>
        <tr>
            <th>Titulo</th>
            <th>Artista</th>
            <th>Album</th>
        </tr>
        <% List<Album> albums = (List<Album>) request.getAttribute("albums");
            for (Album album : albums) { %>
        <tr>
            <td><%= album.getTitulo() %></td>
            <td><%= album.getArtista() %></td>
            <td><%= album.getAlbum() %></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>