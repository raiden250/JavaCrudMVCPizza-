package pizza_package;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.Part;

public class Image {
	
	public static String getImageName(Part part) {
		for(String content: part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=')+1).trim().replace( "\"", "" );
			}
		}
		return null;
	}
	
	public static String getValeur( Part part ) throws IOException {
	    BufferedReader reader = new BufferedReader( new InputStreamReader( part.getInputStream(), "UTF-8" ) );
	    StringBuilder valeur = new StringBuilder();
	    char[] buffer = new char[1024];
	    int longueur = 0;
	    while ( ( longueur = reader.read( buffer ) ) > 0 ) {
	        valeur.append( buffer, 0, longueur );
	    }
	    return valeur.toString();
	}
	
	public static void Save(Part part, String fileName, String path) {
		final int tailleTampon = 10240;
		
		BufferedInputStream input = null;
		BufferedOutputStream output =null;
		
		try {
			input = new BufferedInputStream(part.getInputStream(), tailleTampon);
			output = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)), tailleTampon);
			
			byte[] tampon = new byte[tailleTampon];
			int longueur ;
			
			while((longueur = input.read(tampon))>0) {
				output.write(tampon,0,longueur);		
				}
		}catch(IOException e) {
			
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void Delete(String pathImage) {
		File image = new File(pathImage);
		image.delete();
	}
}
