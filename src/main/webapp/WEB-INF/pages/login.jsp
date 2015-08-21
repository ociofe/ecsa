<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>




<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>    
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css"> 
<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> 

<!-- FONT -->
<link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'>

<!-- BOOTSTRAP -->
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">  
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet">

<!--  -->
<script src="<c:url value="/resources/js/main.js" />"></script> 
<script src="<c:url value="/resources/js/utility.js" />"></script> 
<script src="<c:url value="/resources/js/utility.ajax.js" />"></script> 

<script src="<c:url value="/resources/js/jquery-picture-min.js" />"></script> 

<!-- Validator include -->
<script src="<c:url value="/resources/js/bootstrapValidator.js" />"></script>
<script src="<c:url value="/resources/js/it_IT.js" />"></script>
<link href="<c:url value="/resources/css/bootstrapValidator.css" />" rel="stylesheet">


<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">


<body>
<!-- <img src="/images/loading.gif" id="loading-indicator" style="display:none" />
<img src="/ecsa/resources/images/loading.gif" id="loading-indicator" /> -->


<div class="alert alert-danger hide" role="alert" id="errorlist"></div>

<!-- REGISTRATION  -->
<div class="container">
 <img src="/ecsa/resources/images/ecsaLogo.jpg" />
    <h3 class="panel-title">Registration</h3>
 
	<div class="row">
		<div class="col-sm-12">
		<form id="frmRegistration" class="form-horizontal">
			 <div class="form-group">
				<label class="control-label col-sm-2" for="user">User :</label>
				<div class="col-sm-8">
					<input class="form-control input-sm chat-input" id="user" type="text" name="user" value="PIPPO">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="name">Name :</label>
				<div class="col-sm-8">
					<input class="form-control input-sm chat-input" id="name" type="text" name="name" value="PLUTO">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="surname">Surname :</label>
				<div class="col-sm-8">
					<input class="form-control input-sm chat-input" id="surname" type="text" name="surname" value="PIPPONE">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="mail">Email :</label>
				<div class="col-sm-8">
					<input class="form-control input-sm chat-input" id="mail" type="text" name="mail" value="pippo@libero.it">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="password">Password :</label>
				<div class="col-sm-8">
					<input class="form-control input-sm chat-input" id="password" type="text" name="password" value="123456">
				</div>
			</div>
			 <div class="wrapper" align="center">
				<span class="group-btn" >     
					<input class="btn btn-primary btn-md" type="button" id="bottone" value="Invia i dati">
			    </span>
		    </div>
		 </form>
		</div>
     </div>
 </div>
 
 <script type="text/javascript">
 $(document).ready(function() {
	 
	 $('#bottone').on("click",function(){
         var options = {};
         options.idform="#frmRegistration";
         options.url="http://localhost:8080/ecsa/user/register";
         options.dataType="json";
         options.headers= { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	     };
        
         options.success = function(data){
           
             console.log(data);
             
             if(data.status=="FAIL"){
            	 $("#errorlist").html(data.code).removeClass("hide");
             }
             else {
				
			}
         };
         
         myshow.utility.ajax.ajaxCall(options);
         
     }) ;

 		$('#bottone1').click(function(){
 			event.preventDefault();
		 var name = document.getElementById("name").value;
		 var surname = document.getElementById("surname").value;
		 var user = document.getElementById("user").value;
		 var name = document.getElementById("name").value;
		 var password = document.getElementById("password").value;
		 var mail = document.getElementById("mail").value;

			 $.ajax({
			    headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				 type: "POST",
				 url: "http://localhost:8080/ecsa/user/register",
				 dataType: "json",
				 data: JSON.stringify({ "name":name, "password":password, "user":user, "name":name, "mail":mail, "surname":surname }),
				 cache: false,
				 success: function(msg)
			      {
			        $("#risultato").html(msg); 
			      },
			      error: function()
			      {
			        alert("Chiamata fallita, si prega di riprovare..."); 
			      }
		 	});
		 
		 return false;
		 });
 });
</script>

<!-- END  REGISTRATION  -->

<!-- LOGIN  -->
<div class="container">
 
    <h3 class="panel-title">LOGIN</h3>
 
	<div class="row">
	<div class="col-sm-12">
		<form id="frmLogin" class="form-horizontal">
			 <div class="form-group">
				<label class="control-label col-sm-2" for="mail">Email :</label>
				<div class="col-sm-8">
					<input class="form-control input-sm chat-input" id="mail" type="text" name="mail">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Password :</label>
				<div class="col-sm-8">
					<input class="form-control input-sm chat-input" id="password" type="text" name="password">
				</div>
			</div>
			<div class="wrapper" align="center">
				<span class="group-btn" >     
					<input class="btn btn-primary btn-md" type="button" id="bottoneLogin" value="Invia i dati">
			    </span>
		    </div>
		 </form>
		</div>
     </div>
 </div>

 <script type="text/javascript">
 $(document).ready(function() {

	 $('#bottoneLogin').on("click",function(){
         var options = {};
         options.idform="#frmLogin";
         options.url="http://localhost:8080/ecsa/user/login";
         options.dataType="json";
         options.headers= { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	     };
        
         options.success = function(data){
           
             console.log(data);
             
             if(data.status=="FAIL"){
            	 $("#errorlist").html(data.code).removeClass("hide");
             }
             else {
				/* Go to another page */
			}
           
         };
         
         myshow.utility.ajax.ajaxCall(options);
         
     }) ;

 });
</script>
	<!-- END LOGIN  -->
	
	<!-- SEARCH PAGE  -->

<div class="container">
 
    <h3 class="panel-title">SEARCH</h3>
 
	<div class="row">
		<div class="col-sm-12">
		<form id="frmSearch" class="form-inline">
			 <div class="form-group">
				<label class="" for="searchseries">Series :</label>
				<input class="form-control" id="searchseries" type="text" name="searchseries">
			</div>
		    <button class="btn btn-primary" placeholder="Search" type="button" id="bottoneSearch" value="Invia i dati">Search...</button>
			
		 </form>
		</div>
     </div>
 		<div id="imagelist">
      		<!-- <img class="responsiveImg" src="http://thetvdb.com/banners/graphical/70327-g.jpg" /> -->
		</div>
 </div>

 <script type="text/javascript">
 $(document).ready(function() {

	 $('#bottoneSearch').on("click",function(){
		 var param = document.getElementById("searchseries").value;
         var options = {};
         options.method="POST";
         options.idform="#frmSearch";
         options.url="http://localhost:8080/ecsa/search/series?searchParam="+param;
         options.dataType="json";
         options.headers= { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	     };
        
         options.success = function(data){
           
             console.log(data);
             $("#imagelist").empty();
             if(data.status=="FAIL"){
            	 $("#errorlist").html(data.code).removeClass("hide");
             }
             else {
            	 for(var i = 0; i < data.length; i++) {
            		 var seriesId = data[i].seriesid;
                   	 var my_awesome_script = document.createElement('img');
                   	 my_awesome_script.className = "responsiveImg";
                	 my_awesome_script.setAttribute('src','http://thetvdb.com/banners/graphical/'+seriesId+'-g.jpg');
                	 $("#imagelist").append(my_awesome_script);
                	 
            		}$("img").error(function(){
            	        $(this).hide();
            		});

			}
           
         };
         
         myshow.utility.ajax.ajaxCall(options);
         
     }) ;

 });
 


 /* Replace image */
/*  $('img').error(function(){
     $(this).attr('src', 'missing.png');
}); */

 
</script>
	<!-- END SEARCH SERIES  -->
	
</body>
</html>








