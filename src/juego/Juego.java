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
	Boton boton;
	Boton boton1;
	private boolean juegoTerminado;
	
	
	
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		imgNormal = Herramientas.cargarImagen("mago base.png");
		imgMurcielago = Herramientas.cargarImagen("murielago.png");
		anguloFondo = 0;
		this.juegoTerminado = false;
		mago = new Mago(400, 300);
		enemigo = new Enemigo[10];
		imgFondo = Herramientas.cargarImagen("suelo.png");
		barra = new BarraLateral(895, 300);
		boton = new Boton(752,350, 90, 35); 
		boton1 = new Boton(752, 390, 90, 35);
		
		   for (int i = 0; i < enemigo.length; i++) {
		        double x;
		        double y;
		        int radio = 13;
		        int bordeDeAparicion = (int) (Math.random() * 4);
		        if (bordeDeAparicion == 0) { 
		            x = Math.random() * entorno.ancho(); 
		            y = -radio; 
		        } else if (bordeDeAparicion == 1) { 
		            x = Math.random() * entorno.ancho(); 
		            y = entorno.alto() + radio; 
		        } else if (bordeDeAparicion == 2) { 
		            x = -radio; 
		            y = Math.random() * entorno.alto(); 
		        } else if (bordeDeAparicion == 3) { 
		            x = entorno.ancho() + radio; 
		            y = Math.random() * entorno.alto(); 
		        } else { 
		            x = 0; y = 0; 
		        }

		        double velocidad = 2 + Math.random();
		        double angulo = Math.random() * 2 * Math.PI; 
		        
		        enemigo[i] = new Enemigo(x, y, velocidad, angulo, radio);
		    }
		   for (int i = 0; i < rocas.length; i++) {
		    double x = Math.random() * 685;
		    double y = Math.random() * 600;
		    rocas[i] = new Roca(x, y);}
		
		
		this.entorno.iniciar();
		//inicia el juego
	}
	
	private void reiniciarJuego() {
	    mago = new Mago(400, 300); 
	    enemigo = new Enemigo[10]; 
	    for (int a = 0; a < enemigo.length; a++) { 
	        double x;
	        double y;
	        int radio = 13; 
	        int bordeDeAparicion = (int) (Math.random() * 4); 

	        if (bordeDeAparicion == 0) { 
	            x = Math.random() * entorno.ancho(); 
	            y = -radio; 
	        } else if (bordeDeAparicion == 1) { 
	            x = Math.random() * entorno.ancho(); 
	            y = entorno.alto() + radio; 
	        } else if (bordeDeAparicion == 2) { 
	            x = -radio; 
	            y = Math.random() * entorno.alto(); 
	        } else { 
	            x = entorno.ancho() + radio; 
	            y = Math.random() * entorno.alto(); 
	        }
	        double velocidad = 2 + Math.random();
	        double angulo = Math.random() * 2 * Math.PI;
	        enemigo[a] = new Enemigo(x, y, velocidad, angulo, radio);
	    }
	    juegoTerminado = false; 
	}
	    
	// MÉTODO DE COLISIÓN Mago-Enemigo 
	private boolean colisionMagoEnemigo(Mago mago, Enemigo enemigo) {
	    double closestX = Math.max(mago.x - mago.ancho / 2, Math.min(enemigo.x, mago.x + mago.ancho / 2));
	    double closestY = Math.max(mago.y - mago.alto / 2, Math.min(enemigo.y, mago.y + mago.alto / 2));

	    double distX = enemigo.x - closestX;
	    double distY = enemigo.y - closestY;
	    double distanciaAlCuadrado = (distX * distX) + (distY * distY);

	    return distanciaAlCuadrado < (enemigo.radio * enemigo.radio);
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
	    if (juegoTerminado) {
	        entorno.cambiarFont("Arial", 50, Color.RED);
	        entorno.escribirTexto("HAS MUERTO", entorno.ancho() / 2 - 150, entorno.alto() / 2);
	        entorno.cambiarFont("Arial", 20, Color.WHITE);
	        entorno.escribirTexto("Presiona Q para reiniciar", entorno.ancho() / 2 - 120, entorno.alto() / 2 + 50);
	        
	        if (entorno.estaPresionada('q')) {
	            reiniciarJuego();
	        }
	        return;
	    }
	    entorno.dibujarImagen(imgFondo, 300, 300, anguloFondo, 1.0);
	    movimiento(entorno, rocas);
	    
	    // Bucle for para poder reemplazar enemigos
	    for (int i = 0; i < enemigo.length; i++) { 
	        Enemigo currentEnemigo = enemigo[i]; 
	        
	        currentEnemigo.angulo = Math.atan2(mago.y - currentEnemigo.y, mago.x - currentEnemigo.x);
	        moverEnemigos(currentEnemigo);
	        if (chocasteCon(entorno)) { 
	            rebotar(currentEnemigo);
	           }
	        dibujoEnemigos(currentEnemigo);
	        acelerar(currentEnemigo);

	        if (colisionMagoEnemigo(mago, currentEnemigo)) {
	            mago.recibirDaño(5); 
	            
	            //Crea un nuevo enemigo y reemplazar al que colisionó
	            double x;
	            double y;
	            int radio = 13; 
	            int bordeDeAparicion = (int) (Math.random() * 4);

	            if (bordeDeAparicion == 0) { 
	                x = Math.random() * entorno.ancho(); 
	                y = -radio; 
	            } else if (bordeDeAparicion == 1) { 
	                x = Math.random() * entorno.ancho(); 
	                y = entorno.alto() + radio; 
	            } else if (bordeDeAparicion == 2) { 
	                x = -radio; 
	                y = Math.random() * entorno.alto(); 
	            } else { 
	                x = entorno.ancho() + radio; 
	                y = Math.random() * entorno.alto(); 
	            }
	            double velocidad = 2 + Math.random();
	            double angulo = Math.random() * 2 * Math.PI;
	            enemigo[i] = new Enemigo(x, y, velocidad, angulo, radio); // Reemplaza al enemigo en el array
	        }
	    }	
	          
	    if (!mago.estaVivo()) {
	        juegoTerminado = true;
	    }

	    for (Roca roca : rocas) {
	        roca.dibujar(entorno);    
	    }
	    barra.dibujar(entorno);
	    boton.dibujar(entorno);
	    boton1.dibujar1(entorno);
	    mago.dibujarBarraDeVida(entorno);
	    mago.dibujarBarraDeEnergia(entorno); 

	    if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
	        double mx = entorno.mouseX();		
	        double my = entorno.mouseY();
	        
	        if (boton.estaPresionado(mx, my)) {
	            System.out.println("Poder de Fuego activado");
	        }
	        if (boton1.estaPresionado(mx, my)) {
	            System.out.println("Poder de Agua activado");
	        }
	    }
	}
    
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		}	
	
}
