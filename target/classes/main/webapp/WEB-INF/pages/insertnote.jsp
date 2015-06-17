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

<script type="text/javascript">

$(document).ready(function() {
    $('#elenco').dataTable();
} );

$(document).ready(function() {
	$("#fileDetails").prop("selectedIndex", -1);
	$("#programDetails").prop("selectedIndex", -1);
	$("#messageDetails").prop("selectedIndex", -1);
	$("#fieldDetails").prop("selectedIndex", -1);
	$("#menuOptionDetails").prop("selectedIndex", -1);
	$("#windowDetails").prop("selectedIndex", -1);
} );


$(document).ready(function() {
    $('#myForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	programDescription: {
                message: 'The programDescription is not valid',
                validators: {
                    notEmpty: {
                        message: 'The programDescription is required and cannot be empty'
                    },
                    stringLength: {
                        max: 30,
                        message: 'The programDescription must be more than 1 and less than 30 characters long'
                    }
                }
            },
            modificationNumber: {
	            message: 'The modificationNumber is not valid',
	            validators: {
	                notEmpty: {
	                    message: 'The modificationNumber is required and cannot be empty'
	                },
	                stringLength: {
	                    max: 4,
	                    message: 'The modificationNumber must be more than 1 and less than 4 characters long'
	                }
	            }
	        },
	        description: {
	            message: 'The description is not valid',
	            validators: {
	                stringLength: {
	                    max: 30,
	                    message: 'The description must be more than 1 and less than 4 characters long'
	                }
	            }
	        },
	        textDescription: {
	            message: 'The textDescription is not valid',
	            validators: {
	                stringLength: {
	                    max: 256,
	                    message: 'The textDescription must be more than 1 and less than 4 characters long'
	                }
	            }
	        },
	        release: {
	            message: 'The release version is not valid',
	            validators: {
	            	notEmpty: {
	                    message: 'The release version is required and cannot be empty'
	                },
	                stringLength: {
	                    max: 7,
	                    message: 'The release version must be more than 1 and less than 7 characters long'
	                }
	            }
	        },
	        status: {
	            message: 'The status is not valid',
	            validators: {
	                stringLength: {
	                    max: 1,
	                    message: 'The status must be 1 character long'
	                }
	            }
	        },
	        internalName: {
	            message: 'The internalName is not valid',
	            validators: {
	                stringLength: {
	                    max: 30,
	                    message: 'The internalName must be more than 1 and less than 30 characters long'
	                }
	            }
	        },
	        analyserName: {
	            message: 'The analyserName is not valid',
	            validators: {
	                stringLength: {
	                    max: 30,
	                    message: 'The analyserName must be more than 1 and less than 30 characters long'
	                }
	            }
	        },
	        testerName: {
	            message: 'The testerName is not valid',
	            validators: {
	                stringLength: {
	                    max: 30,
	                    message: 'The testerName must be more than 1 and less than 30 characters long'
	                }
	            }
	        },
	        testedOnDate: {
	            message: 'The testedOnDate is not valid',
	            validators: {
	                stringLength: {
	                    max: 8,
	                    message: 'The testedOnDate must be more than 1 and less than 8 characters long'
	                },
		            regexp: {
		                regexp: /^[0-9]+$/,
		                message: 'The testedOnDate can only consist of number'
		            }
	            }
	        },
	        promotedToLiveOnDate: {
	            message: 'The promotedToLiveOnDate is not valid',
	            validators: {
	                stringLength: {
	                    max: 8,
	                    message: 'The promotedToLiveOnDate must be more than 1 and less than 8 characters long'
	                },
		            regexp: {
		                regexp: /^[0-9]+$/,
		                message: 'The promotedToLiveOnDate can only consist of number'
		            }
	            }
	        },
	        textKey: {
	            message: 'The textKey is not valid',
	            validators: {
	                stringLength: {
	                    max: 15,
	                    message: 'The textKey must be more than 1 and less than 15 characters long'
	                },
		            regexp: {
		                regexp: /^[0-9]+$/,
		                message: 'The textKey can only consist of number'
		            }
	            }
	        }
	        
        }
    });
});
</script>

<style>
     body{
         background-image: url("/resources/images/pittogrammi-ecsa-BU.JPG");
     }
</style>

<body>


<div align="center">
			<div onclick="window.location='index';" class="jumbotron ">		
			</div>
		<h1 align="center" >Change Management Application</h1>
		<br>
</div>


