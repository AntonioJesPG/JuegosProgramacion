package Version_14;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;


public class Cuadro {
	
	//Posici�n del cuadro en el tablero del tres en raya
	private int x, y;
	
	//Pixel superior izquierdo del cuadro ( a partir de este pixel se pinta el cuadro)
	private int esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY;
	// Ancho y alto de este cuadro
	private int ancho, alto;
	
	//Variable para saber si se le ha clickado
	private boolean clickHecho = false;
	
	//Variable del jugador que es propietario de este cuadro
	
	int jugadorPropietario=0;
	
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
		
		//Si se le clicka se pintan im�genes
		if(this.clickHecho) {
			//pintaImagenesVectoriales(g);
			pintaImagenesMapaDeBits(g);
		}
	}
	
	//Debe de depender del jugador que haga click se pinta un c�rculo o una X
	private void pintaImagenesVectoriales  (Graphics g) {
		
		//Como usamos un fondo negro el c�rculo ha de ser blanco (Por defecto es negro)
		g.setColor(Color.WHITE);
		
		//Si el jugador es el 1 se pinta una cruz
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_1) {
			// Para pintar una cruz pinto dos l�neas que se cruzan
			g.setColor(Color.WHITE);
			g.drawLine(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY, 
					this.esquinaSuperiorIzquierdaX + this.ancho, this.esquinaSuperiorIzquierdaY + alto);
			g.drawLine(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY + alto, 
					this.esquinaSuperiorIzquierdaX + this.ancho, this.esquinaSuperiorIzquierdaY);
		} 
		
		//Si el jugador es el 2 se pinta un c�rculo
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_2) {
		g.drawOval(this.esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY, this.ancho, this.alto);
		}
	}
	
	//Este m�todo pinta una imagen de las que se tiene en la carpeta
	private void pintaImagenesMapaDeBits (Graphics g) {
		
		// Ahora, dependiendo del jugador propietario de este cuadro, pinto algo diferente
		
				BufferedImage imagenAPintar = null;
				if (this.jugadorPropietario == TresEnRaya.JUGADOR_1) { // Comprueba jugador 1 - Pinto una calavera
					imagenAPintar = AlmacenImagenes.getInstance().getSprite("skull.png");
				} 
				if (this.jugadorPropietario == TresEnRaya.JUGADOR_2) { // En este caso el jugador 2 - Pinto huesos cruzados
					imagenAPintar = AlmacenImagenes.getInstance().getSprite("crossbones.png");
				}
				
				// Una vez que s� qu� imagen quiero pintar, la muestro en pantalla
				if (imagenAPintar != null) {
					int x = this.esquinaSuperiorIzquierdaX + this.ancho / 2 - imagenAPintar.getWidth() / 2;
					int y = this.esquinaSuperiorIzquierdaY + this.alto / 2 - imagenAPintar.getHeight() / 2;
					g.drawImage(imagenAPintar, x, y, null);
				}
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
	public void click (int i) {
		if(this.jugadorPropietario == 0) {
			this.jugadorPropietario = i;
		}
		
		//Repasar este m�todo
		
		//Se actualiza la matriz de jugadas para reflejar la nueva jugada
		TresEnRaya.getInstance().getMatrizJugadas()[this.y][this.x] = i;
		
		//Establecemos al jugador propietario del cuadro
		jugadorPropietario = i;
		
		//Cambiamos la variable booleana ya que se le ha clickado
		this.clickHecho = true;
		
		//Repintamos el objeto
		TresEnRaya.getInstance().repaint();
		TresEnRaya.getInstance().revalidate();	
		
		// S�lo por depurar el programa, imprimo en consola la matriz de jugadas
				System.out.println("Estado del juego");
				for (int p = 0; p < TresEnRaya.getInstance().getMatrizJugadas().length; p++) {
					for (int j = 0; j < TresEnRaya.getInstance().getMatrizJugadas()[p].length; j++) {
						System.out.print(TresEnRaya.getInstance().getMatrizJugadas()[p][j] + "\t");
					}
					System.out.println();
				}
		
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
