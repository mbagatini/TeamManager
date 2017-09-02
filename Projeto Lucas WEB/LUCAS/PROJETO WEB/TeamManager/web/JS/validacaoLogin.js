/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validar(){
   // so permitira o envio se os dados forem fornecidos
   if(document.login.emailUsuario.value == ""){
     alert("Forneca o nome do usuario");
     document.login.emailUsuario.focus();
     return false;
   }
   else if(document.login.senha.value == ""){
     alert("Forneca a senha do usuario");
     document.login.senha.focus();
     return false;
   }
   else
     return true; 
}

