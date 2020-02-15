<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Livreur" %>
<html>
<head>
    <title>Liste des pizzas</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <style>

    * { 
        box-sizing: border-box;
    }

        img{
            width:100%;
            padding: 0%;
            height: 230px;
            opacity: 1;
	        -webkit-transition: .3s ease-in-out;
	        transition: .3s ease-in-out;
        }

        img:hover {
	       -- opacity: .5;
        }

        div{
            overflow: auto;
        }

        .imgLivreur{
            margin-bottom:2%;
            padding-bottom: 1%;
        }

        .btn-info{
            background-color: black;
            border: 1px solid black;
        }

        .btn-info:hover{
            background-color: rgb(128, 128, 128);
            border: 1px solid black;
        }
        .description{
            padding-bottom: 2%; 
            border-bottom: 3px solid black;
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
    <h3 style="text-align:center; color:red; text-decoration:underline; font-weight:bold; margin-bottom: 40px"> Liste des livreurs de chez Milou </h3>
    <a class="btn btn-info" href="${pageContext.request.contextPath}/LivreurServlet?action=addLivreur" role="button">Ajouter un livreur</a>
	<% 
	   ArrayList<Livreur> MyLivreurs = (ArrayList<Livreur>)request.getAttribute("LIVREURS");
    %>

     <div class="container">
	    <%
		   for(Livreur livreur : MyLivreurs)
		    {%>
		       <div class="col-md-4 col-sm-6 imgLivreur">
	             <img class="zoom" src="images/livreurs/<%= livreur.getId() %>.jpg">           
	             <div class="description">
	                <h3 style="color:blue"><b><%= livreur.getPrenom() + "  "+ livreur.getNom().toUpperCase() %></b></h3>
	                      <p><%= livreur.getAge() %> ans</p>
	                      <p>Date d'entrée: <%=livreur.getdateEntree() %></p>
	                      <a class="btn btn-primary" href="${pageContext.request.contextPath}/LivreurServlet?action=editOneLivreur&idlivreur=<%=livreur.getId()%>" role="button">Editer</a>
	                   <a class="btn btn-danger" href="${pageContext.request.contextPath}/LivreurServlet?action=deletelivreur&idlivreur=<%=livreur.getId()%>" role="button">Supprimer</a>                        	                                       
	             </div>
	           </div>
		    <% }%>
 </div>	 
 </body>
</html>