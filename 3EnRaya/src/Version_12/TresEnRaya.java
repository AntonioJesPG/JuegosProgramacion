package Version_12;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;


public class TresEnRaya extends Canvas{

	//Creamos la ventana
	JFrame ventana = new JFrame("3 en raya");
	
	//Alto y ancho de la ventana
	private static final int JFRAME_WIDTH=700;
	private static final int JFRAME_HEIGHT=700;
	
	//Lista de Cuadros que va a tener el canvas
	//El tablero va a ser 3 x 3
	List<Cuadro> cuadros = new ArrayList<Cuadro>();
	
	//Se crean los jugadores y una variable para los turnos
	public static int JUGADOR_1 = 1;
	public static int JUGADOR_2 = 2;
	private int turnoActual = JUGADOR_1;
	
	//Variable de instancia del patr�n Singleton
	private static TresEnRaya instance = null;
	
	//Constructor
	public TresEnRaya() {
		
		//Creamos el JPanel haciendo un recast del contenido de nuestra ventana
		JPanel panel = (JPanel) ventana.getContentPane();
		
		//Le establecemos el layout al panel y lo a�adimos
		panel.setLayout(new BorderLayout());
		panel.add(this, BorderLayout.CENTER);
		
		//Dimensi�n de la ventana
		ventana.setBounds(0,0,JFRAME_WIDTH,JFRAME_HEIGHT);
		
		//Se inicializan los cuadros
		inicializaCuadrosDelTablero();
		
		//Se a�ade un m�todo para detectar los clicks
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//Solo necesitamos el click izquierdo
				if(e.getButton() == MouseEvent.BUTTON1) {
					//Buscamos el cuadro en el que hacemos click
					for(Cuadro cuadro: cuadros) {
						if(cuadro.seHaHechoClickSobreCuadro(e.getX(), e.getY())) {
							cuadro.click(turnoActual);
							
							//Aqu� han de cambiarse los turnos
							
							if(turnoActual == JUGADOR_1) {
								AlmacenSonidos.getInstance().playSound("musket.wav");
								turnoActual = JUGADOR_2;
							}
							
							else {
								AlmacenSonidos.getInstance().playSound("swords.wav");
								turnoActual = JUGADOR_1;
							}
						}
					}
				}
			}
		});
		
		//Sonido en bucle del juego
		AlmacenSonidos.getInstance().loopSound("PirateTheme.wav");
		
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
		
		//Creamos las opciones de salir de la aplicaci�n
		int eleccion = JOptionPane.showOptionDialog(ventana, "�Salir de la aplicaci�n?","Salir",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		
		//Si el usuario desea salir lo cerramos
		if(eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	//Modificamos el m�todo paint de canvas
	@Override
	public void paint(Graphics g) {
			super.paint(g);
			
			//Pintamos un rect�ngulo
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			//Para cada cuadro que se tiene se pinta el mismo
			for( Cuadro cuadro : cuadros) {
				cuadro.paint(g);
			}
	}
	
	//M�todo para devolver la instancia Singleton
	public static TresEnRaya getInstance() {
		
		if(instance == null) instance = new TresEnRaya();
		return instance;
		
	}
	
	/*M�todo para inicializar los cuadros
	 * La primera fila de 3 cuadros tendr� como coordenadas 0,0  0,1 y 0,2
	 * La segunda fila de 3 cuadros tendr� como coordenadas 1,0  1,1 y 1,2
	 * La tercera fila de 3 cuadros tendr� como coordenadas 2,0  2,1 y 2,2
	 */
	private void inicializaCuadrosDelTablero() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.cuadros.add(new Cuadro(j, i));
			}
		}
	}
	
	//M�todo main
	public static void main(String[] args) {
		TresEnRaya.getInstance();
	}
}
