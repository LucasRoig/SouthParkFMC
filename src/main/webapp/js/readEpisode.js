/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    autosize($('textarea'));
    //Fonction pour les form-switch
    $(".form-switch .btn-switch").click(function () {
        var form = $(this).parents(".form-switch");
        var front = form.find(".front");
        var back = form.find(".back");
        back.removeClass("back").addClass("front");
        front.removeClass("front").addClass("back");
        autosize.update($('textarea'));
    })

    $('#addQuote .select-selectize-createEnable').selectize({create: true});
    $('#addApparition .select-selectize-createEnable').selectize({create: true});
    $('#addTag .select-selectize-createEnable').selectize({
        create: function (input, callback) {
            $.ajax({
                url: "/tag/create",
                data: {tagName: input},
                method: "POST",
                success: function (data) {
                    if(data.result == true){
                        callback({value:data.tagId, text:input});
                    }
                    showErrorAfter("Impossible de créer le tag " + input, $('#addTag .select-selectize-createEnable').parents(".form-group"));
                    callback(false);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus); //error logging
                    console.log(errorThrown);
                    showErrorAfter("Impossible de créer le tag " + input, $('#addTag .select-selectize-createEnable'))
                    callback(false);
                }
            });
        }
    });
    $('.select-selectize-createDisable').selectize({create: false});
});

function showErrorAfter(errorMessage, element){
    $("<div/>",{
        class:"alert alert-danger",
        html:"<strong>Erreur : </strong> " + errorMessage
    }).insertAfter(element);
}

