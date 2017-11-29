/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazTextualQytetet;

import modeloqytetet.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

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
        vista.mostrar("Pulsa una tecla para continuar...");
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
        while (true) {
            this.estadoActual();
            if (jugador.isEncarcelado()) {
                this.intentarSalirCarcel();
            }
            if (!jugador.isEncarcelado()) {
                boolean tienePropietario = juego.jugar();
                vista.mostrar("Valor del dado: " + juego.getValorDado());
                vista.mostrar("Has caido en: " + casilla.toString());
                this.pausa();
                this.estadoActual();
                
                if (!jugador.isEncarcelado()) {
                    switch (casilla.getTipo()) {
                        case SORPRESA:
                            vista.mostrar(juego.getCartaActual().toString() + "\nAplicando Sopresa...");
                            this.espera();
                            juego.aplicarSorpresa();
                            break;
                        case CALLE:
                            if (tienePropietario) {
                                vista.mostrar("\nPagando alquiler...");
                                this.espera();
                            } else {
                                if (vista.elegirQuieroComprar() == 0) {
                                    if (juego.comprarTituloPropiedad()) {
                                        vista.mostrar("\nHas comprado el Titulo de propiedad: "
                                                + casilla.getTitulo().getNombre());
                                    } else {
                                        vista.mostrar("\nNo puedes comprar la propiedad");
                                    }
                                }
                            }
                            break;
                    }
                    this.estadoActual();
                }
            }
            if (jugador.tengoPropiedades() && !jugador.isEncarcelado()) {
                gestionInmobiliaria();
            }
            this.estadoActual();
            pasaTurno();
        }
    }
    
    private void gestionInmobiliaria() {
        int opcion;
        do {
            opcion = vista.menuGestionInmobiliaria();
            ArrayList<Casilla> noHipotecadas = juego.propiedadesHipotecadasJugador(false);
            ArrayList<Casilla> Hipotecadas = juego.propiedadesHipotecadasJugador(true);
            Casilla modCasilla;
            switch (opcion) {
                case 0:
                    break;
                case 1:
                    modCasilla = elegirPropiedad(noHipotecadas);
                    if (juego.edificarCasa(modCasilla)) {
                        vista.mostrar("Has edificado una casa en "
                                + modCasilla.getTitulo().getNombre());
                    } else {
                        vista.mostrar("No has podido edificar la casa.");
                    }
                    break;
                case 2:
                    modCasilla = elegirPropiedad(noHipotecadas);
                    if (juego.edificarHotel(modCasilla)) {
                        vista.mostrar("Has edificado un hotel en "
                                + modCasilla.getTitulo().getNombre());
                    } else {
                        vista.mostrar("No has podido edificar el hotel.");
                    }
                    break;
                case 3:
                    modCasilla = elegirPropiedad(noHipotecadas);
                    if (juego.venderPropiedad(modCasilla)) {
                        vista.mostrar("Has vendido la propiedad "
                                + modCasilla.getTitulo().getNombre());
                    } else {
                        vista.mostrar("No has podido vender la propiedad");
                    }
                    break;
                case 4:
                    modCasilla = elegirPropiedad(noHipotecadas);
                    if (juego.hipotecarPropiedad(modCasilla)) {
                        vista.mostrar("Has hipotecado la propiedad "
                                + modCasilla.getTitulo().getNombre());
                    } else {
                        vista.mostrar("No has podido hipotecar la propiedad");
                    }
                    break;
                case 5:
                    modCasilla = elegirPropiedad(Hipotecadas);
                    if (juego.cancelarHipoteca(modCasilla)) {
                        vista.mostrar("Has cancelado la hipoteca de "
                                + modCasilla.getTitulo().getNombre());
                    } else {
                        vista.mostrar("No has podido cancelar la hipoteca");
                    }
                    break;
                default:
                    break;
            }
            estadoActual();
        } while (opcion != 0);
    }
    
    private void pasaTurno() {
        vista.mostrar("Turno siguiente jugador...");
        juego.siguienteJugador();
        this.pausa();
    }
    
    private Boolean bancarrota() {
        return jugador.getSaldo() < 0;
    }
    
    private void gameOver() {
        int pos = 1;
        vista.mostrar("Game Over! Jugador " + jugador.getNombre() + " en bancarrota");
        vista.mostrar("Ranking segun el capital: ");
        Map<String, Integer> map = juego.obtenerRanking();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            vista.mostrar(pos + ". " + entry.getKey() + " -- " + entry.getValue());
            pos++;
        }
        System.exit(0);
    }
    
    private void estadoActual() {
        vista.mostrar("\nJugador actual: " + jugador.toString()
                + "\nCasilla actual: " + casilla.getNumeroCasilla()
                + "\nEncarcelado: " + jugador.isEncarcelado()
                + "\nSaldo actual: " + jugador.getSaldo());
        if (this.bancarrota()) {
            this.gameOver();
        }
        this.pausa();
    }
    
    private void espera() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Continuando...");
        }
    }
    
    private void intentarSalirCarcel() {
        boolean libre = false;
        int eleccion = vista.menuSalirCarcel();
        
        if (eleccion == 0) {
            libre = juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
        } else {
            libre = juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
        }
        if (libre) {
            vista.mostrar("Hurra!! Has salido de la carcel");
        } else {
            vista.mostrar("Lo siento. Sigues en la carcel");
        }
        this.pausa();
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
