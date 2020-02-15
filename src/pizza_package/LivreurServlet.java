package pizza_package;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class LivreurServlet
 */
@WebServlet("/LivreurServlet")
public class LivreurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	ArrayList<Livreur> lesLivreurs = new ArrayList();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivreurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
				
		if (action.equalsIgnoreCase("listlivreurs")) 
			{
				try {
					initListLivreurs(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		if (action.equalsIgnoreCase("editOneLivreur")){
			editOneLivreur(request, response);
		}
		
		if (action.equalsIgnoreCase("deletelivreur")){
			try {
				deletelivreur(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   		
		}
		
		if (action.equalsIgnoreCase("addLivreur")){
			addLivreur(request, response);
		}
	}
	
	private void editOneLivreur(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 
		try{
			int id_livreur =  Integer.parseInt(request.getParameter("idlivreur"));
			Livreur livreur = LivreurDAO.getLivreurById(id_livreur);
			
			request.setAttribute("TheLivreur", livreur);
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/singleLivreur.jsp" ).forward( request, response );
		}catch(Exception e) {
		    request.setAttribute("resultat","erreur: "+ e.getMessage());		 
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation2.jsp" ).forward( request, response );
		}
	}
		
	private void initListLivreurs(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		 
		try{
			lesLivreurs = LivreurDAO.getAllLivreurs();
		    request.setAttribute("LIVREURS", lesLivreurs);		 
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/livreurs.jsp" ).forward( request, response );
		}catch(Exception e) {
		    request.setAttribute("resultat","erreur: "+  e.getMessage());		 
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation2.jsp" ).forward( request, response );
		}		
	}
	
	private void deletelivreur(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, SQLException {
	    int id_livreur =  Integer.parseInt(request.getParameter("idlivreur"));
		String result = LivreurDAO.deleteLivreur(id_livreur);
		
		if(result.equals("ok")){
			Image.Delete(this.getServletContext().getRealPath("/images/livreurs/") +id_livreur+".jpg");
		    request.setAttribute("resultat","Livreur supprimée");		 
		}
		    
		else{
		    request.setAttribute("resultat", "erreur: "+  result);		 
		}
		
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation2.jsp" ).forward( request, response );

	}

	private void addLivreur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/addLivreur.jsp" ).forward( request, response );

	}
	
	private void editOneLIvreurPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int id_livreur =  Integer.parseInt(request.getParameter("idlivreur"));
	    int age = Integer.parseInt(request.getParameter("age"));
	    String nom = request.getParameter("nom");
	    String pnom = request.getParameter("prenom");

	    String chemin = this.getServletContext().getRealPath("/images/livreurs/");
	    
	    String result = LivreurDAO.updateLivreur(id_livreur, nom, pnom, age);	
		
		try {
		    		    
		    Part photo = request.getPart("photo");
		    
		    String nomFile = Image.getImageName(photo) ;
		    
		    if(result.equals("ok") && nomFile != null && !nomFile.isEmpty())
		    {	  
		    	Image.Delete(chemin+nomFile);

		    	nomFile = id_livreur + ".jpg";		    	
		    	Image.Save(photo, nomFile, chemin);
		    }
			
		    request.setAttribute("resultat","c'est fait");		 

		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation2.jsp").forward( request, response );
		    
		}catch(Exception e) {
		    request.setAttribute("resultat","erreur: " + result);		 
		    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation2.jsp" ).forward( request, response );
		}
	}

	
    private void addLivreurInBase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String nom = request.getParameter("nom");
	    String pnom =  request.getParameter("prenom");
	    int age  = Integer.parseInt(request.getParameter("age"));
	    
	    String chemin = this.getServletContext().getRealPath("/images/livreurs/");
   
		int result = LivreurDAO.addLivreur(nom, pnom, age);
				
		if(result != 0){
		    request.setAttribute("resultat","Livreur ajouté");		 
		    
		    Part photo = request.getPart("photo");
		    
		    String nomFile = Image.getImageName(photo) ;
		    
		    if(nomFile != null && !nomFile.isEmpty()) {
		    	nomFile = String.valueOf(result) + ".jpg";
		    	
		    	Image.Save(photo, nomFile, chemin);
		    }
		    
		}
		    
		else{
		    request.setAttribute("resultat", "erreur: "+  "une erreur s'est produite");		 
		}
		
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/result_operation2.jsp" ).forward( request, response );
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("actionpost");
				
		if(action.equals("editinlivrbase"))		
		    editOneLIvreurPost( request, response );
		if(action.equals("addinlivrbase"))
		    addLivreurInBase( request, response );	   			
	}

}
