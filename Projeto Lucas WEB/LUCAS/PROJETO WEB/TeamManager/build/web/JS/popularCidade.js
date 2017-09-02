
function prencherCidade(codigo) {
    $(document).ready(function () {
        var stateId = $('#uf').val();
        $.ajax({
            url: "/TeamManager/acao?parametro=popularCidade&uf=" + stateId,
            type: "POST",
            dataType: "json",
            data: {uf: stateId},
            success: function (cidades) {
                $("#cidade").html(""); // clear before appending new list
                var string = ""
                $.each(cidades, function (i, city) {
                    string = string + "<option value= '" + city.id + "' ";
                    if (city.id == codigo){
                        string = string + " selected ";
                    }
                    string = string + " >" + city.nome + "</option> ";
                });
                $("#cidade").html("" + string);
            }
        });
    });
}




