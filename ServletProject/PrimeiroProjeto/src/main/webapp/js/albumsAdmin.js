function filtrarPorGenero() {
    var generoSelect = document.getElementById("genero");
    var generoSelecionado = generoSelect.options[generoSelect.selectedIndex].value;

    var nomeAlbumInput = document.getElementById("nomeAlbum");
    var nomeAlbum = nomeAlbumInput.value;

    var url = "/albums-admin";
    var params = [];

    if (generoSelecionado) {
        params.push("genero=" + generoSelecionado);
    }

    if (nomeAlbum) {
        params.push("nomeAlbum=" + encodeURIComponent(nomeAlbum));
    }

    if (params.length > 0) {
        url += "?" + params.join("&");
    }

    window.location.href = url;
}


function filtroGeneroEditar() {
    var generoSelect = document.getElementById("generoEditar");
    var generoSelecionado = generoSelect.options[generoSelect.selectedIndex].value;

}

function toggleEditForm(button) {
    var editForm = button.nextElementSibling;
    editForm.style.display = (editForm.style.display === "none") ? "block" : "none";
}