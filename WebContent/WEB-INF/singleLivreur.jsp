<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Livreur" %>
<html>
 <head>
	  <title>Bootstrap Liste Pizza</title>
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	
	  <style type="text/css">
        .thumbnail{
            height: 380px;
            margin-bottom: 50px;
        }

        .button {
            background-color:gray ;
            color: white;
        }

        .button:hover {
          background-color: darkgray; 
        }

        .zoom{
         -webkit-transform: scale(1);
         transform: scale(1);
         -webkit-transition: .3s ease-in-out;
         transition: .3s ease-in-out;
        }
        .zoom:hover{
        -webkit-transform: scale(1.1);
        transform: scale(1.1);
        }

  </style>


</head>
<%@ include file="header.jsp" %>
<body>
    <div>
       <a style="margin-top:20px" class="btn btn-info" href="${pageContext.request.contextPath}/LivreurServlet?action=listlivreurs" role="button">Retour à la liste</a>
       <h3> ${ TheLivreur.getPrenom()}  ${TheLivreur.getNom().toUpperCase() } </h3>
    </div>
	<div class="row">	
        <div class="col-md-6 col-lg-6">
	      <div class="thumbnail" >
	          <img class="zoom" src="images/livreurs/${TheLivreur.getId()}.jpg">           
	           <div style="text-align: center;">
                    <p> ${TheLivreur.getAge()} ans</p>
                    <p>Date d'entrée: ${TheLivreur.getdateEntree() }</p>
                    <a class="btn btn btn-danger" href="${pageContext.request.contextPath}/LivreurServlet?action=deletelivreur&idlivreur=${TheLivreur.getId()}" role="button">Supprimer</a>                          
               </div>          	    
	      </div>
	     <p style="position: relative; top: -20px"></p>
	    </div>	 
	    <div class="col-md-6 col-lg-6">
		    <form method="post" action="livreurs" style="padding-top:40px" class="col-md-8" enctype="multipart/form-data">
				<fieldset>
					<legend>Formulaire modification </legend>	 
	
					<div class="form-group">
						<label>Nom du livreur<span class="requis"> *</span></label>
						<input type="text" class="form-control" name ="nom" id="nom" value=" ${ TheLivreur.getNom()}" required />
					</div>
					
					<div class="form-group">
						<label>Prénom du livreur<span class="requis"> *</span></label>
						<input type="text" class="form-control" name ="prenom" id="prenom" value="${ TheLivreur.getPrenom()}" required />
					</div>
					
					<div class="form-group">
						<label>Age du livreur<span class="requis"> *</span></label>
						<input type="text" class="form-control" name ="age" id="age" value="${TheLivreur.getAge()}" required />
					</div>
	
					<div class="form-group">
						<label>Inserer la photo<span class="requis"> *</span></label></label>
						<input class="form-control" type="file" name="photo" id="photo" />
					</div>
					<input type="text" name="actionpost" id="actionpost" value="editinlivrbase" style="display:none"/>
					<input type="text" name="idlivreur" id="idlivreur" value="${TheLivreur.getId()}" style="display:none"/>
					<input type="submit" class="btn btn-primary" value="Modifier le livreur" style="float:right"> 
			     </fieldset>
			  </form>
		</div>
	 </div>
</body>
</html> 