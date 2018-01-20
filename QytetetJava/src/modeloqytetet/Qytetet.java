package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Qytetet {

    public final int MAX_JUGADORES;
    public final int MAX_CARTAS;
    public final int MAX_CASILLAS;
    public final int PRECIO_LIBERTAD;
    public final int SALDO_SALIDA;
    private static final Qytetet instance = new Qytetet();
    private Dado dado;
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo;
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Tablero tablero;
    private int valorDado;

    private Qytetet() {
        this.MAX_JUGADORES = 4;
        this.MAX_CARTAS = 10;
        this.MAX_CASILLAS = 20;
        this.SALDO_SALIDA = 1000;
        this.PRECIO_LIBERTAD = 200;
        this.mazo = new ArrayList();
        this.jugadores = new ArrayList();
        dado = Dado.getInstancia();
        cartaActual = null;
        valorDado = 0;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public Sorpresa getCartaActual() {
        return cartaActual;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public int getValorDado() {
        return valorDado;
    }

    private void inicializarCartasSorpresa() {
        mazo.add(new Sorpresa("Bonita cuenta en Suiza, ahora eres un especulador", 5000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa("Te hemos pillado hackeando los servidores de la UGR, vas directamente a la carcel",
                tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Decides ir de compras, vas en metro a Recogidas", 19, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Se han limpiado tus delitos de la base de datos de la policia. Sales de la cárcel",
                0, TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa("Has ganado un viaje un viaje a Las Vegas, pero lo vendes porque prefieres seguir programando",
                800, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Un huracán ha arrasado con todos tus hoteles, pagas 300 por cada casa y hotel.", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Un magnate del petroleo ha decidido invertir en tus propiedades, "
                + "obtienes 400 por cada casa y hotel", 400, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Decides regalar un año de suscripción de Netflix a cada jugador, "
                + "pagas 150 por cada jugador", 150, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Has aprobado la carrera, cada jugador te da 200 por"
                + "tu gran hazaña", -200, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Tienes ganas de salir de fiesta, vas a Pedro Antonio", 8, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Anoche te pasaste con la juerga, te has dejado 750 en una noche", 750, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Te ha llegado un sobre con una tarjeta negra, eres un nuevo especulador", 3000, TipoSorpresa.CONVERTIRME));
//        Collections.shuffle(mazo);
    }

    private void inicializarJugadores(ArrayList<String> nombres) {
        nombres.forEach((n) -> {
            jugadores.add(new Jugador(n));
        });
    }

    private void inicializarTablero() {
        tablero = new Tablero();
    }

    public static Qytetet getInstance() {
        return instance;
    }

    public void siguienteJugador() {
        int jugadorSiguiente = (jugadores.indexOf(this.jugadorActual) + 1) % jugadores.size();
        jugadorActual = jugadores.get(jugadorSiguiente);
    }

    void salidaJugadores() {
        for (Jugador jugador : this.jugadores) {
            jugador.setCasillaActual(tablero.obtenerCasillaNumero(0));
            jugador.modificarSaldo(7500);
        }
//        int i = new Random().nextInt(jugadores.size());
//        jugadorActual = jugadores.get(i);
        jugadorActual = jugadores.get((int) (Math.random() * jugadores.size()));

    }

    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas) {
        ArrayList<Casilla> casillas = new ArrayList<>();
        for (TituloPropiedad propiedad : jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas)) {
            casillas.add(propiedad.getCasilla());
        }
        return casillas;
    }

    public void inicicializarJuego(ArrayList<String> nombres) {
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
        salidaJugadores();
    }

    public boolean comprarTituloPropiedad() {
        boolean puedoComprar = jugadorActual.comprarTitulo();
        return puedoComprar;
    }

    public boolean edificarCasa(Casilla casilla) {
        boolean puedoEdificar = false;
        boolean sePuedeEdificar = false;
        if (casilla.soyEdificable()) {
            sePuedeEdificar = ((Calle)casilla).sePuedeEdificarCasa(jugadorActual.getFactorEspeculador());
            if (sePuedeEdificar) {
                puedoEdificar = jugadorActual.puedoEdificar(casilla);
                if (puedoEdificar) {
                    jugadorActual.modificarSaldo(-((Calle)casilla).edificarCasa());
                }
            }
        }
        return puedoEdificar;
    }

    public boolean edificarHotel(Casilla casilla) {
        boolean puedoEdificar = false;
        boolean sePuedeEdificar = false;
        if (casilla.soyEdificable()) {
            sePuedeEdificar = ((Calle)casilla).sePuedeEdificarHotel(jugadorActual.getFactorEspeculador());
            if (sePuedeEdificar) {
                puedoEdificar = jugadorActual.puedoEdificar(casilla);
                if (puedoEdificar) {
                    jugadorActual.modificarSaldo(-((Calle)casilla).edificarHotel());
                }
            }
        }
        return puedoEdificar;

    }

    public boolean hipotecarPropiedad(Casilla casilla) {
        boolean puedoHipotecar = false;
        if (((Calle)casilla).soyEdificable() && !((Calle)casilla).estaHipotecada()) {
            puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
            if (puedoHipotecar) {
                jugadorActual.modificarSaldo(((Calle)casilla).hipotecar());
            }
        }
        return puedoHipotecar;
    }

    public boolean cancelarHipoteca(Casilla casilla) {
        boolean puedoCancelar = false;
        if (((Calle)casilla).estaHipotecada()) {
            puedoCancelar = jugadorActual.puedoHipotecar(casilla);
            if (puedoCancelar) {
                jugadorActual.modificarSaldo(-((Calle)casilla).cancelarHipoteca());
            }
        }
        return puedoCancelar;
    }

    public boolean venderPropiedad(Casilla casilla) {
        boolean puedoVender = false;
        if (casilla.soyEdificable()) {
            puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            if (puedoVender) {
                jugadorActual.venderPropiedad(casilla);
            }
        }
        return puedoVender;
    }

    public boolean aplicarSorpresa() {
        boolean tienePropietario = false;
        int valor = cartaActual.getValor();

        if (null != cartaActual.getTipo()) {
            switch (cartaActual.getTipo()) {
                case PAGARCOBRAR:
                    jugadorActual.modificarSaldo(valor);
                    break;
                case IRACASILLA:
                    boolean esCarcel = tablero.esCasillaCarcel(valor);
                    if (esCarcel) {
                        encarcelarJugador();
                    } else {
                        Casilla nuevaCasilla = tablero.obtenerCasillaNumero(valor);
                        tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
                    }
                    break;
                case PORCASAHOTEL:
                    jugadorActual.pagarCobrarPorCasaYHotel(valor);
                    break;
                case PORJUGADOR:
                    for (Jugador jugador : jugadores) {
                        if (jugador != jugadorActual) {
                            jugador.modificarSaldo(valor);
                            jugadorActual.modificarSaldo(-valor);
                        }
                    }
                    break;
                case CONVERTIRME:
                    int index = jugadores.indexOf(jugadorActual);
                    jugadorActual = jugadorActual.convertirme(valor);
                    jugadores.set(index, jugadorActual);
                    break;
                default:
                    break;
            }
        }

        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL) {
            jugadorActual.setCartaLibertad(cartaActual);
        } else {
            mazo.add(cartaActual);
        }

        return tienePropietario;
    }

    private void encarcelarJugador() {
        if (!jugadorActual.tengoCartaLibertad()) {
            jugadorActual.irACarcel(tablero.getCarcel());
        } else {
            mazo.add(jugadorActual.devolverCartaLibertad());
        }
    }

    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo) {
        boolean libre = false;
        if (metodo == MetodoSalirCarcel.TIRANDODADO) {
            valorDado = dado.tirar();
            libre = valorDado > 4;
        } else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD) {
            libre = jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
        }
        if (libre) {
            jugadorActual.setEncarcelado(false);
        }
        return libre;
    }

    public boolean jugar() {
        boolean tienePropietario = false;
        valorDado = dado.tirar();
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, 7);
        tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);

        if (!nuevaCasilla.soyEdificable()) {
            if (nuevaCasilla.getTipo() == TipoCasilla.JUEZ) {
                encarcelarJugador();
            } else if (nuevaCasilla.getTipo() == TipoCasilla.SORPRESA) {
                cartaActual = mazo.get(0);
                mazo.remove(0);
            }
        }
        return tienePropietario;
    }

    public Map obtenerRanking() {
        Map<String, Integer> ranking = new LinkedHashMap<>();
        ArrayList<Jugador> listaOrdenada = new ArrayList(jugadores);
        listaOrdenada.sort((p1, p2) -> p2.obtenerCapital() - p1.obtenerCapital());
        listaOrdenada.forEach((jugador)
                -> ranking.put(jugador.getNombre(), jugador.obtenerCapital()));
        return ranking;
    }
}
