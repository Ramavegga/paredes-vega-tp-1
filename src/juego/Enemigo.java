package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {

	double x;
	double y;
	Enemigo [] enemigo;
	private double velocidad;
	private double angulo;
	private int radio;
	Image imgMurcielago;
	
	

	
	public Enemigo(double x, double y, double velocidad, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		imgMurcielago = Herramientas.cargarImagen("murielago.png");
			
	}
	
	public void mover() {
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
	}
	
	
	public boolean chocasteCon(Entorno e) {
		return x <= radio || y <= radio || x >= e.ancho() - radio || y >= e.alto() - radio;		
	}
	
	public void rebotar() {
		angulo += Math.PI/2;
	}
	
	public void acelerar() {
		velocidad += 0.05;
	}
	
	public void dibujo(Entorno entorno) {
		//entorno.dibujarImagen(imgMurcielago, x, y, angulo, 0.08);
		entorno.dibujarCirculo(x, y, 2 * radio, Color.CYAN);
	}
}
