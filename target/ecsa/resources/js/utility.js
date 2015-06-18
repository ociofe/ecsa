var ecsa = ecsa || {};

$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o['"'+this.name+'"'].push) {
               o['"'+this.name+'"'] = [o['"'+this.name+'"']];
           }
           o['"'+this.name+'"'].push(this.value || '');
       } else {
           o['"'+this.name+'"'] = this.value || '';
       }
   });
   return o;
};

	ecsa.regEx = (function(){
	
	var module={};

	var initRegExt=function(){
		$(document).on('keypress', 'input[type="number"],.number', function (event) {
			var reg = /^[0-9]$/;
			return checkRegEx(event,reg);
		});
	
		$(document).on('keypress', '.charNumber', function (event) {
			var reg = new RegExp("^[a-zA-ZàèéìòùÀÈÉÌÒÙ0-9 \']$");
			return checkRegEx(event,reg);
		});
		
		$(document).on('keypress', '.char', function (event) {
			var reg = new RegExp("^[a-zA-ZàèéìòùÀÈÉÌÒÙ \']$");
			return checkRegEx(event,reg);
		});
	};

	// **************************************************		
	// function : checkRegEx
	//	funzione per controllo regular-exp
	// **************************************************
	// Parametri :	1) e : event
	//				2) reg : regular-exp
	// Return : <true> : carattere valido
	//			<false> : carattere non valido
	// **************************************************		
	var	checkRegEx=function(e,reg){
		var key = "";
		var isCtrl = "";
		
		if(window.event) {
			key = e.keyCode;
			isCtrl = window.event.ctrlKey;
		}
		else if(e.which) {
			key = e.which;
			isCtrl = e.ctrlKey;
		}
		
		if(isCtrl){
			return true;
		}
	
		var chCode = ('charCode' in e) ? e.charCode : e.keyCode;
		if (isNaN(chCode) || chCode === 0) return true;	
		keychar = String.fromCharCode(chCode);	
		return (reg.test(keychar));
	};

	$(document).ready(function(){
		initRegExt();
	});
	
	return module;

})();


