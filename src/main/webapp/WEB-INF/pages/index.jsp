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



<script src="<c:url value="/resources/js/main.js" />"></script> 

<!-- IMPORT DATA TABLE -->
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>  
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<!-- Validator include -->
<script src="<c:url value="/resources/js/bootstrapValidator.js" />"></script>
<script src="<c:url value="/resources/js/it_IT.js" />"></script>
<link href="<c:url value="/resources/css/bootstrapValidator.css" />" rel="stylesheet">


<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">

<body>

<div class="container">
	<div class="jumbotron">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	</div>
	<h1 align="center" >ECSA INDEX</h1>
	<br>
	<div align="center">
		<h2><a href="/ecsa/notemanagement">Management</a></h2>
		<h2><a href="/ecsa/locazioni">Locazioni</a></h2>
	</div>
		
		<br>
	<!-- <div align="center"><button align="center" type="submit" name="op" onclick="window.location.href='/ecsa/notemanagement'" class="btn btn-file">MANAGMENT</button></div>
	<br>
	<div align="center"><button align="center" type="submit" name="op" onclick="window.location.href='/ecsa/locazioni'" class="btn btn-file">LOCAZIONI</button></div> -->
	
</body>
</html>