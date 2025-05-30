package juego;

import java.awt.Image;
import java.awt.Color; 
import entorno.Herramientas;
import entorno.Entorno; 
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
	
	public int vida; 
	public final int VIDA_MAXIMA = 100; 

	public Mago(int x, int y) {
		this.x = x;
		this.y = y;
		this.vida = VIDA_MAXIMA; 
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
	
	public void recibirDaño(int cantidad) {
		this.vida -= cantidad; 
		if (this.vida < 0) {
			this.vida = 0; 
		}
	}
	
	public boolean estaVivo() {
		return this.vida > 0; 
	}

    
    public void dibujarBarraDeVida(Entorno e) { 
        int anchoBarra = 80; 
        int altoBarra = 15;   
        int margenX = 715;    
        int margenY = 200;    

      
        e.dibujarRectangulo(margenX + anchoBarra / 2, margenY + altoBarra / 2, anchoBarra, altoBarra, 0, Color.DARK_GRAY);

      
        double porcentajeVida = (double)this.vida / this.VIDA_MAXIMA; 
        int anchoVidaActual = (int)(anchoBarra * porcentajeVida);

       
        e.dibujarRectangulo(margenX + anchoVidaActual / 2, margenY + altoBarra / 2, anchoVidaActual, altoBarra, 0, Color.GREEN);
        e.cambiarFont("Arial", 10, Color.WHITE);
        e.escribirTexto("Vida: " + this.vida, margenX + 5, margenY + altoBarra / 2 + 5);
    }
    public void dibujarBarraDeEnergia(Entorno e) { 
        int anchoBarra = 80; 
        int altoBarra = 15;   
        int margenX = 715;     
        int margenY = 180;     
        e.dibujarRectangulo(margenX + anchoBarra / 2, margenY + altoBarra / 2, anchoBarra, altoBarra, 0, Color.blue);
        e.cambiarFont("Arial", 10, Color.WHITE);
        e.escribirTexto("Energia: " + this.vida, margenX + 5, margenY + altoBarra / 2 + 5);
    }
}