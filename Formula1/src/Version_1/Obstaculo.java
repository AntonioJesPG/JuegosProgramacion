package Version_1;

public abstract class Obstaculo{
	
	private int x;
	private int tamanio;
	private int reduccionDeVelocidad;
	
	public Obstaculo(int x,int tamanio, int reduccionDeVelocidad){
		this.x = x;
		this.tamanio = tamanio;
		this.reduccionDeVelocidad = reduccionDeVelocidad;
	}

	
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getTamanio() {
		return tamanio;
	}



	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}



	public int getReduccionDeVelocidad() {
		return reduccionDeVelocidad;
	}



	public void setReduccionDeVelocidad(int reduccionDeVelocidad) {
		this.reduccionDeVelocidad = reduccionDeVelocidad;
	}



	public abstract void paint();
}
