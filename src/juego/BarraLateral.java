package juego;
import java.awt.Color;

	public class BarraLateral {
		double x;
		double y;
		int ancho = 100;
		int alto = 600;
		Color color;
		
		public BarraLateral(double x, double y, Color color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
		public void dibujar(entorno.Entorno entorno) {
		        entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);
		    }
	}