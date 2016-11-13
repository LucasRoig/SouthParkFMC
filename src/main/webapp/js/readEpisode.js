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

    $('#addQuote .select-selectize-createEnable' ).selectize({create: true});
    $('#addApparition .select-selectize-createEnable' ).selectize({create: true});
    $('#addTag .select-selectize-createEnable' ).selectize({create: true});
    $('.select-selectize-createDisable').selectize({create: false});
});

