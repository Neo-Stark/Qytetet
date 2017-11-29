/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazTextualQytetet;

import modeloqytetet.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author fran
 */
public class ControladorQytetet {

    private static final Scanner in = new Scanner(System.in);
    private Qytetet juego;
    private Jugador jugador;
    private Casilla casilla;
    private VistaTextualQytetet vista;

    private void pausa() {
        vista.mostrar("Pulsa una tecla para continuar");
        in.nextLine();
    }

    public void inicializacionJuego() {
        juego = Qytetet.getInstance();
        ArrayList<String> nombres = new ArrayList();
        nombres = vista.obtenerNombreJugadores();
        juego.inicicializarJuego(nombres);
        jugador = juego.getJugadorActual();
        casilla = juego.getJugadorActual().getCasillaActual();

        vista.mostrar("Tablero: " + juego.getTablero().toString()
                + "\nComienza el jugador: " + jugador.toString()
                + "\nCasilla actual: " + casilla.toString());
        this.pausa();
    }

    public void desarrolloJuego() {
        vista.mostrar("Jugador actual: " + jugador.toString()
                + " Casilla actual" + casilla.toString());
        if (jugador.isEncarcelado()) {
            boolean libre = false;
            int eleccion = vista.menuSalirCarcel();

            if (eleccion == 0) {
                libre = juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
            } else {
                libre = juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
            }
            if (libre) {
                vista.mostrar("Has salido de la carcel");
            } else {
                vista.mostrar("Sigues en la carcel");
            }
            
            this.pausa();
        }
        if (jugador.isEncarcelado()) {
            juego.siguienteJugador();
        } else {
            juego.jugar()
        }
    }

    public Casilla elegirPropiedad(ArrayList<Casilla> propiedades) {
        //este metodo toma una lista de propiedades y genera una lista de strings, con el numero y nombre de las propiedades
        //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion;
        ArrayList<String> listaPropiedades = new ArrayList();
        for (Casilla casilla : propiedades) {
            listaPropiedades.add("\t" + casilla.getNumeroCasilla() + "\t" + casilla.getTitulo().getNombre());
        }
        seleccion = vista.menuElegirPropiedad(listaPropiedades);
        return propiedades.get(seleccion);
    }

}
