package pizza_package;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PizzaDAO {
	
	public static ArrayList<Pizza> getAllPizzas() throws ClassNotFoundException, SQLException{
		
		Statement st = (Statement) ConnBase.connectSQL();
		ResultSet rs = st.executeQuery("select * from pizzas");
	    ArrayList<Pizza> pizzas = new ArrayList();
		
		while(rs.next())
		{
			  int id = rs.getInt("id");
			  String nom = rs.getString("DesignPizz");
			  double prix = rs.getDouble("TarifPizz");
		  
		      pizzas.add(new Pizza(id, nom, prix));
		}
		
		st.close();
		return pizzas;
	}
	
	static public Pizza getPizzaById(int idPizza) throws SQLException, ClassNotFoundException {
		Statement st = (Statement) ConnBase.connectSQL();
		ResultSet res = st.executeQuery("select * from pizzas where id = " + idPizza + " ");       
	    res.next();
        Pizza pizza = new Pizza(res.getInt("id"),res.getString("DesignPizz"),res.getDouble("TarifPizz"));          
      
        return pizza;
    }

	static public Pizza getPizzaByName(String name) throws SQLException, ClassNotFoundException {
		Statement st = (Statement) ConnBase.connectSQL();
		ResultSet res = st.executeQuery("select * from pizzas where DesignPizz = '" + name + "'");       
	    res.next();
        Pizza pizza = new Pizza(res.getInt("id"),res.getString("DesignPizz"),res.getDouble("TarifPizz"));          
      
        return pizza;
    }
	
	static public String deletePizza(int idPizza) throws SQLException, ClassNotFoundException {
		try{
			Statement st = (Statement) ConnBase.connectSQL();
	     	st.executeUpdate("DELETE FROM pizzas WHERE id= " + idPizza);             
            return "ok";
		}catch(Exception e) {
			return e.getMessage();
		}
    }
	
	static public int addPizza(String nomPizza, double prixPizza) {
		try {

			String req = "INSERT INTO pizzas (DesignPizz, TarifPizz) VALUES ('" + nomPizza + "'," + prixPizza + ")";
			Statement st = ConnBase.connectSQL();
			if(st.executeUpdate(req) != 0) {
				Pizza p= getPizzaByName(nomPizza) ;
				return p.getId();
			}
			
			else {
				return 0;
			}
			
		}catch(Exception e) {
			return 0;
		}		
	}

	
	static public String updatePizza(int idpizza, String nomPizza, double prixPizza) {
		Statement st ;
		
		try {

			String req = "UPDATE pizzas SET DesignPizz ='" + nomPizza + "', TarifPizz =" + prixPizza +" WHERE id = " + idpizza;
			st = ConnBase.connectSQL();
		    st.executeUpdate(req) ;
			
		    return "ok";
			
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
