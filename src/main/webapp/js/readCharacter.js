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
    
     //Button remove apparition
    $(".btn-remove-apparition").click(function(){
       var button = $(this)[0];
       //GITAN POWER 9999999999
       var splitValues = button.value.split("|");
       $.ajax({
           url:"/character/removeApparition",
           data:{characterId:splitValues[0], episodeId:splitValues[1]},
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
    
    $("#editApparition").on("show.bs.modal", function(event){   
       var button = $(event.relatedTarget); //boutton qui a appelle le modal
       var roleid = button.data('roleid');
       var characterId = button.data('characterid');
       var note = button.data('note');
       var episodeId = button.data('episodeid');
       var characterName = button.data('charactername');
       
       var modal = $(this);
       $(modal.find('#editApparitionRoleId')).selectize()[0].selectize.setValue(roleid);
       modal.find('#editApparitionCharacterName').val(characterName);
       modal.find('#editApparitionEpisodeId').val(episodeId);
       modal.find('#editApparitionNote').val(note);
       modal.find('#editApparitionCharacterId').val(characterId);
    });
    
});
