package pizza_package;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LivreurDAO {

	
	public static ArrayList<Livreur> getAllLivreurs() throws ClassNotFoundException, SQLException{
		
		Statement st = (Statement) ConnBase.connectSQL();
		ResultSet rs = st.executeQuery("Call getLivreurs()");
	    ArrayList<Livreur> livreurs = new ArrayList();

		
		while(rs.next())
		{
			  int id = rs.getInt("NroLivr");
			  String nom = rs.getString("NomLivr");
			  String pnom = rs.getString("PrenomLivr");
			  int age = rs.getInt("AgeLivr");
			  String date = rs.getString("DatEmbauchLivr");
		  
		      livreurs.add(new Livreur(id, nom, pnom, age, date));
		}
		
		st.close();
		return livreurs;
	}
	
	static public String deleteLivreur(int idLivreur) throws SQLException, ClassNotFoundException {

		try{
			Statement st = (Statement) ConnBase.connectSQL();
	     	st.executeUpdate("DELETE FROM livreur WHERE NroLivr= " + idLivreur);             
            return "ok";
		}catch(Exception e) {
			return e.getMessage();
		}
    }
	
	static public Livreur getLivreurByName(String name) throws SQLException, ClassNotFoundException {
		Statement st = (Statement) ConnBase.connectSQL();
		ResultSet res = st.executeQuery("select * from livreur where NomLivr = '" + name + "'");       
	    res.next();
	    Livreur livreur = new Livreur(res.getInt("NroLivr"),res.getString("NomLivr"),res.getString("PrenomLivr"),
                res.getInt("AgeLivr"),res.getString("DatEmbauchLivr"));      
	    
        return livreur;
    }
	
	static public Livreur getLivreurById(int idlivreur) throws SQLException, ClassNotFoundException {
		Statement st = (Statement) ConnBase.connectSQL();
		ResultSet res = st.executeQuery("select * from livreur where NroLivr = " + idlivreur + " ");       
	    res.next();
        Livreur livreur = new Livreur(res.getInt("NroLivr"),res.getString("NomLivr"),res.getString("PrenomLivr"),
        		                      res.getInt("AgeLivr"),res.getString("DatEmbauchLivr"));          
      
        return livreur;
    }
	
	static public int addLivreur(String nom, String pnom, int age) {
		try {

			String req = "Call createLivreur('" + nom + "','" + pnom + "'," + age +  ")";
			Statement st = ConnBase.connectSQL();
			if(st.executeUpdate(req) != 0) {
				Livreur l= getLivreurByName(nom) ;
				return l.getId();
			}
			
			else {
				return 0;
			}
			
		}catch(Exception e) {
			return 0;
		}		
	}
	
	static public String updateLivreur(int id, String nom, String pnom, int age) {
		Statement st ;
		
		try {

			String req = "UPDATE livreur SET NomLivr ='" + nom + "', PrenomLivr ='" + pnom +
					     "', AgeLivr =" + age +" WHERE NroLivr = " + id;
			st = ConnBase.connectSQL();
		    st.executeUpdate(req) ;
			
		    return "ok";
			
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
