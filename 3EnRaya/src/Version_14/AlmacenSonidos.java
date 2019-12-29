package Version_14;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

public class AlmacenSonidos {

	//Se crea un HashMap que va a contener los sonidos
	private HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();
	
	//Se incorpora un patrón Singleton
	private static AlmacenSonidos instance = null;
	
	//Carpeta con los recursos
	private static String RESOURCES_FOLDER = "../resources/sounds/";
	
	//Recursos de sonido
	public static String MUSICA_DE_FONDO = "PirateTheme.wav";
	public static String EFECTO_JUGADOR_1 = "musket.wav";
	public static String EFECTO_JUGADOR_2 = "swords.wav";
	
	public AlmacenSonidos() {
		//Cargamos los recursos de sonido
		this.getAudioClip(MUSICA_DE_FONDO);
		this.getAudioClip(EFECTO_JUGADOR_1);
		this.getAudioClip(EFECTO_JUGADOR_2);
	}
	
	//Método del patrón Singleton
	public static AlmacenSonidos getInstance() {
		if (instance == null) {
			instance = new AlmacenSonidos();
		}
		return instance;
	}
	

	 //Método de carga de un sonido, en forma de AudioClip, desde el disco duro
	private AudioClip loadResource(String name) {
		URL url=null;
		url = getClass().getResource(name);
		return Applet.newAudioClip(url);
	}
	

	//Obtiene el AudioClip desde el almacén HashMap, si no existe lo carga desde el disco duro.
	private AudioClip getAudioClip(String resourceName) {
		
		// En primera instancia intentamos obtener el objeto AudioClip a partir del HashMap.
		AudioClip clip = sounds.get(resourceName);
		
		// En caso de que el objeto AudioClip no exista dentro del HashMap, se carga desde el disco duro
		if (clip == null) {
			clip = loadResource(RESOURCES_FOLDER + resourceName);
			// Una vez que cargo el recurso en la memoria, lo agrego al HashMap, así no habrá que volver a 
			// buscarlo en el disco duro. Como "clave" del objeto en el HashMap utilizo el nombre del fichero
			sounds.put(resourceName, clip);
		}
		return clip;	
	}

	//Reproducir una única vez el sonido
	public void playSound(final String name) {
		getAudioClip(name).play();
	}
	
	//Reproducción en bucle
	public void loopSound(final String name) {
		getAudioClip(name).loop();
	}
}
