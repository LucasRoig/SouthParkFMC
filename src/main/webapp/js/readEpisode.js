/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    //plutot sale.
    var episodeId = $("#episodeId")[0].value;

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
                    showErrorAfter("Impossible de créer le tag " + input, $('#addTag .select-selectize-createEnable'));
                    callback(false);
                }
            });
        }
    });
    $('.select-selectize-createDisable').selectize({create: false});
    
    //Button remove quote
    $(".btn-remove-quote").click(function(){
       var button = $(this)[0];
       $.ajax({
           url:"/episode/removeQuote",
           data:{quoteId:button.value},
           method:"POST",
           success: function(data){
               if(data.result == true){
                   $(button).parents("tr")[0].remove();
               }else{
                    showErrorAfter("Echec de la suppression",$(button).parents("tr")[0])
               }
           },
           error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus); //error logging
                    console.log(errorThrown);
                    showErrorAfter("Echec de le suppression", $(button).parents("tr")[0]);
                }
       });
    });
    
    //Button remove apparition
    $(".btn-remove-apparition").click(function(){
       var button = $(this)[0];
       $.ajax({
           url:"/episode/removeApparition",
           data:{characterId:button.value, episodeId:episodeId},
           method:"POST",
           success: function(data){
               if(data.result == true){
                   $(button).parents("tr")[0].remove();
               }else{
                    showErrorAfter("Echec de la suppression",$(button).parents("tr")[0])
               }
           },
           error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus); //error logging
                    console.log(errorThrown);
                    showErrorAfter("Echec de le suppression", $(button).parents("tr")[0]);
                }
       });
    });
    
    //button remove tag
    $(".btn-remove-tag").click(function(){
       var button = $(this)[0];
       $.ajax({
           url:"/episode/removeTag",
           data:{tagId:button.value, episodeId:episodeId},
           method:"POST",
           success: function(data){
               if(data.result == true){
                   $(button).parents("tr")[0].remove();
               }else{
                    showErrorAfter("Echec de la suppression",$(button).parents("tr")[0])
               }
           },
           error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus); //error logging
                    console.log(errorThrown);
                    showErrorAfter("Echec de le suppression", $(button).parents("tr")[0]);
                }
       });
    });
    
    $("#editTag").on("show.bs.modal", function(event){      
       var button = $(event.relatedTarget); //boutton qui a appelle le modal
       var id = button.data('id');
       var name = button.data('name');
       var note = button.data('note');
       
       var modal = $(this);
       modal.find('#editTagName').val(name);
       modal.find('#editTagNote').val(note);
       modal.find('#editTagId').val(id);
    });
    
    $("#editQuote").on("show.bs.modal", function(event){      
       var button = $(event.relatedTarget); //boutton qui a appelle le modal
       var quoteId = button.data('quoteid');
       var characterId = button.data('characterid');
       var note = button.data('note');
       var text = button.data('text');
       var characterName = button.data('charactername');
       
       var modal = $(this);
       modal.find('#editQuoteCharacterName').val(characterName);
       modal.find('#editQuoteNote').val(note);
       modal.find('#editQuoteText').val(text);
       modal.find('#editQuoteCharacterId').val(characterId);
       modal.find('#editQuoteId').val(quoteId);
    });
    
    $("#editApparition").on("show.bs.modal", function(event){      
       var button = $(event.relatedTarget); //boutton qui a appelle le modal
       var roleid = button.data('roleid');
       var characterId = button.data('characterid');
       var note = button.data('note');
       var characterName = button.data('charactername');
       
       var modal = $(this);
       $(modal.find('#editApparitionRoleId')).selectize()[0].selectize.setValue(roleid);
       modal.find('#editApparitionCharacterName').val(characterName);
       modal.find('#editApparitionNote').val(note);
       modal.find('#editApparitionCharacterId').val(characterId);
    });
    
});

function showErrorAfter(errorMessage, element){
    $("<div/>",{
        class:"alert alert-danger",
        html:"<strong>Erreur : </strong> " + errorMessage
    }).insertAfter(element);
}