//*****************************************************************		
// ecsa.validate
// blocco di funzioni per la gestione della valdiazione di una form
// ****************************************************************
ecsa.validate = (function(){

	var module = {};

	// ******************************************************************		
	// function : setValidateMessageRules
	// Contiene la customizzazione dei messaggi associati alle validazioni
	// native di jquery validator
	// ******************************************************************
	var setValidateMessageRules=function(){
		$.validator.messages.required = ecsa.utility.getPropertiesByCode("cn.checkout.form.field.required");
		$.validator.messages.email = ecsa.utility.getPropertiesByCode("cn.checkout.form.email.invalid");
	};

	// *****************************************************************		
	// function : validateForm
	// Funzione esposta all'esterno per la gestione della validazione 
	// di una form
	// *****************************************************************
	// Parametri :	oggetto json options
	//				options.idform :	identificativo della form va passato
	//				con il #
	// Return : nessuno
	// *****************************************************************
	module.validateForm=function(options){
		jQuery.validator.setDefaults({
				errorPlacement: function(error, element) {
				var dataCheckobj=$(element).data("checkobj");
				$(element).next(".has-error-element").remove();
				var htmlError = "<div class='has-error-element'>"+error.text()+"</div>";
				$(htmlError).insertAfter(element);
				$(element).parents("div.cn-form-group").addClass("has-error");
				if(!ecsa.utility.isNullOrEmpty(dataCheckobj)){
					$(dataCheckobj).next(".has-error-element").remove();
					$(htmlError).insertAfter(dataCheckobj);
					$(dataCheckobj).parents("div.cn-form-group").addClass("has-error");
				}
			},
				onfocusout:function (element) {
				var validator = $(options.idform).validate();
				var dataCheckobj=$(element).data("checkobj");
				if(validator.element(element)){
					$(element).parents("div.cn-form-group").removeClass("has-error");
					$(element).next(".has-error-element").remove();
					if(!ecsa.utility.isNullOrEmpty(dataCheckobj)){
						$(dataCheckobj).parents("div.cn-form-group").removeClass("has-error");
						$(dataCheckobj).next(".has-error-element").remove();
					}
				}
			},
			invalidHandler: function(event, validator) {
				ecsa.utility.loadingEnd();
			}
		});
		
		setValidateMessageRules();

		/* **************************************************************** */
		/*                    METODI DI VALIDAZIONE CUSTOM                  */
		/* **************************************************************** */
		
		/* Metodo <minLen> controlla che la lunghezza minima sia rispettata.  */
		/* La lunghezza dev'essere specificata in un attributo data-minlength */
		/* associato all'elemento che si vuole controllare.                    */
		$.validator.addMethod("minLen", function (value, element, param) {
			var minlength = $(element).data("minlength");
			if ($(element).val().length < minlength) {
				return false;
			} else {
				return true;
			}
		},function(param,element){
			var minlength = $(element).data("minlength");
			/*var isPassword = ($(element).attr("type")==="password") ? true : false;
			if(isPassword){
				$(element).attr("type","text");
				$(element).data("oldtype","password");
			}*/
			
			return ecsa.utility.getPropertiesByCodeParam("cn.checkout.pwd.invalid",minlength);		
		});
		
		/* Metodo <checkobj> controlla che due elementi siano uguali.                */
		/* Ad esempio <email> - <conferma email> o <password> - <conferma password>  */
		/* Associare all'elemento che si vuole controllare l'attributo data-checkobj */
		/* nel quale sara' specificato id dell'elemento da confrontare.              */
		$.validator.addMethod("checkobj", function (value, element, param) {
			var checkobj = $(element).data("checkobj");
			if(ecsa.utility.isElementExist(checkobj)){
				var checkobjvalue = $(checkobj).val();
				if(!ecsa.utility.isNullOrEmpty(checkobjvalue)){
					if(checkobjvalue === value){
						return true;
					}
					return false;
				}else{
					console.log("ATTENZIONE !!! checkobj <"+checkobj+"> non valorizzato");
					return true;
				}
				
			}else{
				console.log("ATTENZIONE !!! checkobj <"+checkobj+"> non esiste");
			}
		},function(param,element){
			var message = $(element).data("checkobj_message");
			return ecsa.utility.getPropertiesByCodeParam(message);	
		});
		/* **************************************************************** */
		
		$.validator.addClassRules({
			"v-required": {required: true},
			"v-email": {email:true},
			"v-minLen": {minLen: true},
			"v-checkobj": {checkobj: true}
		});		
				
		
		if(!ecsa.utility.isNullOrEmpty(options.idform)){
			$(options.idform).validate({
				/*onfocusout: false,
				onkeyup: false*/
			});
		}
	};
	
	return module;

})();

