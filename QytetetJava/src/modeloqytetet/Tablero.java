/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;

public class Tablero {

    private static ArrayList<Casilla> casillas;
    private static Casilla carcel;

    public Tablero() {
        inicializar();
    }

    private static void inicializar() {
        casillas = new ArrayList();
        casillas.add(new Casilla(0, 0, TipoCasilla.SALIDA));
        casillas.add(new Casilla(1, 450, TipoCasilla.CALLE,
                new TituloPropiedad("Calle San Jerónimo", 100, 18.3f, 750, 658)));
        casillas.add(new Casilla(2, 350, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Periodistas", 50, 8.2f, 400, 500)));
        casillas.add(new Casilla(3, 0, TipoCasilla.IMPUESTO));
        casillas.add(new Casilla(4, 650, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Ángel Ganivet", 60, 13.1f, 150, 400)));
        casillas.add(new Casilla(5, 0, TipoCasilla.JUEZ));
        casillas.add(new Casilla(6, 400, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Cuesta Gomerez", 60, 10, 600, 700)));
        casillas.add(new Casilla(7, 0, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(8, 550, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Pedro Antonio", 55, 10.2f, 300, 500)));
        casillas.add(new Casilla(9, 400, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Reyes Catolicos", 100, 17.3f, 400, 600)));
        casillas.add(new Casilla(10, 0, TipoCasilla.PARKING));
        casillas.add(new Casilla(11, 450, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Gran vía", 87, 8.7f, 0, 250)));
        casillas.add(new Casilla(12, 0, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(13, 500, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Camino de Ronda ", 55, 10.1f, 0, 500)));
        casillas.add(new Casilla(14, 600, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Elvira", 100, 12.2f, 350, 700)));
        casillas.add(new Casilla(15, 0, TipoCasilla.CARCEL));
        casillas.add(new Casilla(16, 650, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Fuente Nueva", 100, 19.8f, 800, 750)));
        casillas.add(new Casilla(17, 0, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(18, 750, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Carrera del Darro", 53, 10.0f, 0, 300)));
        casillas.add(new Casilla(19, 300, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Recogidas", 50, 15.2f, 0, 1000)));
        
        carcel = casillas.get(2);
    }

    @Override
    public String toString() {
        String tablero = "";
        for (Casilla c : casillas) {
            tablero += c.toString();
        }
        return "Tablero{" + "casillas=" + casillas + ", carcel=" + carcel + tablero + '}';
    }

}
