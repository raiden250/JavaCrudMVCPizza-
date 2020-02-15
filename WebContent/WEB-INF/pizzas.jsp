<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Pizza" %>
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

        .imgPizza{
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
    <h3 style="text-align:center; color:red; text-decoration:underline; font-weight:bold; margin-bottom: 40px"> Carte des pizzas de chez Milou </h3>
    <a class="btn btn-info" href="${pageContext.request.contextPath}/PizzaServlet?action=addPizza" role="button">Ajouter une pizza</a>
	<% ArrayList<Pizza> MyPizzas = (ArrayList<Pizza>)request.getAttribute("PIZZAS"); %>
       <div class="container">
          <a> ${PATH}</a>
		    <%
			   for(Pizza pizza : MyPizzas)
			    {%>
			       <div class="col-md-4 col-sm-6 imgPizza">
		             <img class="zoom" src="images/pizzas/<%= pizza.getId() %>.jpg">           
		             <div class="description">
		                <h2><b><%= pizza.getId() + "  "+ pizza.getNom() %></b></h2>
                        <p>Prix : <%= pizza.getPrix() %> euros</p>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/PizzaServlet?action=editOnePizza&idpizza=<%=pizza.getId()%>" role="button">Editer</a>
	                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/PizzaServlet?action=deletepizza&idpizza=<%=pizza.getId()%>" role="button">Supprimer</a>                          
	                                        
		             </div>
		           </div>
			    <% }%>
		 </div>	 
 </body>
</html>