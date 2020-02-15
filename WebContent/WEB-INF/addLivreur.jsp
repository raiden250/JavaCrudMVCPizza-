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
 		 <a style="margin-top:20px" class="btn btn-info" href="${pageContext.request.contextPath}/LivreurServlet?action=listlivreurs" role="button"><span class="glyphicon glyphicon-home"></span>Retour à la liste</a><br/>
	      <form method="post" action="livreurs" style="padding-top:40px" class="col-md-8" enctype="multipart/form-data">
			<fieldset>
				<legend>Formulaire Création Livreur</legend>	 

				<div class="form-group">
					<label>Nom du livreur<span class="requis"> *</span></label>
					<input type="text" class="form-control" name ="nom" id="nom" required />
				</div>
				
				<div class="form-group">
					<label>Prénom du livreur<span class="requis"> *</span></label>
					<input type="text" class="form-control" name ="prenom" id="prenom"  required />
				</div>
				
				<div class="form-group">
					<label>Age du livreur<span class="requis"> *</span></label>
					<input type="text" class="form-control" name ="age" id="age"  required />
				</div>

				<div class="form-group">
					<label>Inserer la photo<span class="requis"> *</span></label></label>
					<input class="form-control" type="file" name="photo" id="photo" required/>
				</div>
				<input type="text" name="actionpost" id="actionpost" value="addinlivrbase" style="display:none"/>
				
				<input type="submit" class="btn btn-primary" value="Ajouter le livreur" style="float:right"> 
		     </fieldset>
		  </form>
	</body>
</html>