package Version_1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Coche extends Vehiculo{

	
	
	public Coche() {
		super();
	}
	
	public Coche(int x, int y) {
		super(x,y);
	}
	
	public void paint(JPanel frame) {
		frame.add(new Circulo());
	}


}
