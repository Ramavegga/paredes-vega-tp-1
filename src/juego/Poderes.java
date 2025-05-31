package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Poderes {
	double x;
	double y;
	public int radio;
	String tipoPoder;
	long tiempoCreacion;
	final long DURACION_VISIBLE_MS = 300;
	Image PoderFuego; 
	Image PoderAgua;

	public Poderes(double x, double y, int radio, String tipoPoder) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.tipoPoder = tipoPoder;
		this.tiempoCreacion = System.currentTimeMillis(); 
		PoderFuego = Herramientas.cargarImagen("PoderFuego.png"); 
		PoderAgua = Herramientas.cargarImagen("PoderAgua.png");
	}

	public void dibujarEfecto(Entorno e) {
		if (System.currentTimeMillis() - this.tiempoCreacion < DURACION_VISIBLE_MS) {
			if (this.tipoPoder.equals("fuego")) {
				e.dibujarImagen(PoderFuego, x, y, 0, 0.15); // Dibuja la imagen del poder de fuego
			}
			if(this.tipoPoder.equals("agua")) {
				e.dibujarCirculo(this.x, this.y, this.radio, Color.CYAN); 
				e.dibujarImagen(PoderAgua, x, y, 0, 0.15);
			}
		}
	}

	
	public int aplicarEfecto(Enemigo[] enemigos, Entorno e) {
		int enemigosAfectados = 0; 
		
		// Lógica para el poder de fuego
		if (this.tipoPoder.equals("fuego")) {
			for (int i = 0; i < enemigos.length; i++) {
				Enemigo currentEnemigo = enemigos[i];
				if (currentEnemigo != null) {
					double distanciaX = this.x - currentEnemigo.x;
					double distanciaY = this.y - currentEnemigo.y;
					double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
					if (distancia < this.radio + currentEnemigo.radio) {
						enemigos[i] = crearNuevoEnemigo(e); 
						enemigosAfectados++;
					}
				}
			}
		}

		// Lógica para el poder de agua
		if (this.tipoPoder.equals("agua")) {
			for (int i = 0; i < enemigos.length; i++) {
				Enemigo currentEnemigo = enemigos[i];
				if (currentEnemigo != null) {
					double distanciaX = this.x - currentEnemigo.x;
					double distanciaY = this.y - currentEnemigo.y;
					double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
					if (distancia < this.radio + currentEnemigo.radio) {
						enemigos[i] = crearNuevoEnemigo(e); 
						enemigosAfectados++;
					}
				}
			}
		}

		return enemigosAfectados; 
	}

	
	private Enemigo crearNuevoEnemigo(Entorno e) {
		double xNuevo, yNuevo;
		int radioEnemigo = 13;
		int bordeDeAparicion = (int) (Math.random() * 4);

		if (bordeDeAparicion == 0) {
			xNuevo = Math.random() * e.ancho();
			yNuevo = -radioEnemigo;
		} else if (bordeDeAparicion == 1) {
			xNuevo = Math.random() * e.ancho();
			yNuevo = e.alto() + radioEnemigo;
		} else if (bordeDeAparicion == 2) {
			xNuevo = -radioEnemigo;
			yNuevo = Math.random() * e.alto();
		} else { // Derecha
			xNuevo = e.ancho() + radioEnemigo;
			yNuevo = Math.random() * e.alto();
		}
		double velocidadNueva = 2 + Math.random();
		double anguloNuevo = Math.random() * 2 * Math.PI;
		return new Enemigo(xNuevo, yNuevo, velocidadNueva, anguloNuevo, radioEnemigo);
	}

	public boolean yaTermino() {
		return System.currentTimeMillis() - this.tiempoCreacion >= DURACION_VISIBLE_MS;
	}
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, 90, 35, 0, Color.RED);
		e.cambiarFont("Arial", 15, Color.WHITE);
		e.escribirTexto("FUEGO", x - 25, y + 5);
	}
	public void dibujar1(Entorno e) {
		e.dibujarRectangulo(x, y, 90, 35, 0, Color.BLUE);
		e.cambiarFont("Arial", 15, Color.WHITE);
		e.escribirTexto("AGUA", x - 20, y + 5);
	}
}