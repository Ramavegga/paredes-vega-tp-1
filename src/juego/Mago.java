package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Mago {

	double x;
	double y;
	double anguloFondo;
	Image imgNormal;
	Image imgDerecha;
	Image imgIzquierda;
	Image imgEspalda;
	Image imgFrente;

	
	int ancho = 50;
	int alto = 75;

	public Mago(int x, int y) {
		this.x = x;
		this.y = y;
		imgNormal = Herramientas.cargarImagen("mago base.png");
		imgDerecha = Herramientas.cargarImagen("magoderecha.png");
		imgIzquierda = Herramientas.cargarImagen("magoizquierda.png");
		imgEspalda = Herramientas.cargarImagen("magoespalda.png");
		imgFrente = Herramientas.cargarImagen("magofrente.png");
		anguloFondo = 0;
	}
	public double getX() { return x; }
	public double getY() { return y; }
	public int getAncho() { return ancho; }
	public int getAlto() { return alto; }
}