//****************************************************************		
//ecsa.utility
//blocco di funzioni generiche
//****************************************************************
ecsa.utility = (function(){
	var module = {};
	
	// *****************************************************************		
	// function : isIEBrowser
	// Funzione per verificare se siamo su IE o altro BROWSER  
	// *****************************************************************
	// Parametri : nessuno
	// Return : <true>  se BROWSER IE 
	//          <false> se altro BROWSER
	// *****************************************************************
	var isIEBrowser=function() {
        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE ");

        if (msie > 0)      // If Internet Explorer, return version number
            return true;
        else                 // If another browser, return 0
        	return false;

        return false;
    };
	
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
	// function : submitForm
	// Funzione esposta all'esterno per fare il submit di una form se  
	// non si vuole utilizzare un bottone di tipo SUBMIT
	// *****************************************************************
	// Parametri : oggetto json options
    //             options.idform : identificativo della form passato
    //			   con il #
	// Return : nessuno
	// *****************************************************************    
    module.submitForm=function(options){
    	if(!ecsa.utility.isNullOrEmpty(options.idform)){
    		var idform = options.idform;
    		var option = {loadingelement:idform};
    		ecsa.utility.loadingStart(option);
    		$(idform).submit();
    	}else{
    		console.log("Attenzione !!! submitForm metodo POST : <idform> non valorizzato");
            return;
    	}
    };
    
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

		//TODO FARE LA VALIDAZIONE
    	
    	
    	if(ecsa.utility.isNullOrEmpty(options.url)){
            console.log("Attenzione !!! ajaxCall : <url> non valorizzato");
            return;
        }

        options.method = ecsa.utility.isNullOrEmpty(options.method) ? "POST" : options.method;
        options.data = ecsa.utility.isNullOrEmpty(options.data) ? "" : options.data;
        options.enableLoadingEnd = ecsa.utility.isNullOrEmpty(options.enableLoadingEnd) ? true : options.enableLoadingEnd;
        if(options.method==="POST"){
        	if(!ecsa.utility.isNullOrEmpty(options.idform)){
        		var idform = options.idform;
        		options.data = ecsa.utility.funSerializeObject(idform);
        	}else{
        		console.log("Attenzione !!! ajaxCall metodo POST : <idform> non valorizzato");
                return;
        	}
        }
        options.type=options.method; // aggiunto perche' nelle versioni vecchie di jquery il parametro methods non funziona
        options.dataType = ecsa.utility.isNullOrEmpty(options.dataType) ? "HTML" : options.dataType;
        options.success = ecsa.utility.isNullOrEmpty(options.success) ? function(data){
            console.log("AJAX CALL SUCCESS");
            console.log(data);
           	//ecsa.utility.loadingEnd();
        } : options.success;
        options.async = ecsa.utility.isNullOrEmpty(options.async) ? true : options.async;
        options.error = ecsa.utility.isNullOrEmpty(options.error) ? function(){
        	console.log("AJAX CALL ERROR");
        	} : options.error;
        options.beforeSend = ecsa.utility.isNullOrEmpty(options.beforeSend) ? function(){
        	//ecsa.utility.loadingStart(options);
        	} : options.beforeSend;
        options.complete = ecsa.utility.isNullOrEmpty(options.complete) ? function(){
        	if(options.enableLoadingEnd){
        		//ecsa.utility.loadingEnd();
        	}
        } : options.complete;
        options.cache = ecsa.utility.isNullOrEmpty(options.cache) ? false : options.cache;
        
        $.ajax(options);
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
		var parent = $(element).closest('.append-loading');
		if(ecsa.utility.isElementExist(element)){
			element = $(element).find('.append-loading');
		}
		// il messaggio per ora non e' utilizzato si e' scelto di mettere un immagine
		//var message = ecsa.utility.getPropertiesByCode('text.loadingMessage');		
		$(parent).append(selector.html.loading);
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

    /*
    module.hashchange=function(step){
	    location.hash = "currentstep="+step;
	}
	*/
    module.getHashParemter=function(){
    	
    	var options={};
		options.url="/CN/cart/rollover/MiniCart";
		options.method="GET";
		options.data={totalDisplay:0};
		//options.dataType="json";
		//options.loadingelement=loadingelement;
		options.success=function(data){
			console.log(data);
		};
    	
		ecsa.utility.ajaxCall(options);
		
    	/*
    	if(!ecsa.utility.isNullOrEmpty(options) && options.browserBack){
	    	if(!ecsa.utility.isNullOrEmpty(location.hash)){
			    var hash = location.hash;
				var splithash = hash.split("=");
				var key = splithash[0];
				var value = parseInt(splithash[1]);
				//console.log("ESISTE : " + $(selector.step+".sel-step"+value).length);
				//if(value===2){
					//value = 1;
				//}
				$(selector.step+".sel-step"+value).click();
	    	}else{
	    		console.log("ATTENZIONE !!! getHashParemter : non ci sono parametri hask nel URL.");
	    	}
	    }
	    */
	};
    
    // *****************************************************************		
	// function : getPropertiesByCode
	// Funzione esposta all'esterno per recuperare il messaggio d'errore 
	// dalle properties dal codice passato
	// *****************************************************************
	// Parametri : <code> della properties da recuperare
	// Return : messaggio di ritorno dal server
	// *****************************************************************      
    module.getPropertiesByCode=function(code){
		var p = {"code":code};
		var mex =  getPropertiesValue(p);
		if(ecsa.utility.isNullOrEmpty(mex)){
			mex = code;
		}	
		return mex;
	};
	
    // *****************************************************************		
	// function : getPropertiesByCodeParam
	// Funzione esposta all'esterno per recuperare il messaggio d'errore 
	// dalle properties dal codice/parametri passati
	// *****************************************************************
	// Parametri : <code> della properties da recuperare
    //			   <params> parametri da passare al messaggio separati 
    //			   da "," se pie' di un parametro 
	// Return : messaggio di ritorno dal server
	// *****************************************************************    
	module.getPropertiesByCodeParam=function(code,params){
		var p = {"code":code,"params":params};
		var mex =  getPropertiesValue(p);
		if(ecsa.utility.isNullOrEmpty(mex)){
			mex = code;
		}	
		return mex;
	};
	
	// *****************************************************************		
	// function : getPropertiesValue
	// Funzione per recuperare il messaggio d'errore dal server 
	// *****************************************************************
	// Parametri : <pObj> oggetto jsondella properties da recuperare
	// Return : <mex> messaggio di ritorno dal server
	// *****************************************************************  	
	var getPropertiesValue=function(pObj){
		var url = window.location.origin+"/CN/getPropertiesValue";
		//console.log(window.location);
		var mex = "";
				
		$.ajax({
			 method:"GET",
	         url:url,
	         data:pObj,
	         async: false, // non togliere
	         success: function(data) {
	        	 mex = data;
             },
             error: function(xhr, status, error) {
            	 console.log("Recupero proporeties fallito : " + error);
             }
	    });     		
		
		return mex;
	};
    
	// *****************************************************************		
	// function : createDateSelect
	// Funzione esposta all'esterno per popolare le select di 
	// giorno-mese-anno 
	// *****************************************************************
	// Parametri : <p> oggetto json 
	//			    p.selectorgg : selettore select giorno
	//			    p.selectormm : selettore select mese
	//			    p.selectoryy : selettore select anno	
	// Return : nessuno
	// *****************************************************************	
	module.createDateSelect=function(p){
		
		var selectorgg = p.selectorgg;
		var selectormm = p.selectormm;
		var selectoryy = p.selectoryy;
		var option = "";
		var i = 1;
		while (i<=31 ) {
			option = $("<option>");
			var gg = ecsa.utility.pad(i,2);
			$(option).html(gg).attr({"value":gg});
			$(selectorgg).append(option);
			i++;
		}
		
		i = 1;
		while (i<=12 ) {
			option = $("<option>");
			var mm = ecsa.utility.pad(i,2);
			$(option).html(mm).attr({"value":mm});
			$(selectormm).append(option);
			i++;
		}
		
		var currentYear = (new Date()).getFullYear();
		var maxYear = currentYear;
		var orderAsc = false;
		i = maxYear - 100;
		while (i<=maxYear ) {
		  option = $("<option>");
		  var yy = currentYear--;
		  if(orderAsc){
			  yy = i;
		  }
		  
		  $(option).html(yy).attr({"value":yy});
		  $(selectoryy).append(option);
		  i++;
		}			
	};
	
	// *****************************************************************		
	// function : getDistrict
	// Funzione esposta all'esterno per recuperare il district dal CAP
	// CAP
	// *****************************************************************
	// Parametri : <option> oggetto json 
	//			    option.cap : valore del CAP
	//			    option.countryIso : valore della NAZIONE
	//			    option.selectorSelect : selettore della select da 
	//			    popolare	
	// Return : nessuno
	// *****************************************************************	
	module.getDistrict=function(option){
		var cap=option.cap;
		var countryIso=option.countryIso;
		var selectorSelect=option.selectorSelect;
		var loadingelement=ecsa.utility.isNullOrEmpty(option.loadingelement) ? "" : option.loadingelement;
		
		if(ecsa.utility.isNullOrEmpty(cap) || ecsa.utility.isNullOrEmpty(countryIso)){
			console.log("ATTENZIONE !!! getDistrict <cap> : "+cap+" o <countryIso> : "+countryIso+" non sono valorizzati" );
			return false;
		}

		var options={};
		options.url=urlController.getDistrictOrStateService;
		options.method="GET";
		options.data={countryIso:countryIso,cap:cap};
		options.dataType="json";
		options.loadingelement=loadingelement;
		options.success=function(data){
			data = jQuery.parseJSON(data);
			if(!ecsa.utility.isNullOrEmpty(data) && data.length > 0){
				var selectedVal = $(selector.district).val();
				var options = {selectorSelect:selectorSelect,selectedVal:selectedVal};
				ecsa.utility.createSelectoption(data,options);
		
				$(selectorSelect).change();
			}else{
				$(selector.selecttown).addClass("hide");
	    		$(selector.selectdistrict).addClass("hide");
	    		/*Gestione particolare per brand che utilizzano le multiselect*/
	    		$(selector.selecttown).next("span").addClass("hide");
	    		$(selector.selectdistrict).next("span").addClass("hide");
	    		/* ************************************************************/
	    		$(selector.district).removeClass("hide");
	    		$(selector.town).removeClass("hide");
			}
			
		};
		
		ecsa.utility.ajaxCall(options);
	};
	
	// ******************************************************************		
	// function : getTown
	// Funzione esposta all'esterno per recuperare le town dalla district
	// ******************************************************************
	// Parametri : <option> oggetto json 
	//			    option.cap : valore del CAP
	//			    option.countryIso : valore della NAZIONE
	//			    option.district : valore della DISTRICT	
	//			    option.selectorSelect : selettore della select da 
	//			    popolare	
	// Return : nessuno
	// *****************************************************************	
	module.getTown=function(option){
		var cap=option.cap;
		var countryIso=option.countryIso;
		var district=option.district;
		var selectorSelect=option.selectorSelect;
		var loadingelement=ecsa.utility.isNullOrEmpty(option.loadingelement) ? "" : option.loadingelement;
		
		if(ecsa.utility.isNullOrEmpty(cap) || 
		   ecsa.utility.isNullOrEmpty(countryIso) || 
		   ecsa.utility.isNullOrEmpty(district)){
			console.log("ATTENZIONE !!! getDistrict <cap> : "+cap+" o <countryIso> : "+countryIso+" o <district> : "+district+" non sono valorizzati" );
			return false;
		}

		var options={};
		options.url="town-service.json";
		options.method="GET";
		options.data={countryIso:countryIso,cap:cap,districtOrState:district};
		options.dataType="json";

		options.loadingelement=loadingelement;
		options.success=function(data){
			var selectedVal = $(selector.town).attr("value");
			var options = {selectorSelect:selectorSelect,selectedVal:selectedVal};
			ecsa.utility.createSelectoption(jQuery.parseJSON(data),options);
		};
		
		ecsa.utility.ajaxCall(options);
	};
	
	// *******************************************************************		
	// function : createSelectoption
	// Funzione per aggiungere le option a una select
	// *******************************************************************
	// Parametri : data: oggetto json con la lista dei valori delle option 
	//			   options: oggetto json con il seletore della select da 
	//			   gestire e il valore sel record selezionato nel campo
	//			   input nascosto associato alla select
	// Return : nessuno
	// *******************************************************************	
	module.createSelectoption=function(data,options){
		var selectorSelect = options.selectorSelect;
		var selectedVal = options.selectedVal;
		$.each(data, function (key, item) {
			var option = $("<option>");
			var selected = "";
			if(item === selectedVal){
				$(option).attr("selected","selected");
			}
			$(option).val(item).html(item);
			
			$(selectorSelect).append(option);
		});
		
	};

	// *******************************************************************		
	// function : backToTop
	// Funzione esposta all'eterno per tornare al TOP della pagina
	// *******************************************************************
	// Parametri :  options: oggetto json con il seletore dell'ancora 
	//             a cui tornare e tempo di slide dell'animazione.
	// 			   Entrambi i parametri sono obbligatori
	// Return : nessuno
	// *******************************************************************
	module.backToTop=function(options){
		if(!ecsa.utility.isNullOrEmpty(options)){
			if(!ecsa.utility.isNullOrEmpty(options.offsetSelector) &&
			   !ecsa.utility.isNullOrEmpty(options.backToTopTime)){
				var offsetSelector = options.offsetSelector;
				var time = options.backToTopTime;
				var positionTOP = $(offsetSelector).offset();
				$('html, body').stop(true, false).animate({
					scrollTop:positionTOP.top - 80
				}, time);		
			}
			else{
				console.log("Attenzione !!! backToTop : offsetSelector o backToTopTime non valorizzati");
			}
		}else{
			console.log("Attenzione !!! backToTop : options non valorizzata");
		}
	};

	// *********************************************************************	
	// function : pad
	// Funzione esposta all'eterno che aggiunge n ZERI davanti a una stringa
	// *********************************************************************
	// Parametri : <str> : stringa dove aggiungere gli ZERI
	//			   <max> : numero di ZERI d'aggiungere
	// Return : String con gli ZERI davanti
	// *********************************************************************

	module.pad=function(str, max) {
	  str = str.toString();
	  return str.length < max ? ecsa.utility.pad("0" + str, max) : str;
	};
	
	
	// *********************************************************************	
	// function : showModalError
	// Funzione esposta all'eterno per mostrare una pop up con messaggio
	// d'errore
	// *********************************************************************
	// Parametri : oggetto json il codice del messaggio da visualizzare
	//			   e il titolo della pop up (default ERRORE)
	// Return : nessuno
	// *********************************************************************	
	module.showModalError=function(options) {
		var errorMessage = ecsa.utility.isNullOrEmpty(options.errorMessage) ? "messaggio non valorizzato" : ecsa.utility.getPropertiesByCode(options.errorMessage);
		var title = ecsa.utility.isNullOrEmpty(options.title) ? ecsa.utility.getPropertiesByCode("cn.checkout.modalError.title") : ecsa.utility.getPropertiesByCode(options.errorMessage);
		$(selector.modalError+" .modal-title").html(title);
		$(selector.modalError+" #errorMessage").html(errorMessage);
		$(selector.modalError).modal("show");
	};

	// *********************************************************************	
	// function : plgiCheck
	// Viene istanziato il plugin icheck per customizzare i radio button 
	// e le checkbox
	// *********************************************************************
	// Parametri : nessuno
	// Return : nessuno
	// *********************************************************************	
	module.plgiCheck=function(){	
		$('input[type="checkbox"],input[type="radio"]').each(function(i,el){
			   if(!$(el).parent("div").hasClass("icheckbox_futurico") && 
	              !$(el).parent("div").hasClass("iradio_futurico") && 
	              !$(el).parents("ul").hasClass("multiselect-container")){	
				   $(el).iCheck({
				      checkboxClass: 'icheckbox_futurico',
					  radioClass: 'iradio_futurico',
					  increaseArea: '20%'	    
				   });	
				} 
			   
			   $(el).on('ifChecked', function(){
				   $(this).prop('checked', true);
				   $(this).trigger("click");
			   });
			   
			   $(el).on('ifUnchecked', function(){
				   $(this).prop('checked', false);
				   $(this).trigger("click");
			   });
		});
	};
	
	// *********************************************************************	
	// function : isValidURL
	// Viene verificato che la stringa passata sia una url valida
	// *********************************************************************
	// Parametri : nessuno
	// Return : nessuno
	// *********************************************************************
	module.isValidURL=function(url) {
	  var regex = /(http|https):\/\/(\w+:{0,1}\w*)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%!\-\/]))?/;
	  return regex.test(url);
	};
	
	return module;

})();




