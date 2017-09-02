/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validar() {

    camponome = document.formCidade.nome;
    campouf = document.formCidade.uf;
    var retorno = true;

    // so permitira o envio se os dados forem fornecidos
    if (camponome.value.length > 45) {
        alert("Campo de nome da cidade não pode ter mais que 45 caracteres");
        camponome.style.backgroundColor = "#B0E2FF";
        camponome.focus();
        retorno = false;
    } else {
        camponome.style.backgroundColor = "white";
    }
    
    if (campouf.value == "X") {
        alert("Selecione um estado")
        campouf.style.backgroundColor = "#B0E2FF";
        campouf.focus();
        retorno = false;
    } else {
        campouf.style.backgroundColor = "white";
    }
    
    return retorno;
}


