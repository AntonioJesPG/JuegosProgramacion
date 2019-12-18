package Version_1;

import javax.swing.JOptionPane;

public class Pista {

	private Vehiculo conductor;
	private Obstaculo[] obstaculos = new Obstaculo[4];
	private final int tamanioPista = 20;
	private String[] p = new String[tamanioPista];
	
	public Pista(Vehiculo v, int idConductor) {
		
		for(int i = 0 ; i < 4 ; i++) {
			int valor = (int) Math.round(Math.random() * (100 - 0)) + 0;
			int posicionEnPista = (int) Math.round(Math.random() * (19 - 1)) + 1;
			
			while(hayObstaculo(posicionEnPista)) {
				posicionEnPista = (int) Math.round(Math.random() * (19 - 1)) + 1;
			}
			//Hay que crear posiciones al azar
			if(valor >= 50) {
				Mancha mancha = new Mancha(posicionEnPista,1);
				obstaculos[i] = mancha;
			}
			if(valor < 50) {
				Rampa rampa = new Rampa(posicionEnPista,1);
				obstaculos[i] = rampa;
			}	
		}
		
		this.conductor = v;
		
		for(int i = 0; i < tamanioPista ; i++) p[i] = null;
		
		p[0] = "X";
		
		for(int i=0; i < obstaculos.length; i++) {
			if(obstaculos[i] != null) {
			if(obstaculos[i] instanceof Mancha) p[obstaculos[i].getX()] = "M";
			if(obstaculos[i] instanceof Rampa) p[obstaculos[i].getX()] = "R";
			}
		}
		
		System.out.println("Conductor " + idConductor);
		mostrarPista();
	}
	
	public void mostrarPista() {
		System.out.print("\n[");
		for(int i = 0; i<p.length ; i++) {
			if(p[i]==null) System.out.print(" ,");
			else System.out.print(p[i] + ",");
		}
		System.out.println("]\n");
	}
	
	public void conducir() {
		int cantidadMovimiento = conductor.avanza();
		int movimientosRestantes = cantidadMovimiento;
		boolean puedoMoverme = true;
		
		
		for(int i = 0; i <= cantidadMovimiento; i++) {
			
			p[conductor.getX()] = null;
			if(puedoMoverme) {
				conductor.setX(conductor.getX()+1);
				movimientosRestantes--;
				if(movimientosRestantes <= 0) puedoMoverme = false;
			}
			
			if(hayObstaculo(conductor.getX())) {
				movimientosRestantes = movimientosRestantes - pasarObstaculo(conductor.getX());
			}
			if(conductor.getX() < p.length) p[conductor.getX()] = "X";
			else { 
				p[tamanioPista-1] = "X";
				i = cantidadMovimiento+1;
				}
			}
			mostrarPista();
		}
		//conductor.setX(cantidadMovimiento);
		
	
	
	public int pasarObstaculo(int pos) {
		
		for(int i = 0 ; i < 4 ; i++) {
			if(obstaculos[i] != null) {
				if(obstaculos[i].getX() == pos) return obstaculos[i].getReduccionDeVelocidad();
			}
		}
		
		return 0;
	}
	
	public boolean hayObstaculo(int pos) {
		
		boolean obstaculo = false;
		
		for(int i = 0 ; i < 4 ; i++) {
			if(obstaculos[i] != null) {
				if(obstaculos[i].getX() == pos) obstaculo = true;
			}
		}
		return obstaculo;
	}
	
	public int getTamanioPista() {
		return this.tamanioPista;
	}
	
	public Vehiculo getConductor() {
		return this.conductor;
	}
	
	public boolean metaPista() {
		
		return conductor.getX()>=tamanioPista;

	}
	
}
