package juego;

import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Color;
import java.awt.Image;




public class Boton {
	double x;
	double y;
	int ancho;
	int alto;
	
		public Boton(int x, int y, int ancho, int alto) {
			this.x = x;
			this.y = y;
			this.ancho = ancho;
			this.alto = alto;
		 }
	public void dibujar(Entorno e) {
				e.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED);
				e.cambiarFont("Arial", 18, Color.ORANGE);
				e.escribirTexto("FUEGO", x - ancho/3, y + alto/5);
 }
	public void dibujar1(Entorno e) {
				e.dibujarRectangulo(x, y, ancho, alto, 0, Color.BLUE);
				e.cambiarFont("Arial", 18, Color.WHITE);
				e.escribirTexto("AGUA",x - ancho/4, y + alto/6);
	}
	public boolean estaPresionado(double px, double py) {
	    return px >= x - ancho / 2 && px <= x + ancho / 2 && py >= y - alto / 2 && py <= y + alto / 2;
	}

}
