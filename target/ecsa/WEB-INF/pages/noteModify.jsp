<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.io.File"%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>    
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script src="<c:url value="/resources/js/jasny-bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/jasny-bootstrap.min.css" />" rel="stylesheet">
<!-- Validator include -->
<script src="<c:url value="/resources/js/bootstrapValidator.js" />"></script>
<script src="<c:url value="/resources/js/it_IT.js" />"></script>
<link href="<c:url value="/resources/css/bootstrapValidator.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


 <script>

$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length - 1;
        $('#fileTable').append(
                '<tr><td>'+
                '   <input type="file" name="files['+ fileIndex +']" />'+
                '</td></tr>');
    });
     
});
 

$(function() {
    var fileDetails="${note.fileDetails}"; 
    $("#fileDetails").val(fileDetails);
    
    var programDetails="${note.programDetails}"; 
    $("#programDetails").val(programDetails);
    
    var messageDetails="${note.messageDetails}"; 
    $("#messageDetails").val(messageDetails);
    
    var windowDetails="${note.windowDetails}"; 
    $("#windowDetails").val(windowDetails);
    
    var menuOptionDetails="${note.menuOptionDetails}"; 
    $("#menuOptionDetails").val(menuOptionDetails);
    
    var fieldDetails="${note.fieldDetails}"; 
    $("#fieldDetails").val(fieldDetails);
    
});

