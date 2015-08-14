/**
 * Created by mbalestrini on 29/05/2015.
 */
var myshow = myshow || {};
myshow.utility.ajax = myshow.utility.ajax || {};

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


myshow.utility.ajax = (function(){
    var module = {};

    // *****************************************************************
    // function : ajaxCall
    // Funzione esposta all'esterno per la gestione di una chiamata ajax
    // *****************************************************************
    // Parametri : oggetto json options
    //             options.url : url da richiamare <OBBLIGATORIO>
    //             options.method : default <POST>
    //             options.data : default <VUOTO>
    //             options.dataType : default <HTML>
    //             options.success : default mostra solo il data
    //             					 di ritorno va sempre IMPLEMENTATA
    //								 <OBBLIGATORIO>
    //             options.async : default <TRUE>
    //             options.error : default mostra un messaggio generico
    //             				   se non sono necessarie azioni diverse
    //                             in caso d'errore si pue' lasciare cosi
    //             options.beforeSend : default mostra il messaggio o
    //             				   immagine di caricamento in corso
    //             options.complete : default nasconde il messaggio o
    //             				   immagine di caricamento in corso
    //             options.cache : default <FALSE>
    // Return : nessuno
    // *****************************************************************
    module.ajaxCall=function(options){

        if(myshow.utility.isNullOrEmpty(options.url)){
            console.log("Attenzione !!! ajaxCall : <url> non valorizzato");
            return;
        }

       
        options.isDelete = myshow.utility.isNullOrEmpty(options.isDelete) ? false : options.isDelete;
        options.method = myshow.utility.isNullOrEmpty(options.method) ? "POST" : options.method;
        options.data = myshow.utility.isNullOrEmpty(options.data) ? "" : options.data;
        options.headers = myshow.utility.isNullOrEmpty(options.headers) ? "" : options.headers;
        options.enableLoadingEnd = myshow.utility.isNullOrEmpty(options.enableLoadingEnd) ? true : options.enableLoadingEnd;
        if(options.method==="POST" && !options.isDelete){
            if(!myshow.utility.isNullOrEmpty(options.idform)){
                var idform = options.idform;
                options.data = JSON.stringify(myshow.utility.ajax.funSerializeObject(idform));
            }else{
                console.log("Attenzione !!! ajaxCall metodo POST : <idform> non valorizzato");
                return;
            }
        }
        options.type=myshow.utility.isNullOrEmpty(options.type) ? options.method : options.type; // aggiunto perche' nelle versioni vecchie di jquery il parametro methods non funziona
        options.dataType = myshow.utility.isNullOrEmpty(options.dataType) ? "HTML" : options.dataType;
        options.success = myshow.utility.isNullOrEmpty(options.success) ? function(data){
            console.log("AJAX CALL SUCCESS");
            console.log(data);
            //myshow.utility.loadingEnd();
        } : options.success;
        options.async = myshow.utility.isNullOrEmpty(options.async) ? true : options.async;
        options.error = myshow.utility.isNullOrEmpty(options.error) ? function(){console.log("AJAX CALL ERROR");} : options.error;
        options.beforeSend = myshow.utility.isNullOrEmpty(options.beforeSend) ? function(){myshow.utility.ajax.loadingStart(options);} : options.beforeSend;
        options.complete = myshow.utility.isNullOrEmpty(options.complete) ? function(){

            if(options.enableLoadingEnd){
                myshow.utility.ajax.loadingEnd();
            }
        } : options.complete;
        options.cache = myshow.utility.isNullOrEmpty(options.cache) ? false : options.cache;

        $.ajax(options);
    };


    module.ajaxReplaceContent=function(options){
        $(myshow.selector.alertsuccess).hide();
        $(myshow.selector.alertdanger).hide();
        var target  = myshow.utility.isNullOrEmpty(options.target) ? "#page-wrapper .content" : options.target;
        $(target).html(options.data);
    };
    // *****************************************************************
    // function : loadingStart
    // Funzione per aggiungere a un elemento contanitore il messaggio
    // di caricamento in corso
    // *****************************************************************
    // Parametri : oggetto json p
    //             options.loadingelement : elemento selettotre a cui si
    //			   vuole aggiungere il messaggio.
    // Return : nessuno
    // *****************************************************************
    module.loadingStart=function(p){
        var element = p.loadingelement;
        var parent = "body";
        if(!myshow.utility.isNullOrEmpty(element)){
            parent = $(element).closest('.append-loading');
        }
        //$(parent).append(myshow.selector.html.loading);
    };

    // *****************************************************************
    // function : loadingEnd
    // Funzione per rimuovere il messaggio di caricamento della pagina
    // *****************************************************************
    // Parametri : nessuno
    // Return : nessuno
    // *****************************************************************
    module.loadingEnd=function(){

        $("body").find('.loading').fadeOut();
    };

    // *****************************************************************
    // function : funSerializeObject
    // Funzione per recuperare i campi di una form da passare poi
    // alla chiamata AJAX in caso di method POST
    // *****************************************************************
    // Parametri : form da cui recuperare i campi e valori
    // Return : json con i campi valori della form passata
    // *****************************************************************
    module.funSerializeObject=function(form){
        return $(form).serializeObject();
    };

    module.showBEError=function(data){
        var errorsHtml = "";
        $(myshow.selector.alertdanger).hide();
        if(!myshow.utility.isNullOrEmpty(data)){
            var errors = data.responseJSON;
            $.each( errors, function( key, value ) {
                errorsHtml += '<li>' + value[0] + '</li>'; //showing only the first error.
            });
            $(myshow.selector.alertdanger+" ul").empty().append(errorsHtml);
            $(myshow.selector.alertdanger).show();
        }
    };

    module.shoeBESucces=function(data){
        var mex = myshow.utility.isNullOrEmpty(data.mex) ? "Messaggio non valorizzato" : data.mex;
        $(myshow.selector.alertsuccess+" .mex").empty().html(mex);
        $(myshow.selector.alertsuccess).show();
    };

    $(document).ready(function(){

    });

    return module;

})();
