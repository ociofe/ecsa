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

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>



<script src="<c:url value="/resources/js/main.js" />"></script> 
<script src="<c:url value="/resources/js/utility.js" />"></script> 

<!-- IMPORT DATA TABLE -->
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>  
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<!-- Validator include -->
<script src="<c:url value="/resources/js/bootstrapValidator.js" />"></script>
<script src="<c:url value="/resources/js/it_IT.js" />"></script>
<link href="<c:url value="/resources/css/bootstrapValidator.css" />" rel="stylesheet">


<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">

<body>

<form action="http://localhost:8080/ecsa/user/register" method="POST" id="frmLogin">

<label>id</label>
<input type="text" name="id" />
<label>user</label>
<input type="text" name="user" />
<label>password</label>
<input type="text" name="password"/>
<label>name</label>
<input type="text" name="name"/>
<label>surname</label>
<input type="text" name="surname"/>
<label>mail</label>
<input type="text" name="mail"/>

<input type="button" id="btnlogin" value="login"></input


</form>
	
	
</body>
</html>



<script type="text/javascript">

    $(document).ready(function(){
    	$("#btnlogin").on("click",function(){
    		var options = {};
    		options.idform = "#frmLogin";
    		options.url = $("#frmLogin").attr("action");
    		options.dataType="json";
    		ecsa.utility.ajaxCall(options);
    	})
    	
    	
    	
    })


</script>

