package Version_1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Carrera extends Canvas{
	private Pista[] pistas = new Pista[4];
	private Vehiculo[] conductores = new Vehiculo[4];
	private Vehiculo[] podio = new Vehiculo[3];
	int jugadoresEnMeta = 0;
	boolean metaLlena = false;
	public BufferedImage buffer;
	
	public Carrera() {
		
		Coche c;
		Moto m;
		
		/*
		JFrame ventana = new JFrame("Carrera");	
		
		JPanel panel = (JPanel)ventana.getContentPane();
		
		setBounds(0,0,1000,600);
		
		panel.setPreferredSize(new Dimension(1000,600));
		panel.setLayout(null);
		panel.add(this);
		
		ventana.setBounds(0,0,1000,600);
		ventana.setVisible(true);
		*/
		
		for(int i = 0 ; i < 4 ; i++) {
		String str = JOptionPane.showInputDialog("\nJugador" +  (i+1) + " selecciona un vehículo\n-1 Moto\n-2 Coche");
			if(Integer.parseInt(str) != 1 && Integer.parseInt(str) != 2) {
				i--;
			}
			if(Integer.parseInt(str) == 1) {
				m = new Moto();
				m.setNombre("Conductor " + (i+1));
				conductores[i] = m;
			}
			if(Integer.parseInt(str) == 2) {
				c = new Coche();
				c.setNombre("Conductor " + (i+1));
				conductores[i] = c;
			}
		}
		
		for(int i = 0 ; i < 4 ; i++) {
			Pista p = new Pista(conductores[i], i+1);
			pistas[i] = p;
		}
		

	}
	
	public void carreraCoches() {
		int ronda = 1;
		while(!metaLlena) {
			System.out.println("\nRonda " + ronda);
			for(int i = 0; i < 4; i++) {
				if(!pistas[i].metaPista()) {
				System.out.println("\nConductor " + (i+1));
				pistas[i].conducir();
				if(pistas[i].metaPista()) llegarMeta(conductores[i]);
				}
			}
			ronda++;
		}
		
	}
	
	public void mostrarPodio() {
		
		System.out.println("\nPodio de jugadores");
		for(int i = 0; i < 3; i++) {
			System.out.println(podio[i].getNombre());
			
		}
	}
	
	public void llegarMeta(Vehiculo v) {
		if( jugadoresEnMeta == 3) {
			metaLlena = true;
			mostrarPodio();
		}
		if(jugadoresEnMeta < 3) {
			podio[jugadoresEnMeta] = v;
			jugadoresEnMeta++;
		}
		
	}	
	
}
