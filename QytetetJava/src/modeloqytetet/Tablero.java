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
        casillas.add(new otraCasilla(0, 0, TipoCasilla.SALIDA));
        casillas.add(new Calle(1, 450, TipoCasilla.CALLE,
                new TituloPropiedad("Calle San Jerónimo", 100, 0.7f, 750, 350)));
        casillas.add(new Calle(2, 350, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Periodistas", 50, 0.9f, 400, 500)));
        casillas.add(new otraCasilla(3, 500, TipoCasilla.IMPUESTO));
        casillas.add(new Calle(4, 650, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Ángel Ganivet", 300, 1.1f, 150, 400)));
        casillas.add(new otraCasilla(5, 0, TipoCasilla.JUEZ));
        casillas.add(new Calle(6, 400, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Cuesta Gomerez", 350, 1, 600, 700)));
        casillas.add(new otraCasilla(7, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(8, 550, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Pedro Antonio", 55, 1.2f, 300, 500)));
        casillas.add(new Calle(9, 400, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Reyes Catolicos", 100, 1.35f, 400, 600)));
        casillas.add(new otraCasilla(10, 0, TipoCasilla.PARKING));
        casillas.add(new Calle(11, 450, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Gran vía", 87, 1.7f, 800, 550)));
        casillas.add(new otraCasilla(12, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(13, 500, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Camino de Ronda ", 55, 1.5f, 750, 500)));
        casillas.add(new Calle(14, 600, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Elvira", 65, 1.2f, 350, 500)));
        casillas.add(new otraCasilla(15, 0, TipoCasilla.CARCEL));
        casillas.add(new Calle(16, 650, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Fuente Nueva", 75, 0.5f, 800, 650)));
        casillas.add(new otraCasilla(17, 0, TipoCasilla.SORPRESA));
        casillas.add(new Calle(18, 750, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Carrera del Darro", 90, 1f, 900, 700)));
        casillas.add(new Calle(19, 300, TipoCasilla.CALLE,
                new TituloPropiedad("Calle Recogidas", 100, 2f, 1000, 750)));

        carcel = casillas.get(15);
    }

    Casilla obtenerCasillaNumero(int numeroCasilla) {
        return casillas.get(numeroCasilla);
    }

    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento) {
        int Nueva_casilla = (casilla.getNumeroCasilla() + desplazamiento) % 20;
        return casillas.get(Nueva_casilla);
    }

    Casilla getCarcel() {
        return carcel;
    }

    boolean esCasillaCarcel(int numeroCasilla) {
        return (numeroCasilla == carcel.getNumeroCasilla());
    }

    @Override
    public String toString() {
        String tablero = "";
        for (Casilla c : casillas) {
            tablero += '\n' + c.toString();
        }
        return "Tablero:" + tablero
                + "\n\tNumero total de casillas=" + casillas.size();
    }

}
