package Version_08;

import java.awt.Color;
import java.awt.Graphics;

public class Cuadro {
	
	//Posici�n del cuadro en el tablero del tres en raya
	private int x, y;
	
	//Pixel superior izquierdo del cuadro ( a partir de este pixel se pinta el cuadro)
	private int esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY;
	// Ancho y alto de este cuadro
	private int ancho, alto;
	
	public Cuadro(int x, int y) {
		
		super();
		this.x = x;
		this.y = y;
		
	}

	//M�todo para pintar el cuadro
	public void paint(Graphics g) {
		
		//Se obtiene el alto y el ancho de los cuadros por medio del Canvas y se divide entre 3 ya que vamos a tener 3 cuadros de alto y ancho
		ancho = TresEnRaya.getInstance().getWidth() / 3;
		alto = TresEnRaya.getInstance().getHeight() / 3;
		esquinaSuperiorIzquierdaX = this.x * ancho;
		esquinaSuperiorIzquierdaY = this.y * alto;
		
		// Pintar el borde
		g.setColor(Color.WHITE);
		g.drawRect(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY, ancho, alto);	
	}
	
	//M�todo para comprobar si se ha hecho click sobre el cuadro actual.
	public boolean seHaHechoClickSobreCuadro (int xClick, int yClick) {
		if (xClick > this.esquinaSuperiorIzquierdaX && xClick < (esquinaSuperiorIzquierdaX + ancho) // Coordenada x dentro del ancho
				&&
			yClick > this.esquinaSuperiorIzquierdaY && yClick < (esquinaSuperiorIzquierdaY + alto)) { // Coordenada y dentro del alto
			return true;
		}
		return false;
	}
	
	//M�todo para cuando se hace click sobre un cuadro
	public void click () {
		System.out.println("Click sobre m� " + this.toString());
	}
	
	@Override
	public String toString() {
		return "Cuadro [xEnTablero=" + x + ", yEnTablero=" + y + "]";
	}

	// Setters y getters	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}
