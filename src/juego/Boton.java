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
	Image fuego; 
	Image agua;
	public Boton(int x, int y, int ancho, int alto) {
			this.x = x;
			this.y = y;
			this.ancho = ancho;
			this.alto = alto;
			fuego = Herramientas.cargarImagen("fuego.png");
			agua = Herramientas.cargarImagen("agua.png");
		 }
	public void dibujar(Entorno e) {
				e.dibujarImagen(fuego, x, y, 0,0.07);
 }
	public void dibujar1(Entorno e) {
				e.dibujarImagen(agua, x, y, 0, 0.07);

	}
	public boolean estaPresionado(double px, double py) {
	    return px >= x - ancho / 2 && px <= x + ancho / 2 && py >= y - alto / 2 && py <= y + alto / 2;
	}

}