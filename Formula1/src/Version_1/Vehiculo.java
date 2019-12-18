package Version_1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Vehiculo {
	
	private int x=0;
	private int y = 0;
	private int velocidad; //Es la Y de la pantalla
	private String nombre;
	
	public Vehiculo() {
		this.velocidad = 1;
		
	}
	
	public Vehiculo(int x, int y) {
		this.x = x;
		this.y = y;
		this.velocidad = 1;
	}
	
	public int avanza() {
		int avance = (int) Math.round(Math.random() * (6 - 0)) + 0;
		return velocidad*avance;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public abstract void paint(JPanel panel);

	public class Cuadrado extends JComponent{
		
		public void paintComponent(Graphics g){
	        
	            g.setColor(Color.RED);
	            g.drawRect(x,y, 50,50);        
	         
	    }
	}
	
	public class Circulo extends JComponent{
		
		public void paintComponent(Graphics g){
	        
	            g.setColor(Color.GREEN);
	            g.drawOval(x,y, 50,50);        
	         
	    }
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
