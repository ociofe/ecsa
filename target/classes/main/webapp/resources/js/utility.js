/**
 * Created by mbalestrini on 29/05/2015.
 */
var myshow = myshow || {};

myshow.utility = (function(){
    var module = {};

    // *****************************************************************
    // function : isElementExist
    // Funzione esposta all'esterno per verificare se l'elemnto passato
    // e' in pagina
    // *****************************************************************
    // Parametri : elemento da verificare
    // Return : <true>  l'elemento e' in pagina
    //          <false> l'elemento non e' in pagina
    // *****************************************************************
    module.isElementExist= function(el){

        if($(el).length>0){
            return true;
        }

        return false;
    };

    // *****************************************************************
    // function : isNullOrEmpty
    // Funzione esposta all'esterno per verificare se il valore passato
    // e' vuoto
    // *****************************************************************
    // Parametri : valore da verificare
    // Return : <true>  l'elemento e' vuoto
    //          <false> l'elemento non e' in vuoto
    // *****************************************************************
    module.isNullOrEmpty=function(value){
        if(value === undefined || value === null || value.toString() === "null" || value.toString() === ""){
            return true;
        }
        return false;
    };

    // *****************************************************************
    // function : isNullOrEmptyElements
    // Funzione esposta all'esterno per verificare se il valore
    // dell'elemento passatp e' vuoto
    // *****************************************************************
    // Parametri : valore da verificare
    // Return : <true>  l'elemento e' vuoto
    //          <false> l'elemento non e' in vuoto
    // *****************************************************************
    module.isNullOrEmptyElements=function(el){
        var value = $(el).val();
        return this.isNullOrEmpty(value);
    };

    module.pad=function(str, max) {
        str = str.toString();
        return str.length < max ? this.pad("0" + str, max) : str;
    };

    module.neon = function(options){

        var string = options.string;
        var objToAppend = options.objToAppend;
        var arrstring = string.split("<br>");

        var start = -1;
        var end = 0;
        var arrclass=["in","out"];
        var char = "";
        var span = "";
        $(objToAppend).empty();
        $.each(arrstring,function(key,string){
            start = -1;
            end = 0;
            for(var i=0;i<string.length;i++){

                start++;
                end++;
                classe = arrclass[Math.floor(Math.random()*arrclass.length)];
                char = string.substring(start,end);
                span = $("<span>");
                $(span).addClass(classe).append(char);

                $(objToAppend).append(span);

            }
            $(objToAppend).append("<br>");
        });
    };

    $(document).ready(function(){

    });

    return module;

})();
