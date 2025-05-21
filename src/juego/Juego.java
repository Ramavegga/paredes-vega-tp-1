package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;

public class Juego extends InterfaceJuego
{
	
	private Entorno entorno;
	//holaaaaa
	Mago mago;
	Enemigo[] enemigo;
	Image imgFondo;
	Image imgNormal;
	Image imgMurcielago;
	Image imgRoca;
	double anguloFondo;
	Roca[] rocas = new Roca[5];
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		imgNormal = Herramientas.cargarImagen("mago base.png");
		imgMurcielago = Herramientas.cargarImagen("murielago.png");
		anguloFondo = 0;
		mago = new Mago(400, 300);
		enemigo = new Enemigo[5];
		for (int i = 0; i < enemigo.length; i++) {
			double x = Math.random() * 600;
			double y = Math.random() * 600;
			double velocidad = 2 + Math.random();
			double angulo = Math.random() * 2 * Math.PI;
			int radio = 13;
			enemigo[i] = new Enemigo(x, y, velocidad, angulo, radio);}
		for (int i = 0; i < rocas.length; i++) {
		    double x = Math.random() * 800;
		    double y = Math.random() * 600;
		    rocas[i] = new Roca(x, y);}
		imgFondo = Herramientas.cargarImagen("suelo.png");
		

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		entorno.dibujarImagen(imgFondo, 300, 300, anguloFondo, 1.0);
		mago.movimiento(entorno, rocas);
		for (Enemigo enemigo : enemigo) {
		    enemigo.dibujo(entorno);
		    enemigo.mover();

		    if (enemigo.chocasteCon(entorno)) {
		        enemigo.rebotar();
		    }
		}
		
		for (Roca roca : rocas) {
		    roca.dibujar(entorno);
		    }
		for (Enemigo e : enemigo) {
            e.mover();
            e.dibujo(entorno);
        }
	}
    

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		}	
	
}