<div class="container">
	<div align="center">
		<button  id="showButton"  class="btn btn-primary">Inserisci Nota</button>
	</div>
	<div class="hideForm">
		<h3  align="center">Inserisci Nota</h3>
		<form:form id="myForm" class="form-horizontal" method="POST" action="resultPage" modelAttribute="elementNote" >
		
		   <div class="form-group">
		      <label for="generator" class="col-sm-2 control-label">Generator</label>
		      <div class="col-sm-8">
		         <input name="generator" type="text" class="form-control" id="generator" 
		            value="${lastNote}" readonly="readonly" placeholder="${lastNote}" >
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">programDescription</label>
		      <div class="col-sm-8">
		         <input name="programDescription"   type="text" class="form-control" id="programDescription" 
		            placeholder="MHPGDE">
		      </div>
		   </div>
		   
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">modificationNumber</label>
		      <div class="col-sm-8">
		         <input name="modificationNumber" type="text" class="form-control" id="modificationNumber" 
		            placeholder="MHMONR">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">description</label>
		      <div class="col-sm-8">
		         <input name="description" type="text" class="form-control" id="description" 
		            placeholder="MHDESC">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">textDescription</label>
		      <div class="col-sm-8">
		         <input name="textDescription" type="text" class="form-control" id="textDescription" 
		            placeholder="MHT256">
		      </div>
		   </div>
		   
		   
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">release</label>
		      <div class="col-sm-8">
		         <input name="release" type="text" class="form-control" id="release" 
		            placeholder="MHRELL">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">fileDetails</label>
		      <div class="col-sm-8">
	         	<form:select path="fileDetails" class="form-control" id="fileDetails" name="fileDetails" type="text">
				  	<form:option value="Y">Y</form:option>
					<form:option value="N">N</form:option>
				 </form:select>
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">programDetails</label>
		      <div class="col-sm-8">
		      	<form:select path="programDetails" class="form-control" id="programDetails" name="programDetails" type="text">
				  	<form:option value="Y">Y</form:option>
					<form:option value="N">N</form:option>
				 </form:select>
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">messageDetails</label>
		      <div class="col-sm-8">
		      	<form:select path="messageDetails" class="form-control" id="messageDetails" name="messageDetails" type="text">
				  	<form:option value="Y">Y</form:option>
					<form:option value="N">N</form:option>
				</form:select>
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">fieldDetails</label>
		      <div class="col-sm-8">
		        <form:select path="fieldDetails" class="form-control" id="fieldDetails" name="fieldDetails" type="text">
				  	<form:option value="Y">Y</form:option>
					<form:option value="N">N</form:option>
				</form:select>
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">menuOptionDetails</label>
		      <div class="col-sm-8">
		      	<form:select path="menuOptionDetails" class="form-control" id="menuOptionDetails" name="menuOptionDetails" type="text">
				  	<form:option value="Y">Y</form:option>
					<form:option value="N">N</form:option>
				</form:select>
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">windowDetails</label>
		      <div class="col-sm-8">
		      <form:select path="windowDetails" class="form-control" id="windowDetails" name="windowDetails" type="text">
				  	<form:option value="Y">Y</form:option>
					<form:option value="N">N</form:option>
			  </form:select>
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">status</label>
		      <div class="col-sm-8">
		         <input name="status" type="text" class="form-control" id="status" 
		            placeholder="MHSTAT">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">internalName</label>
		      <div class="col-sm-8">
		         <input name="internalName" type="text" class="form-control" id="internalName" 
		            placeholder="MHPSNA">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">analyserName</label>
		      <div class="col-sm-8">
		         <input name="analyserName" type="text" class="form-control" id="analyserName" 
		            placeholder="MHANAL">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">testerName</label>
		      <div class="col-sm-8">
		         <input name="testerName" type="text" class="form-control" id="testerName" 
		            placeholder="MHTEST">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">testedOnDate</label>
		      <div class="col-sm-8">
		         <input name="testedOnDate" type="text" value="20140820" class="form-control" id="testedOnDate" 
		            placeholder="MHTDAT">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">promotedToLiveOnDate</label>
		      <div class="col-sm-8">
		         <input name="promotedToLiveOnDate" value="20140820" type="text" class="form-control" id="promotedToLiveOnDate" 
		            placeholder="MHPDAT">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">textKey</label>
		      <div class="col-sm-8">
		         <input name="textKey" value="0" type="text" class="form-control" id="textKey" 
		            placeholder="MHTXKY">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="description" class="col-sm-2 control-label">attachmentName</label>
		      <div class="col-sm-8">
		         <input name="attachmentName" type="text" class="form-control" id="attachmentName" 
		            placeholder="MHATNA">
		      </div>
		   </div>
		   <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-4">
		         <button type="submit" class="btn btn-success">Create Note</button>
		         <button type="reset" id="discardButton" class="btn btn-warning col-sm-offset-2">Discard Note</button>
		      </div>
		   </div>
		</form:form>
		
	</div>
	
	<br>
	<br>
	<br>
	<div class="container" >
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<fieldset>
					<legend align="center"><h3>Elenco Cambiamenti</h3></legend>
					    <table id="elenco" class="table table-striped table-condensed table-responsive">
					    		<thead>
							    <tr>
									<th></th>
									<!-- <th></th> -->
							      	<th>ID</th>
							      	<!-- <th>Program description</th> -->
							      	<th>Modification number</th>
							      	<th>Description</th>
									<th>Text description</th>
									<th>Release</th>
									<!-- <th>File details</th>
									<th>Program details</th>
									<th>Message details</th>
									<th>Field details</th>
									<th>Menu option details</th>
									<th>Window details</th> -->
									<!-- <th>Status</th> -->
									<th>Internal name</th>
									<!-- <th>Analyser name</th>
									<th>Tester name</th> -->
									<!-- <th>Tested on date</th> -->
									<th>Promoted to live on date</th>
