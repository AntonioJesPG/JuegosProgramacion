package Version_01;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TresEnRaya {

	//Creamos la ventana
	JFrame ventana = new JFrame("3 en raya");
	
	//Alto y ancho de la ventana
	private static final int JFRAME_WIDTH=700;
	private static final int JFRAME_HEIGHT=700;
	
	//Constructor
	public TresEnRaya() {
		
		//Dimensión de la ventana y mostrarla
		ventana.setBounds(0,0,JFRAME_WIDTH,JFRAME_HEIGHT);
		ventana.setVisible(true);
		
	}
		
	//Método main
	public static void main(String[] args) {
		TresEnRaya tres = new TresEnRaya();
	}
}
