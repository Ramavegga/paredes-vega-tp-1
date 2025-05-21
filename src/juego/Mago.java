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

	public void movimiento(Entorno entorno, Roca[] rocas) {
		double nuevaX = this.x;
		double nuevaY = this.y;

		if (entorno.estaPresionada('w')) {
			nuevaY -= 2;
			if (!hayColision(nuevaX, nuevaY, ancho, alto, rocas)) {
				y = nuevaY;
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}
			entorno.dibujarImagen(imgEspalda, this.x, this.y, anguloFondo, 0.08);
		} else if (entorno.estaPresionada('s')) {
			nuevaY += 2;
			if (!hayColision(nuevaX, nuevaY, ancho, alto, rocas)) {
				y = nuevaY;
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}
			
			entorno.dibujarImagen(imgFrente, this.x, this.y, anguloFondo, 0.08);
		} else if (entorno.estaPresionada('a')) {
			nuevaX -= 2;
			if (!hayColision(nuevaX, nuevaY, ancho, alto, rocas)) {
				x = nuevaX;
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}
			
			entorno.dibujarImagen(imgDerecha, this.x, this.y, anguloFondo, 0.08);
		} else if (entorno.estaPresionada('d')) {
			nuevaX += 2;
			if (!hayColision(nuevaX, nuevaY, ancho, alto, rocas)) {
				x = nuevaX;
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}

			entorno.dibujarImagen(imgIzquierda, this.x, this.y, anguloFondo, 0.08);
		} else {
			entorno.dibujarImagen(imgNormal, this.x, this.y, anguloFondo, 0.08);
			//entorno.dibujarRectangulo(nuevaX, nuevaY, 50, 75, 0, Color.MAGENTA);
		}
	}

	private boolean hayColision(double xNuevo, double yNuevo, int ancho, int alto, Roca[] rocas) {
		for (Roca roca : rocas) {
			if (roca.colisionaCon(xNuevo - ancho / 2, yNuevo - alto / 2, ancho, alto)) {
				return true;
			}
		}
		return false;
	}


	public double getX() { return x; }
	public double getY() { return y; }
	public int getAncho() { return ancho; }
	public int getAlto() { return alto; }
}

