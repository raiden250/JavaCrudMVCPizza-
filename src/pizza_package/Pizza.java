package pizza_package;

import java.util.ArrayList;

import javax.swing.Icon;

public class Pizza {
	
	private int id;
	private double prix;
	private String taille;
	private String nom;
	private Icon image;
	private ArrayList<String> ingredients = new ArrayList<String>();
	
	
	public Pizza (int id,String n, double p){
		this.nom=n; this.prix=p; this.id=id; 
		this.ingredients.add("cheddar"); this.ingredients.add("farine"); 
	}
	
	public Pizza (String n, Icon image){
		this.nom=n; this.image=image;  
	}
	
    public double getPrix() {
		return prix;
	}
    
    public int getId() {
		return id;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setd(int id) {
		this.id = id;
	}
	
	public void setImage(Icon imgage){
		this.image = image;
	}
	
	public Icon getImage(){
		return this.image;
	}
	
    public void addIngredient(String ing){
    	if(!this.ingredients.contains(ing))
    		this.ingredients.add(ing);
    	else
    		System.out.println("ingrédient déja ajouté");
    }
    
	
	public ArrayList<String> getIngredients(){
		return this.ingredients;
	}
	
	public String getStringIngredients(){
		String s="";
		
		if(this.ingredients.size()==0)  s="0 ingredients rempli";
		else{
			for(int i=0; i<this.ingredients.size(); i++)
				s += ingredients.get(i)+" ";
		}
		return s;
	}
	
}
