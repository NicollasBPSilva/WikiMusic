function fetchAlbums() {
    var url = "/encontrar-albums";
    window.location.href = url;
}
function filtrarPorGenero() {
    var generoSelect = document.getElementById("genero");
    var generoSelecionado =
        generoSelect.options[generoSelect.selectedIndex].value;

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




function redirecionarArtista(){
    var url = "/encontrar-artistas";
    var params = [];
    window.location.href = url;
}
function redirecionarHome(){
    var url = "home.jsp";
    var params = [];
    window.location.href = url;
}
// var currentAlbum = -1;
// function toggleAlbum(albumId) {
//     var album = document.getElementById("album" + albumId);
//     var prevAlbum = document.getElementById("album" + currentAlbum);
//
//     if (album.style.display === "none") {
//         album.style.display = "block";
//         if (prevAlbum) {
//             prevAlbum.style.display = "none";
//         }
//         currentAlbum = albumId;
//     } else {
//         album.style.display = "none";
//         currentAlbum = -1;
//     }
// }