<!-- 									<th>Text key</th>
									<th>Attachment name</th> -->
							    </tr>
							  </thead>
							  <c:forEach items="${data}" var="note">
							    <tr>
							      <td> <!-- Actions for the individual item -->
							     		<button onclick="deleteConfirmation(${note.generator})" href="<c:url value="/remove?idToDelete=${note.generator}"/>" type="submit" class="btn btn-danger">Delete</button>
	        					  </td>
	        					  
	        					   <!--  <td><button onclick="goToShowPage(${note.generator})" href="<c:url value="/noteModify?idToModify=${note.generator}"/>"  class="btn btn-warning">Show</button></td>-->
							      
							      <%-- <td><a></a><c:out value="${note.generator}" /></td> --%>
							      <td><a href="<c:url value="/noteModify?idToModify=${note.generator}"/>"><c:out value="${note.generator}" /></td></a>
							     <%--  <td><c:out value="${note.programDescription}" /></td> --%>
							      <td><a href="<c:url value="/noteModify?idToModify=${note.generator}"/>"><c:out value="${note.modificationNumber}" /></td></a>
							      <td><a href="<c:url value="/noteModify?idToModify=${note.generator}"/>"><c:out value="${note.description}" /></td></a>
							      <td><a href="<c:url value="/noteModify?idToModify=${note.generator}"/>"><c:out value="${note.textDescription}" /></td></a>
							      <td><a href="<c:url value="/noteModify?idToModify=${note.generator}"/>"><c:out value="${note.release}" /></td></a>
							      <%-- <td><c:out value="${note.fileDetails}" /></td>
							      <td><c:out value="${note.programDetails}" /></td>
							      <td><c:out value="${note.messageDetails}" /></td>
							      <td><c:out value="${note.fieldDetails}" /></td>
							      <td><c:out value="${note.menuOptionDetails}" /></td>
							      <td><c:out value="${note.windowDetails}" /></td> --%>
							     <%--  <td><c:out value="${note.status}" /></td> --%>
							      <td><a href="<c:url value="/noteModify?idToModify=${note.generator}"/>"><c:out value="${note.internalName}" /></td></a>
							      <%-- <td><c:out value="${note.analyserName}" /></td>
							      <td><c:out value="${note.testerName}" /></td> --%>
							    <%--   <td><c:out value="${note.testedOnDate}" /></td> --%>
							      <td><a href="<c:url value="/noteModify?idToModify=${note.generator}"/>"><c:out value="${note.promotedToLiveOnDate}" /></td></a> 
<%-- 							      <td><c:out value="${note.textKey}" /></td> 
							     <td><c:out value="${note.attachmentName}" /></td>   --%>    
							    </tr>
						  	</c:forEach>   		
	    				</table>
					</fieldset>
				</div>
			</div>
			<div class="col-md-12 text-center">
	      <ul class="pagination pagination-lg pager" id="elenco"></ul>
		</div>		
	</div>	
</div>

</body>
</html>