package Final_Version;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

//Esta clase es usada para mostrar el podio y ver que jugador ha ganado
public class Podio extends Canvas{

	JFrame ventana = new JFrame("Podio");
	
	//Alto y ancho de la ventana
	private static final int JFRAME_WIDTH=700;
	private static final int JFRAME_HEIGHT=700;
	
	private int ganador;
	
	
	//Constructor
		public Podio(int i) {

		ganador = i;	
			
		AlmacenImagenes.getInstance();
			
		//Creamos el JPanel haciendo un recast del contenido de nuestra ventana
		JPanel panel = (JPanel) ventana.getContentPane();
			
		//Le establecemos el layout al panel y lo añadimos
		panel.setLayout(new BorderLayout());
		panel.add(this, BorderLayout.CENTER);
			
		//Dimensión de la ventana
		ventana.setBounds(0,0,JFRAME_WIDTH,JFRAME_HEIGHT);
							
		//Sonido en bucle del podio
		AlmacenSonidos.getInstance().loopSound("VictoryTheme.wav");
		
		//Eliminamos el cierre normal de la ventana y personalizamos la posibilidad de cerrar la ventana
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
					cerrarAplicacion();
				}
			});
			
		//Mostrar la ventana
		ventana.setVisible(true);
			
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
				
			if(ganador == TresEnRaya.getInstance().JUGADOR_1) {
			g.drawImage(AlmacenImagenes.getInstance().getSprite("ganadorJ1.png"),0,0,JFRAME_WIDTH,JFRAME_HEIGHT,this);
			}
			if(ganador == TresEnRaya.getInstance().JUGADOR_2) {
				g.drawImage(AlmacenImagenes.getInstance().getSprite("ganadorJ2.png"),0,0,JFRAME_WIDTH,JFRAME_HEIGHT,this);
				}
			
			
		}
}
