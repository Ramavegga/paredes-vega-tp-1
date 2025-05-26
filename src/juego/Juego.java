package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;

public class Juego extends InterfaceJuego
{
	
	private Entorno entorno;

	Mago mago;
	Enemigo[] enemigo;
	Image imgFondo;
	Image imgNormal;
	Image imgMurcielago;
	Image imgRoca;
	double anguloFondo;
	Roca[] rocas = new Roca[5];
	BarraLateral barra;
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		imgNormal = Herramientas.cargarImagen("mago base.png");
		imgMurcielago = Herramientas.cargarImagen("murielago.png");
		anguloFondo = 0;
		mago = new Mago(400, 300);
		enemigo = new Enemigo[10];
		for (int i = 0; i < enemigo.length; i++) {
			double x = Math.random() * 600;
			double y = Math.random() * 600;
			double velocidad = 2 + Math.random();
			double angulo = Math.random() * 2 * Math.PI;
			int radio = 13;
			enemigo[i] = new Enemigo(x, y, velocidad, angulo, radio);}
		for (int i = 0; i < rocas.length; i++) {
		    double x = Math.random() * 700;
		    double y = Math.random() * 600;
		    rocas[i] = new Roca(x, y);}
		imgFondo = Herramientas.cargarImagen("suelo.png");
		barra = new BarraLateral(750, 300, Color.GRAY);
		this.entorno.iniciar();
		//inicia el juego
	}
//COLISION ROCAS Y ENTORNO
	
	private boolean hayColision(double xNuevo, double yNuevo, int ancho, int alto, Roca[] rocas) {
		  double mitadW = ancho / 2.0;
		  double mitadH = alto  / 2.0;


		    if (xNuevo < mitadW || xNuevo > 700 - mitadW  || yNuevo < mitadH || yNuevo > entorno.alto() - mitadH) {
		        return true;
		    }
		    
		for (Roca roca : rocas) {
			if (roca.colisionaCon(xNuevo - ancho / 2, yNuevo - alto / 2, ancho, alto)) {
				return true;
			}
		}
		return false;
	}
	
//MOVIMIENTO MAGO
	
	public void movimiento(Entorno entorno, Roca[] rocas) {
		double paso = 4;
		double nuevaX = mago.x;
		double nuevaY = mago.y;
		double MitadW = mago.ancho / 2.0;
		double MitadH = mago.alto / 2.0;
		if (entorno.estaPresionada('w')) {
			nuevaY -= paso;
			if (!hayColision(nuevaX, nuevaY, mago.ancho, mago.alto, rocas)) {
				mago.y = nuevaY;
			    nuevaX = Math.max(MitadW, Math.min(nuevaX, entorno.ancho() -  MitadW));
			    nuevaY = Math.max(MitadH, Math.min(nuevaY, entorno.alto()  - MitadH));
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}
			entorno.dibujarImagen(mago.imgEspalda, mago.x, mago.y, anguloFondo, 0.08);
		} 
		else if (entorno.estaPresionada('s')) {
			nuevaY += paso;
			if (!hayColision(nuevaX, nuevaY, mago.ancho, mago.alto, rocas)) {
				mago.y = nuevaY;
				  nuevaX = Math.max(MitadW, Math.min(nuevaX, entorno.ancho() - MitadW));
				  nuevaY = Math.max(MitadH, Math.min(nuevaY, entorno.alto()  - MitadH));
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}
			
			entorno.dibujarImagen(mago.imgFrente, mago.x, mago.y, anguloFondo, 0.08);
		} 
		else if (entorno.estaPresionada('a')) {
			nuevaX -= paso;
			if (!hayColision(nuevaX, nuevaY, mago.ancho, mago.alto, rocas)) {
				mago.x = nuevaX;
				  nuevaX = Math.max(MitadW, Math.min(nuevaX, entorno.ancho() - MitadW));
				  nuevaY = Math.max(MitadH, Math.min(nuevaY, entorno.alto()  - MitadH));
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}
			entorno.dibujarImagen(mago.imgDerecha, mago.x, mago.y, anguloFondo, 0.08);
		} 
		else if (entorno.estaPresionada('d')) {
			nuevaX += paso;
			if (!hayColision(nuevaX, nuevaY, mago.ancho, mago.alto, rocas)) {
				mago.x = nuevaX;
				  nuevaX = Math.max(MitadW, Math.min(nuevaX, entorno.ancho() - MitadW));
				  nuevaY = Math.max(MitadH, Math.min(nuevaY, entorno.alto()  - MitadH));
				//entorno.dibujarRectangulo(nuevaX, nuevaY, 30, 40, 0, Color.MAGENTA);
			}
			entorno.dibujarImagen(mago.imgIzquierda, mago.x, mago.y, anguloFondo, 0.08);
		} 
		else {
			entorno.dibujarImagen(imgNormal, mago.x, mago.y, anguloFondo, 0.08);
			//entorno.dibujarRectangulo(nuevaX, nuevaY, 50, 75, 0, Color.MAGENTA);
		}
	}		
//MOVIMIENTO ENEMIGO

	public void moverEnemigos(Enemigo i) {
		i.y += i.velocidad * Math.sin(i.angulo);
		i.x += i.velocidad * Math.cos(i.angulo);
		}

	public boolean chocasteCon(Entorno e) {
		for (Enemigo i : enemigo) {
			if (i.x <= i.radio || i.y <= i.radio || i.x >= e.ancho() - i.radio || i.y >= e.alto() - i.radio) {
				return true;
			}
		}
		return false;
	}

	public void rebotar(Enemigo i) {
			i.angulo += Math.PI/2;
		}
	public void acelerar(Enemigo i) {
			i.velocidad += 0.001;
			}

	public void dibujoEnemigos(Enemigo i) {
		//entorno.dibujarImagen(imgMurcielago, x, y, angulo, 0.08);
			entorno.dibujarCirculo(i.x, i.y, 2 * i.radio, Color.CYAN);
		}
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick(){
		entorno.dibujarImagen(imgFondo, 300, 300, anguloFondo, 1.0);
		movimiento(entorno, rocas);
		
		for (Enemigo i : enemigo) {
		    
		    i.angulo = Math.atan2(mago.y - i.y, mago.x - i.x);
		    
		    moverEnemigos(i);
		    
		    if (chocasteCon(entorno)) {
		        rebotar(i);
		    }
		    dibujoEnemigos(i);
		  
		    acelerar(i);
			}
	
		for (Roca roca : rocas) {
		    roca.dibujar(entorno);    
		 	}
		barra.dibujar(entorno);{
		}
	}
    
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		}	
	
}
