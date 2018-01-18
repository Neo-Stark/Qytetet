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

    private static final Scanner IN = new Scanner(System.in);
    private Qytetet juego;
    private Jugador jugador;
    private Casilla casilla;
    private final VistaTextualQytetet vista;

    public ControladorQytetet() {
        vista = new VistaTextualQytetet();
    }

    private void pausa() {
        vista.mostrar("Pulsa una tecla para continuar...");
        IN.nextLine();
    }

    public void inicializacionJuego() {
        juego = Qytetet.getInstance();
        ArrayList<String> nombres = vista.obtenerNombreJugadores();
        juego.inicicializarJuego(nombres);
        jugador = juego.getJugadorActual();
        casilla = juego.getJugadorActual().getCasillaActual();

        vista.mostrar("Tablero: " + juego.getTablero().toString()
                + "\nComienza el jugador: " + jugador.toString());
//                + "\nCasilla actual: " + casilla.toString());
        this.pausa();
    }

    public void desarrolloJuego() {
        while (true) {
            this.estadoActual();
            if (jugador.isEncarcelado()) {
                this.intentarSalirCarcel();
            }
            if (!jugador.isEncarcelado()) {
                vista.mostrar("Tirando dado...");
                espera();
                boolean tienePropietario = juego.jugar();
                casilla = jugador.getCasillaActual();
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
                            if (juego.getCartaActual().getTipo() == TipoSorpresa.IRACASILLA) {
                                casilla = jugador.getCasillaActual();
                                vista.mostrar("Nueva " + casilla.toString());
                                if (casilla.getTipo() == TipoCasilla.CARCEL) {
                                    break;
                                }
                            } else {
                                jugador = juego.getJugadorActual();
                                this.estadoActual();
                                break;
                            }//Si la sorpresa lleva al jugador a una calle, entrara en case CALLE
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
                            this.estadoActual();
                            break;
                    }
                } else {
                    vista.mostrar("Has caido en la casilla del juez,"
                            + "vas directamente a la carcel.");
                    pasaTurno();
                }
            }
            if (!jugador.isEncarcelado()){
            if (jugador.tengoPropiedades()){
                gestionInmobiliaria();
            } else {
                vista.mostrar("No tienes propiedades");
            }
            }else{
                vista.mostrar("Estas en la carcel");
            }
//            this.estadoActual();
            pasaTurno();
        }
    }

    private void gestionInmobiliaria() {
        int opcion;
        do {
            opcion = vista.menuGestionInmobiliaria();
            if (jugador.tengoPropiedades() || opcion == 0) {
                ArrayList<Casilla> noHipotecadas = juego.propiedadesHipotecadasJugador(false);
                ArrayList<Casilla> Hipotecadas = juego.propiedadesHipotecadasJugador(true);
                Casilla modCasilla;
                switch (opcion) {
                    case 0:
                        break;
                    case 1:
                        if (!noHipotecadas.isEmpty()) {
                            modCasilla = elegirPropiedad(noHipotecadas);
                            if (!(modCasilla == null)) {
                                if (juego.edificarCasa(modCasilla)) {
                                    vista.mostrar("Has edificado una casa en "
                                            + modCasilla.getTitulo().getNombre());
                                } else {
                                    vista.mostrar("No has podido edificar la casa.");
                                }
                            }
                        } else {
                            vista.mostrar("Todas tus propiedades estan hipotecadas");
                            pausa();
                        }
                        break;
                    case 2:
                        if (!noHipotecadas.isEmpty()) {
                            modCasilla = elegirPropiedad(noHipotecadas);
                            if (!(modCasilla == null)) {
                                if (juego.edificarHotel(modCasilla)) {
                                    vista.mostrar("Has edificado un hotel en "
                                            + modCasilla.getTitulo().getNombre());
                                } else {
                                    vista.mostrar("No has podido edificar el hotel.");
                                }
                            }
                        } else {
                            vista.mostrar("Todas tus propiedades estan hipotecadas");
                            pausa();
                        }
                        break;
                    case 3:
                        if (!noHipotecadas.isEmpty()) {
                            modCasilla = elegirPropiedad(noHipotecadas);
                            if (!(modCasilla == null)) {
                                if (juego.venderPropiedad(modCasilla)) {
                                    vista.mostrar("Has vendido la propiedad "
                                            + modCasilla.getTitulo().getNombre());
                                } else {
                                    vista.mostrar("No has podido vender la propiedad");
                                }
                            }
                        } else {
                            vista.mostrar("Todas tus propiedades estan hipotecadas");
                            pausa();
                        }
                        break;
                    case 4:
                        if (!noHipotecadas.isEmpty()) {
                            modCasilla = elegirPropiedad(noHipotecadas);
                            if (!(modCasilla == null)) {
                                if (juego.hipotecarPropiedad(modCasilla)) {
                                    vista.mostrar("Has hipotecado la propiedad "
                                            + modCasilla.getTitulo().getNombre());
                                } else {
                                    vista.mostrar("No has podido hipotecar la propiedad");
                                }
                            }
                        } else {
                            vista.mostrar("Todas tus propiedades estan hipotecadas");
                            pausa();
                        }
                        break;
                    case 5:
                        if (!Hipotecadas.isEmpty()) {
                            modCasilla = elegirPropiedad(Hipotecadas);
                            if (!(modCasilla == null)) {
                                if (juego.cancelarHipoteca(modCasilla)) {
                                    vista.mostrar("Has cancelado la hipoteca de "
                                            + modCasilla.getTitulo().getNombre());
                                } else {
                                    vista.mostrar("No has podido cancelar la hipoteca");
                                }
                            }
                        } else {
                            vista.mostrar("No tienes propiedades hipotecadas");
                            pausa();
                        }
                        break;
                    default:
                        break;
                }
                estadoActual();
            } else {
                vista.mostrar("No te quedan propiedades");
                pausa();
            }
        } while (opcion != 0);
    }

    private void pasaTurno() {
        vista.mostrar("Turno siguiente jugador...");
        juego.siguienteJugador();
        jugador = juego.getJugadorActual();
        casilla = jugador.getCasillaActual();
        this.espera();
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
        vista.mostrar("**-JUGADOR-**"
                + "\nNombre: " + jugador
                + "\nCasilla actual: " + casilla.getNumeroCasilla()
                + "\nEncarcelado: " + jugador.isEncarcelado()
                + "\nCarta libertad: " + jugador.tengoCartaLibertad()
                + "\nSaldo actual: " + jugador.getSaldo()
        );
        if (this.bancarrota()) {
            this.gameOver();
        }
        this.pausa();
    }

    private void espera() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Continuando...");
        }
    }

    private void intentarSalirCarcel() {
        boolean libre = false;
        int eleccion = vista.menuSalirCarcel();

        if (eleccion == 0) {
            libre = juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
            vista.mostrar("Valor dado: " + juego.getValorDado());
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
        if (seleccion == listaPropiedades.size()) {
            return null;
        } else {
            return propiedades.get(seleccion);
        }
    }

}
