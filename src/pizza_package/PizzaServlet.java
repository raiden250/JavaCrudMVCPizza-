package pizza_package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/PizzaServlet")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	ArrayList<Pizza> lesPizzas = new ArrayList();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("listpizza")) 
			{
				try {
					initListPizzas(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		if (action.equalsIgnoreCase("deletepizza")){
			try {
				deletepizza(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   		
		}
		
		if (action.equalsIgnoreCase("editOnePizza")){
			editOnePizza(request, response);   		
		}
		if (action.equalsIgnoreCase("addPizza")){
			addPizza(request, response);
		}
		
		
	}
	
	private void initListPizzas(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		 
		try{
			lesPizzas = PizzaDAO.getAllPizzas();
		    request.setAttribute("PIZZAS", lesPizzas);	

		    this.getServletContext().getRequestDispatcher( "/WEB-INF/pizzas.jsp" ).forward( request, response );
		}catch(Exception e) {
		    request.setAttribute("resultat","erreur: "+  e.getMessage());		 
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation.jsp" ).forward( request, response );
		}		
	}
		
	private void editOnePizza(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 		
		try{
			int id_pizza =  Integer.parseInt(request.getParameter("idpizza"));
			Pizza pizza = PizzaDAO.getPizzaById(id_pizza);
			
			request.setAttribute("ThePizza", pizza);
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/singlePizza.jsp" ).forward( request, response );
		}catch(Exception e) {
		    request.setAttribute("resultat","erreur: "+ e.getMessage());		 
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation.jsp" ).forward( request, response );
		}
	}

	private void editOnePizzaPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int id_pizza =  Integer.parseInt(request.getParameter("idpizza"));
	    double tarif = Double.parseDouble(request.getParameter("prixPizza"));
	    String nom = request.getParameter("nomPizza");
	    String chemin = this.getServletContext().getRealPath("/images/pizzas/");
	    
	    String result = PizzaDAO.updatePizza(id_pizza, nom, tarif)	;	
		
		try {
		    
		    result  = PizzaDAO.updatePizza(id_pizza, nom, tarif);
		    
		    Part photo = request.getPart("photoPizza");
		    
		    String nomFile = Image.getImageName(photo) ;
		    
		    if(result.equals("ok") && nomFile != null && !nomFile.isEmpty())
		    {	  
		    	Image.Delete(chemin+nomFile);

		    	nomFile = id_pizza + ".jpg";		    	
		    	Image.Save(photo, nomFile, chemin);
		    }
			
		    request.setAttribute("resultat","c'est fait");		 

		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation.jsp").forward( request, response );
		    
		}catch(Exception e) {
		    request.setAttribute("resultat","erreur: " + result);		 
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation.jsp" ).forward( request, response );
		}
	}

	private void deletepizza(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, SQLException {
	    int id_pizza =  Integer.parseInt(request.getParameter("idpizza"));
		String result = PizzaDAO.deletePizza(id_pizza);
		
		if(result.equals("ok")){
			Image.Delete(this.getServletContext().getRealPath("/images/pizzas/") +id_pizza+".jpg");
		    request.setAttribute("resultat","Pizza supprimée");		 
		}
		    
		else{
		    request.setAttribute("resultat", "erreur: "+  result);		 
		}
		
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation.jsp" ).forward( request, response );

	}
	
	private void addPizzaInBase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String nom = request.getParameter("nomPizza");
	    double tarif = Double.parseDouble(request.getParameter("prixPizza"));
	    
	    String chemin = this.getServletContext().getRealPath("/images/pizzas/");
   
		int result = PizzaDAO.addPizza(nom, tarif);
				
		if(result != 0){
		    request.setAttribute("resultat","Pizza ajouté");		 
		    
		    Part photo = request.getPart("photoPizza");
		    
		    String nomFile = Image.getImageName(photo) ;
		    
		    if(nomFile != null && !nomFile.isEmpty()) {
		    	nomFile = String.valueOf(result) + ".jpg";
		    	
		    	Image.Save(photo, nomFile, chemin);
		    }
		    
		}
		    
		else{
		    request.setAttribute("resultat", "erreur: "+  "une erreur s'est produite");		 
		}
		
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation.jsp" ).forward( request, response );

	}

	private void addPizza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/addPizza.jsp" ).forward( request, response );

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("actionpost");
		
		if(action.equals("editinpizzabase"))		
		    editOnePizzaPost( request, response );
		if(action.equals("addinpizzabase"))
			addPizzaInBase( request, response );	

	}

}
