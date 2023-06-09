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

