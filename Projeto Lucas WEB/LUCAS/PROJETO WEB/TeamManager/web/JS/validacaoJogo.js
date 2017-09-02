
function validar() {

    camponome = document.formAdversario.nome;
    campotelefone = document.formAdversario.telefone;
    campouf = document.formAdversario.uf;
    campocidade = document.formAdversario.cidade;
    var retorno = true;

    // so permitira o envio se os dados forem fornecidos
    if (camponome.value.length > 45) {
        alert("Campo de descrição não pode ter mais que 45 caracteres");
        camponome.style.backgroundColor = "#B0E2FF";
        camponome.focus();
        retorno = false;
    } else {
        camponome.style.backgroundColor = "white";
    }
    
        // so permitira o envio se os dados forem fornecidos
    if (campotelefone.value.length > 14) {
        alert("Campo de telefone não pode ter mais que 14 caracteres");
        campotelefone.style.backgroundColor = "#B0E2FF";
        campotelefone.focus();
        retorno = false;
    } else {
        campotelefone.style.backgroundColor = "white";
    }

    if (campouf.value == "X") {
        alert("Selecione um estado")
        campouf.style.backgroundColor = "#B0E2FF";
        campouf.focus();
        retorno = false;
    } else {
        campouf.style.backgroundColor = "white";
    }
    
    if (campocidade.value == 0) {
        alert("Selecione uma cidade")
        campocidade.style.backgroundColor = "#B0E2FF";
        campocidade.focus();
        retorno = false;
    } else {
        campocidade.style.backgroundColor = "white";
    }

    return retorno;
}



