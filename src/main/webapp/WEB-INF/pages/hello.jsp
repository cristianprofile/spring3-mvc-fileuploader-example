<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bootstrap Form With Spring Mvc Example</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
				<legend>Subida</legend>
				<form class="form-horizontal" method="POST" enctype="multipart/form-data" action='<c:url value="/subir" />'>
					<div class="control-group">
						<label class="control-label">Primer fichero</label>
						<div class="controls">
							 <input type="file" name="file1">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Segundo fichero</label>
						<div class="controls">
							 <input type="file" name="file2" >
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Tercer fichero</label>
						<div class="controls">
							 <input type="file" name="file3" >
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Cuarto fichero</label>
						<div class="controls">
							 <input type="file" name="file4" >
						</div>
					</div>
					<br>
					<div class="form-actions">
						<button type="submit" class="btn btn-success" value="Upload">Submit</button>
					</div>
				</form>
				</fieldset>
			</div>
		</div>
	</div>	
	
	
		
</body>
</html>






