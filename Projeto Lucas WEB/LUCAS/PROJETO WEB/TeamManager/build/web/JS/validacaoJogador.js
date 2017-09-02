
function validar() {

    camponumero = document.formJogador.numero;
    campouf = document.formJogador.uf;
    campocidade = document.formJogador.cidade;
    campopeso = document.formJogador.peso;
    compoaltura = document.formJogador.altura;
    var retorno = true;

    if (campouf.value == "X") {
        alert("Selecione um estado")
        campouf.style.backgroundColor = "#B0E2FF";
        campouf.focus();
        retorno = false;
    } else {
        campouf.style.backgroundColor = "white";
    }

    if (campocidade.value == "X") {
        alert("Selecione uma cidade")
        campocidade.style.backgroundColor = "#B0E2FF";
        campocidade.focus();
        retorno = false;
    } else {
        campocidade.style.backgroundColor = "white";
    }

    // so permitira o envio se os dados forem fornecidos
    if (camponumero.value == 0 || camponumero.value.length < 1) {
        alert("Número inválido.");
        camponumero.style.backgroundColor = "#B0E2FF";
        camponumero.focus();
        retorno = false;
    } else {
        camponumero.style.backgroundColor = "white";
    }

    if (campopeso.value == 0.0 || campopeso.value.length == 0) {
        alert("Peso inválido")
        campopeso.style.backgroundColor = "#B0E2FF";
        campopeso.focus();
        retorno = false;
    } else {
        campopeso.style.backgroundColor = "white";
    }

    if (campoaltura.value == 0.0 || campoaltura.value.length == 0) {
        alert("Altura inválida")
        campoaltura.style.backgroundColor = "#B0E2FF";
        campoaltura.focus();
        retorno = false;
    } else {
        campoaltura.style.backgroundColor = "white";
    }

    // POSICOES
    var check = document.getElementsByName("posicao");
    var qtde = 0;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked == true) {
            qtde++;
        }
    }

    if (qtde <= 0) {
        alert("Selecione uma posicao")
        retorno = false;
    }

    // POSICOES
    var check = document.getElementsByName("posicao");
    var qtde = 0;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked == true) {
            qtde++;
        }
    }

    if (qtde <= 0) {
        alert("Selecione uma posicao")
        retorno = false;
    }

    return retorno;
}



