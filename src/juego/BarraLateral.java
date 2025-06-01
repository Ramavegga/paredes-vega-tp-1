package juego;

import java.awt.Image;
import java.awt.Color;
import entorno.Herramientas;
import entorno.Entorno;

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
        entorno.dibujarImagen(imgBarra, x, y, 3.141, 2.135);
    }

    
    public void dibujarContadorEnemigos(int enemigosAsesinados, Entorno entorno) {
        int rectX = 740;
        int rectY = 20;
        int rectAncho = 100;
        int rectAlto = 20;
        entorno.dibujarRectangulo(rectX, rectY, rectAncho, rectAlto, 0, Color.GRAY);
        int textoX = rectX - rectAncho / 2 + 5; 
        int textoY = rectY + rectAlto / 2 + 5;
        entorno.cambiarFont("Arial", 10, Color.WHITE);
        entorno.escribirTexto("Enemigos: " + enemigosAsesinados + "/50", textoX, textoY);
    }
}