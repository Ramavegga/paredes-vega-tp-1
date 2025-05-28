package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Roca {
	double x;
	double y;
	int ancho = 30;
	int alto = 35;
	Image imgRoca;

	public Roca(double x, double y) {
		this.x = x;
		this.y = y;
		imgRoca = Herramientas.cargarImagen("roca.png");
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imgRoca, x, y, 0, 0.08);
		//entorno.dibujarRectangulo(x, y, 35, 38, 0, Color.green);
	}


	public boolean colisionaCon(double mx, double my, int mancho, int malto) {
		double rx = this.x - this.ancho / 2;
		double ry = this.y - this.alto / 2;

		return mx < rx + this.ancho &&
		       mx + mancho > rx &&
		       my < ry + this.alto &&
		       my + malto > ry;
	}
}
