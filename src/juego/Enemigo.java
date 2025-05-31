package juego;

import java.awt.Image;

import entorno.Herramientas;

public class Enemigo {

	double x;
	double y;
	Enemigo [] enemigo;
	public double velocidad;
	public double angulo;
	public int radio;
	Image imgMurcielago;
	
	public Enemigo(double x, double y, double velocidad, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		imgMurcielago = Herramientas.cargarImagen("murcielago.png");
			
		}
	}
