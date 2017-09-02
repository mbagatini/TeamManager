/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validar() {

    campodescricao = document.formPosicao.descricao

    // so permitira o envio se os dados forem fornecidos
    if (campodescricao.value.length > 45) {
        alert("Campo de descrição não pode ter mais que 45 caracteres");
        campodescricao.style.backgroundColor = "#B0E2FF";
        campodescricao.focus();
        return false;
    } else
        campodescricao.style.backgroundColor = "white";
        return true;
}


