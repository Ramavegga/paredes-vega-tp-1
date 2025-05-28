package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Poderes {
	double x;
	double y;
	public int radio;
	Image imgagua;
	Image imgfuego;
	public Poderes(double x, double y, int radio) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		imgagua = Herramientas.cargarImagen("agua.jpg");
		imgfuego = Herramientas.cargarImagen("fuego.jpg");
	}
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, null);
}
	public void dibujar1(Entorno e) {
		e.dibujarCirculo(x, y, radio, null);
	}	
}
