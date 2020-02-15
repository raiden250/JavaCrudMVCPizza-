package pizza_package;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnBase {
	
	
	public static Statement connectSQL() throws ClassNotFoundException {
		
	     String url = "jdbc:mysql://localhost:3306/base_pizza";
		 Connection cn ;	
		 Statement st ;
		 
		 try {	
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url,"root","");
			st =  cn.createStatement();
					
		 } catch (SQLException e) {
			e.printStackTrace();
			st=null;
		} 
			    
		return st;
  }
	
}
