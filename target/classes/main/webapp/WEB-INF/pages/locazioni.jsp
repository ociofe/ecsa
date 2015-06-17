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

<!-- Validator include -->
<script src="<c:url value="/resources/js/bootstrapValidator.js" />"></script>
<script src="<c:url value="/resources/js/it_IT.js" />"></script>
<link href="<c:url value="/resources/css/bootstrapValidator.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">

<script type="text/javascript">
$(document).ready(function() {
    $('#forminsert').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	codFornitore: {
                message: 'The fornitore is not valid',
                validators: {
                    notEmpty: {
                        message: 'The fornitore is required and cannot be empty'
                    },
                    stringLength: {
                        max: 30,
                        message: 'The programDescription must be more than 1 and less than 30 characters long'
                    }
                }
            }
        }
    
	});
});
$(document).ready(function() {
    $('#formcancel').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	codFornitore: {
                message: 'The fornitore is not valid',
                validators: {
                    notEmpty: {
                        message: 'The fornitore is required and cannot be empty'
                    },
                    stringLength: {
                        max: 30,
                        message: 'The programDescription must be more than 1 and less than 30 characters long'
                    }
                }
            }
        }
    
	});
});

        
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	
		<div  align="center">
			<div onclick="window.location='index';" class="jumbotron ">			
		</div>
		<h1 align="center" >Locazioni</h1>
		<br>
		
		<!--Inserisci Locazione  -->
		<form:form id="forminsert" class="form-horizontal" method="POST" action="locazioni/insert" modelAttribute="locazione" >
		   <div class="form-group">
		      <label for="fornitore" class="col-sm-2 control-label">Fornitore</label>
		      <div class="col-sm-8">
		         <input name="codFornitore" type="text" class="form-control" id="codFornitore" >
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="C1orCH" class="col-sm-2 control-label">C1 or CH</label>
		      <div class="col-sm-8">
		      	<form:select path="C1orCH"  class="form-control" id="C1orCH" name="C1orCH" type="text">
				  	<form:option value="C1">C1</form:option>
					<form:option value="CH">CH</form:option>
				 </form:select>
		      </div>
		  </div>
		  <div class="form-group">
		      <label for="magazzino" class="col-sm-2 control-label">Magazzino</label>
		      <div class="col-sm-8">
		      	 <form:select path="magazzino"  class="form-control" id="C1orCH" name="magazzino" type="text">
				  	<form:option value="BAL">BAL</form:option>
					<form:option value="FLW">FLW</form:option>
				 </form:select>
		      </div>
		  </div>
		  		  <div class="form-group">
		      <label for="magazzino" class="col-sm-2 control-label">Zona</label>
		      <div class="col-sm-8">
		      	 <form:select path="zona"  class="form-control" id="zona" name="zona" type="text">
				  	<form:option value="Z1">Z1</form:option>
					<form:option value="A2">A2</form:option>
				 </form:select>
		      </div>
		  </div>
		   <button type="submit" class="btn btn-primary">Crea Locazione</button>
		</form:form>
		
		<br>
		
		<br>
		
		<!--Elimina Locazione  -->
		<form:form id="formcancel" class="form-horizontal" method="POST" action="locazioni/delete" modelAttribute="locazione" >
		   <div class="form-group">
		      <label for="fornitore" class="col-sm-2 control-label">Fornitore</label>
		      <div class="col-sm-8">
		         <input name="codFornitore" type="text" class="form-control" id="codFornitore" >
		      </div>
		   </div>
		   
		   <div class="form-group">
		      <label for="C1orCH" class="col-sm-2 control-label">C1 or CH</label>
		      <div class="col-sm-8">
		      	<form:select path="C1orCH"  class="form-control" id="C1orCH" name="C1orCH" type="text">
				  	<form:option value="C1">C1</form:option>
					<form:option value="CH">CH</form:option>
				 </form:select>
		      </div>
		  </div>
		   <div class="form-group">
		      <label for="magazzino" class="col-sm-2 control-label">Magazzino</label>
		      <div class="col-sm-8">
		      	 <form:select path="magazzino"  class="form-control" id="C1orCH" name="magazzino" type="text">
				  	<form:option value="BAL">BAL</form:option>
					<form:option value="FLW">FLW</form:option>
				 </form:select>
		      </div>
		  </div>
		  <div class="form-group">
		      <label for="magazzino" class="col-sm-2 control-label">Zona</label>
		      <div class="col-sm-8">
		      	 <form:select path="zona"  class="form-control" id="zona" name="zona" type="text">
				  	<form:option value="Z1">Z1</form:option>
					<form:option value="A2">A2</form:option>
				 </form:select>
		      </div>
		  </div>
		   <button type="submit" class="btn btn-primary">Cancella Locazione</button>
		</form:form>
	</div>
</div>
</body>

</html>