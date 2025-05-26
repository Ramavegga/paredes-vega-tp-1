package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
public class Boton {
	int x;
	int y;
	int ancho;
	int alto;

		public Boton(int x, int y, int ancho, int alto) {
			this.x = x;
			this.y = y;
			this.ancho = ancho;
			this.alto = alto;
		 }
	public void dibujar(Entorno e) {
				e.dibujarRectangulo(x, y, ancho, alto, 0, Color.blue);
				e.cambiarFont("Arial", 18, Color.CYAN);
				e.escribirTexto("Derecha", x, y);
 }
}