$(document).ready(function() {
    $('#myFormModify').bootstrapValidator({
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
 							     




<div class="container">

		<div  align="center">
			<div class="jumbotron " onclick="window.location='index';"></div>
			<h1 align="center" >Modify note n° ${note.generator}</h1>
			<br>
		</div>
	<div class="col-sm-2" align="right"><button  type="submit" name="op" onclick="window.location.href='/ecsa/notemanagement'" class="btn btn-primary">Back</button></div>
	
	<br></br>
	<form:form id="myFormModify" class="form-horizontal" method="POST" action="noteModify/test" modelAttribute="elementNote" >
			
			   <div class="form-group">
			      <label for="generator" class="col-sm-2 control-label">Generator</label>
			      <div class="col-sm-8">
			         <input name="generator" type="text" class="form-control" id="generator" 
			            value="${note.generator}" readonly="readonly"  >
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">programDescription</label>
			      <div class="col-sm-8">
			         <input value="${note.programDescription}" name="programDescription"   type="text" class="form-control" id="programDescription" 
			            placeholder="MHPGDE">
			      </div>
			   </div>
			   
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">modificationNumber</label>
			      <div class="col-sm-8">
			         <input value="${note.modificationNumber}" name="modificationNumber" type="text" class="form-control" id="modificationNumber" 
			            placeholder="MHMONR">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">description</label>
			      <div class="col-sm-8">
			         <input value="${note.description}" name="description" type="text" class="form-control" id="description" 
			            placeholder="MHDESC">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">textDescription</label>
			      <div class="col-sm-8">
			         <input value="${note.textDescription}" name="textDescription" type="text" class="form-control" id="textDescription" 
			            placeholder="MHT256">
			      </div>
			   </div>
			   
			   
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">release</label>
			      <div class="col-sm-8">
			         <input value="${note.release}" name="release" type="text" class="form-control" id="release" 
			            placeholder="MHRELL">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">fileDetails</label>
			      <div id="fileDetailss" class="col-sm-8">
				      <form:select path="fileDetails" value="${note.fileDetails}" class="form-control" id="fileDetails" name="fileDetails" type="text">
					  	<form:option value="Y">Y</form:option>
						<form:option value="N">N</form:option>
					 </form:select>
				 </div>
<%-- 			      <div class="col-sm-8">
			         <input value="${note.fileDetails}" name="fileDetails" type="text" class="form-control" id="fileDetails" 
			            placeholder="MHFILD">
			      </div> --%>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">programDetails</label>
			      <div class="col-sm-8">
			      	<form:select path="programDetails" value="${note.programDetails}" class="form-control" id="programDetails" name="programDetails" type="text">
					  	<form:option value="Y">Y</form:option>
						<form:option value="N">N</form:option>
					 </form:select>
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">messageDetails</label>
			      <div class="col-sm-8">
			      	<form:select path="messageDetails" value="${note.messageDetails}" class="form-control" id="messageDetails" name="messageDetails" type="text">
					  	<form:option value="Y">Y</form:option>
						<form:option value="N">N</form:option>
					</form:select>
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">fieldDetails</label>
			      <div class="col-sm-8">
			        <form:select path="fieldDetails" value="${note.fieldDetails}" class="form-control" id="fieldDetails" name="fieldDetails" type="text">
					  	<form:option value="Y">Y</form:option>
						<form:option value="N">N</form:option>
					</form:select>
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">menuOptionDetails</label>
			      <div class="col-sm-8">
			      	<form:select path="menuOptionDetails" value="${note.menuOptionDetails}" class="form-control" id="menuOptionDetails" name="menuOptionDetails" type="text">
					  	<form:option value="Y">Y</form:option>
						<form:option value="N">N</form:option>
					</form:select>
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">windowDetails</label>
			      <div class="col-sm-8">
			      <form:select path="windowDetails" value="${note.windowDetails}" class="form-control" id="windowDetails" name="windowDetails" type="text">
					  	<form:option value="Y">Y</form:option>
						<form:option value="N">N</form:option>
				  </form:select>
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">status</label>
			      <div class="col-sm-8">
			         <input value="${note.status}" name="status" type="text" class="form-control" id="status" 
			            placeholder="MHSTAT">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">internalName</label>
			      <div class="col-sm-8">
			         <input value="${note.internalName}" name="internalName" type="text" class="form-control" id="internalName" 
			            placeholder="MHPSNA">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">analyserName</label>
			      <div class="col-sm-8">
			         <input value="${note.analyserName}" name="analyserName" type="text" class="form-control" id="analyserName" 
			            placeholder="MHANAL">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">testerName</label>
			      <div class="col-sm-8">
			         <input value="${note.testerName}" name="testerName" type="text" class="form-control" id="testerName" 
			            placeholder="MHTEST">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">testedOnDate</label>
			      <div class="col-sm-8">
			         <input name="testedOnDate" type="text" value="${note.testedOnDate}"  class="form-control" id="testedOnDate" 
			            placeholder="MHTDAT">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">promotedToLiveOnDate</label>
			      <div class="col-sm-8">
			         <input name="promotedToLiveOnDate" value="${note.promotedToLiveOnDate}" type="text" class="form-control" id="promotedToLiveOnDate" 
			            placeholder="MHPDAT">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">textKey</label>
			      <div class="col-sm-8">
			         <input name="textKey" value="${note.textKey}" type="text" class="form-control" id="textKey" 
			            placeholder="MHTXKY">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="description" class="col-sm-2 control-label">attachmentName</label>
			      <div class="col-sm-8">
			         <input name="attachmentName"  value="${note.attachmentName}" type="text" class="form-control" id="attachmentName" 
			            placeholder="MHATNA">
			      </div>
			   </div>
			   <div class="form-group">
			      <div class="col-sm-offset-2 col-sm-4">
			         <button type="submit" class="btn btn-success">Modify Note</button>
			      </div>
			   </div>
			</form:form>


			<br>
			<br>
			<br>
			
			<form:form method="post" action="noteModify/upload?idToUpload=${note.generator}"
			        modelAttribute="uploadForm" enctype="multipart/form-data">
			 
			    <h3><p>Select files to upload</p></h3>
			    <!-- input id="addFile" type="button" class="btn btn-primary" value="Add File" /> -->
			    <table id="fileTable">
			        <tr>
			            <td><input name="files[0]" type="file" class="btn btn-file" /></td>
			        </tr>
			    </table>
			    <br/><button type="submit" class="btn btn-success">Upload</button>
			</form:form>
			
			<h2 align="center">Download Attachments</h2>
			
			
 			<c:forEach items="${fileList}" var="fileToShow">
				<form:form method="POST"
					action="noteModify/download?idToModify=${note.generator}" modelAttribute="fileToUpload">
				
					 <input  class="form-control"  type="submit"
					value="${fileToShow.fileName}"  name="fileToUpload" id="fileToUpload">
				</form:form>
			
			</c:forEach> 
			
			
			<br>
			<br>
			<br>
			<br>
			
			
	</div>
</body>
</html>