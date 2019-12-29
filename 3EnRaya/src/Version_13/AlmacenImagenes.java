package Version_13;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AlmacenImagenes {
	
	//Se crea un HashMap que va a contener las imágenes
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	//Se incorpora un patrón Singleton
	private static AlmacenImagenes instance=null;
	
	//Se añade la carpeta que contiene las imágenes
	private static String RESOURCES_FOLDER = "../resources/images/";
	
	//Recursos de imagen
	public static String IMAGEN_JUGADOR_1 = "skull.png";
	public static String IMAGEN_JUGADOR_2 = "crossbones.png";
	
	public AlmacenImagenes() {
		//Carga de los recursos de imagen
		this.getSprite(IMAGEN_JUGADOR_1);
		this.getSprite(IMAGEN_JUGADOR_2);
		
	}
	
	public static AlmacenImagenes getInstance() {
		if (instance == null) {
			instance = new AlmacenImagenes();
		}
		return instance;
	}
	
	//Método de carga de imágenes
	private BufferedImage loadImage(String resourceName) {
		
		// Para localizar el archivo se utiliza una URL
		URL url=null;
		
		//Se usa una excepción por si no se encuentra la imagen
		try {
			
			url = getClass().getResource(resourceName);
			return ImageIO.read(url);
			
		} catch (Exception e) {
			
			//Se muestra el error si no se encuentra la imagen
			System.out.println("No se pudo cargar la imagen " + resourceName +" de "+url);
			System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0); // Fin del programa
		}
		return null; // Sólo se llegará a esta línea si no se ha podido cargar el recurso correctamente
	}
	
	//Método para obtener una imagen
	public BufferedImage getSprite(String resourceName) {
		
		//Se intenta obtener el objeto del hashMap
		BufferedImage img = sprites.get(resourceName);
		
		// En caso de que el objeto BufferedImage no exista dentro del HashMap, se carga desde el disco duro
		if (img == null) {
			img = loadImage(RESOURCES_FOLDER + resourceName);
			
			// Una vez que cargo el recurso en la memoria, lo agrego al HashMap, así no habrá que volver a 
			// buscarlo en el disco duro. Como "clave" del objeto en el HashMap utilizo el nombre del fichero
			sprites.put(resourceName, img);
		}
		return img;
	}
}
