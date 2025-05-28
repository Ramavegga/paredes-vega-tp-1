package juego;

import java.awt.Image;

import entorno.Herramientas;

	public class BarraLateral {
		double x;
		double y;
		Image imgBarra;
		
		public BarraLateral(double x, double y) {
			this.x = x;
			this.y = y;
			imgBarra = Herramientas.cargarImagen("barralateral.jpg");
		}
		public void dibujar(entorno.Entorno entorno) {
		        entorno.dibujarImagen(imgBarra,x,y,3.141,2.135);
		    
		}
	}
	