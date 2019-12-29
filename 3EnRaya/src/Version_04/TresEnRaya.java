package Version_04;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class TresEnRaya extends Canvas{

	//Creamos la ventana
	JFrame ventana = new JFrame("3 en raya");
	
	//Alto y ancho de la ventana
	private static final int JFRAME_WIDTH=700;
	private static final int JFRAME_HEIGHT=700;
	
	//Variable de instancia del patrón Singleton
	private static TresEnRaya instance = null;
	
	//Constructor
	public TresEnRaya() {
		
		//Creamos el JPanel haciendo un recast del contenido de nuestra ventana
		JPanel panel = (JPanel) ventana.getContentPane();
		
		//Le establecemos el layout al panel y lo añadimos
		panel.setLayout(new BorderLayout());
		panel.add(this, BorderLayout.CENTER);
		
		//Dimensión de la ventana y mostrarla
		ventana.setBounds(0,0,JFRAME_WIDTH,JFRAME_HEIGHT);
		ventana.setVisible(true);
		
		//Eliminamos el cierre normal de la ventana y personalizamos la posibilidad de cerrar la ventana
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
			}
		});
		
		//Cambiamos el foco al canvas
		this.requestFocus();
		
	}
	
	private void cerrarAplicacion() {
		String[] opciones = {"Aceptar","Cancelar"};
		
		//Creamos las opciones de salir de la aplicación
		int eleccion = JOptionPane.showOptionDialog(ventana, "¿Salir de la aplicación?","Salir",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		
		//Si el usuario desea salir lo cerramos
		if(eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	//Modificamos el método paint de canvas
	@Override
	public void paint(Graphics g) {
			super.paint(g);
			
			//Pintamos un rectángulo blanco
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	//Método para devolver la instancia Singleton
	public static TresEnRaya getInstance() {
		
		if(instance == null) instance = new TresEnRaya();
		return instance;
		
	}
	
	//Método main
	public static void main(String[] args) {
		TresEnRaya.getInstance();
	}
}
