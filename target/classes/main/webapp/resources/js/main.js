$(document).ready(function(){

    $(".hideForm").hide();
    $(document).on('click', '#showButton' , function() {
    	 $(".hideForm").show();
    	 $("#showButton").hide();
   });

});

$(document).ready(function(){

    $(document).on('click', '#discardButton' , function() {
    	 $(".hideForm").hide();
    	 $("#showButton").show();
    	 resetForm($('#myForm'));
   });

});

function resetForm($form) {
    $form.find('input:text, input:password, input:file, select, textarea').val('');
    $form.find('input:radio, input:checkbox')
         .removeAttr('checked').removeAttr('selected');
}

function deleteConfirmation(id) {
	//alert(id);
	var retVal = confirm("Are you sure?");
	 if( retVal == true ){
	      window.location = 'remove?idToDelete='+id;
	   }else{
	      alert("User does not want to continue!");
		  return false;
	   }
}

function goToShowPage(id) {
	  window.location = 'noteModify?idToModify='+id;
}


function getParameterByName(name) {
	//alert(name);
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    //alert(escape(results));
    //alert(decodeURIComponent(results[1].replace(/\+/g, " ")));
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    
}