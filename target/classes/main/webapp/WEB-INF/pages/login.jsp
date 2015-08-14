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

<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">  
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet">


<script src="<c:url value="/resources/js/main.js" />"></script> 
<script src="<c:url value="/resources/js/utility.js" />"></script> 
<script src="<c:url value="/resources/js/utility.ajax.js" />"></script> 

<!-- IMPORT DATA TABLE -->
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>  
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<!-- Validator include -->
<script src="<c:url value="/resources/js/bootstrapValidator.js" />"></script>
<script src="<c:url value="/resources/js/it_IT.js" />"></script>
<link href="<c:url value="/resources/css/bootstrapValidator.css" />" rel="stylesheet">


<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet"> 
<body>


<div class="alert alert-danger hide" role="alert" id="errorlist"></div>

<div class="container">
	<div class="row">
		<form id="frmLogin" class="form-horizontal">
			 <div class="form-group">
				<label class="control-label col-sm-2" for="user">User :</label>
				<div class="col-sm-10">
					<input class="form-control input-sm chat-input" id="user" type="text" name="user" value="PIPPO">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="name">Name :</label>
				<div class="col-sm-10">
					<input class="form-control input-sm chat-input" id="name" type="text" name="name" value="PLUTO">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="surname">Surname :</label>
				<div class="col-sm-10">
					<input class="form-control input-sm chat-input" id="surname" type="text" name="surname" value="PIPPONE">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="mail">Email :</label>
				<div class="col-sm-10">
					<input class="form-control input-sm chat-input" id="mail" type="text" name="mail" value="pippo@libero.it">
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-2" for="password">Password :</label>
				<div class="col-sm-10">
					<input class="form-control input-sm chat-input" id="password" type="text" name="password" value="123456">
				</div>
			</div>
			 <div class="wrapper">
				<span class="group-btn">     
					<input class="btn btn-primary btn-md" type="button" id="bottone" value="Invia i dati">
			    </span>
		    </div>
		 </form>
     </div>
 </div>
 
 <script type="text/javascript">
 $(document).ready(function() {
	 
	 $('#bottone').on("click",function(){
         var options = {};
         options.idform="#frmLogin";
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
             esle{
            	 
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
		 
/*  		 var JSONObject= {"name":name, "password":password, "user":user, "name":name, "mail":mail, "surname":surname };
		 var jsonData = JSON.parse( JSONObject );   */  
		 /* var formData = form2object('frmLogin'); */
		 
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
	
</body>
</html>








