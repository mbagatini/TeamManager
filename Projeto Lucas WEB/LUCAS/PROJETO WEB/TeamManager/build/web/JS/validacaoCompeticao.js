
function validar() {

    campodescricao = document.formCompeticao.descricao;
    campotipo = document.formCompeticao.tipo;
    var retorno = true;

    // so permitira o envio se os dados forem fornecidos
    if (campodescricao.value.length > 45) {
        alert("Campo de descrição não pode ter mais que 45 caracteres");
        campodescricao.style.backgroundColor = "#B0E2FF";
        campodescricao.focus();
        retorno = false;
    } else {
        campodescricao.style.backgroundColor = "white";
    }

    if (campotipo.value == "X") {
        alert("Selecione um tipo de competição")
        campotipo.style.backgroundColor = "#B0E2FF";
        campotipo.focus();
        retorno = false;
    } else {
        campotipo.style.backgroundColor = "white";
    }

    return retorno;
}



