<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<title>Ajout pizza</title>
	<meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	  <style>
	     .requis{
	     color:red;
	     }
	  </style>
	</head>
	<%@ include file="header.jsp" %>
	<body>
 		  <a style="margin-top:20px" class="btn btn-info" href="${pageContext.request.contextPath}/PizzaServlet?action=listpizza" role="button"><span class="glyphicon glyphicon-home"></span> Retour à la liste</a><br/>
	      <form method="post" action="pizzas" style="padding-top:40px" class="col-md-8" enctype="multipart/form-data">
			<fieldset>
				<legend>Formulaire Création Pizza</legend>	 

				<div class="form-group">
					<label>Nom de la pizza<span class="requis"> *</span></label>
					<input type="text" class="form-control" name ="nomPizza" id="nomPizza" required />
				</div>
				
				<div class="form-group">
					<label>Prix de la pizza<span class="requis"> *</span></label>
					<input type="text" class="form-control" name ="prixPizza" id="prixPizza"  required />
				</div>

				<div class="form-group">
					<label>Inserer la photo<span class="requis"> *</span></label></label>
					<input class="form-control" type="file" name="photoPizza" id="photoPizza" required/>
				</div>
				<input type="text" name="actionpost" id="actionpost" value="addinpizzabase" style="display:none"/>
				
				<input type="submit" class="btn btn-primary" value="Ajouter la pizza" style="float:right"> 
		     </fieldset>
		  </form>
	</body>
</html>