<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<html>
<body>
	<h1>Subida de ficheros</h1>
	<form method="POST" enctype="multipart/form-data"
		action="subir">
		Fichero subida 1: <input type="file" name="file1"><br />  
		Fichero subida 2:<input type="file" name="file2"><br />
		Fichero subida 3: <input type="file" name="file3"><br />
		Fichero subida 4: <input type="file" name="file4"><br />
		
		 <input type="submit"
			value="Upload"> subir cuatro ficheros e invocar al servicio
	</form>


	

	
</body>
</html>