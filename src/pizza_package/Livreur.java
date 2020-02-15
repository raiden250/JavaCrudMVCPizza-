package pizza_package;

import java.util.ArrayList;

import javax.swing.Icon;

public class Livreur {
	private int id;
	private String nom;
	private String prenom;
	private String dateEntree;
	private int age;
	
	
	public Livreur (int id, String n, String p, int age, String date){
		this.nom=n; this.prenom=p; this.id=id; 
		this.age = age; this.dateEntree=date;
	}
	
	public Livreur (String n, String p, int a){
		this.nom=n; this.prenom=p; this.age=a; 
	}
	
	public String getNom() {
		return nom;
	}
	
    public String getPrenom() {
		return this.prenom;
	}
    
    public int getId() {
		return id;
	}

    public int getAge() {
    	return this.age;
    			}
   
    public String getdateEntree() {
    	return this.dateEntree;
    }
   
    /**********************************************setteurs****************************************************/
    
    public void setNom(String nom) {
		this.nom = nom;
	}
	
    public void setAge(int age) {
		this.age = age;
	}
	
    public void setDateEntre(String date) {
		this.dateEntree = date;
	}

	public void setPrenom(String pnom) {
		this.prenom = nom;
	}
	
	public void setd(int id) {
		this.id = id;
	}
	
}
