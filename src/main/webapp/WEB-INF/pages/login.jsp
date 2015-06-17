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
	<br>
	<br>
	
	<h4 align="center" style="color:red">
	<c:out value="${errorMessage}" />
	</h4>
	
<%-- 	<form:form  class="form-horizontal" method="POST" action="j_spring_security_check" modelAttribute="login" >
		   <div class="form-group">
		      <label for="username" class="col-sm-2 control-label">Username</label>
		      <div class="col-sm-8">
		         <input name="username" type="text" class="form-control" id="username" >
		      </div>
		   </div>
		   		   <div class="form-group">
		      <label for="password" class="col-sm-2 control-label">Password</label>
		      <div class="col-sm-8">
		         <input name="password" type="text" class="form-control" id="password" >
		      </div>
		   </div>
		   <button type="submit" class="col-sm-offset-2 col-sm-2 btn btn-primary">Login</button>
	</form:form> --%>
	 	<form:form  class="form-horizontal" method="POST" action="/ecsa/j_spring_security_check" >
		   <div class="form-group">
		      <label for="j_username" class="col-sm-2 control-label">Username</label>
		      <div class="col-sm-8">
		         <input name="j_username" type="text" class="form-control" id="j_username" >
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="j_password" class="col-sm-2 control-label">Password</label>
		      <div class="col-sm-8">
		         <input name="j_password" type="password" class="form-control" id="j_password" >
		      </div>
		   </div>
		   <button type="submit" value="Login" class="col-sm-offset-2 col-sm-2 btn btn-primary">Login</button>
	</form:form>
		
	
	
</body>
</html>