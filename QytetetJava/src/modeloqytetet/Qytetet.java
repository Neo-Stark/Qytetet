package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Qytetet {

    public int MAX_JUGADORES = 4;
    public int MAX_CARTAS = 10;
    public int MAX_CASILLAS = 20;
    public int PRECIO_LIBERTAD = 200;
    public int SALDO_SALIDA = 1000;
    private static final Qytetet instance = new Qytetet();
    private Dado dado;
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo = new ArrayList();
    private ArrayList<Jugador> jugadores = new ArrayList();
    private Jugador jugadorActual;
    private Tablero tablero;

    private Qytetet() {

    }

    public Sorpresa getCartaActual() {
        return cartaActual;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    private void inicializarCartasSorpresa() {
        mazo.add(new Sorpresa("Se han limpiado tus delitos de la base de datos de la policia. Sales de la cárcel",
                0, TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa("Te hemos pillado hackeando los servidores de la UGR, vas directamente a la carcel",
                tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Has ganado un viaje un viaje a Las Vegas, pero lo vendes porque prefieres seguir programando",
                800, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Decides ir de compras, vas en metro a Recogidas", 19, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Un huracán ha arrasado con todos tus hoteles, pagas 300 por cada casa y hotel.", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Un magnate del petroleo ha decidido invertir en tus propiedades, "
                + "obtienes 400 por cada casa y hotel", 400, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Decides regalar un año de suscripción de Netflix a cada jugador, "
                + "pagas 150 por cada jugador", 150, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Has aprobado la carrera, cada jugador te da 200 por"
                + "tu gran hazaña", 200, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Tienes ganas de salir de fiesta, vas a Pedro Antonio", 8, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Anoche te pasaste con la juerga, te has dejado 750 en una noche", 750, TipoSorpresa.PAGARCOBRAR));
        Collections.shuffle(mazo);
    }

    private void inicializarJugadores(ArrayList<String> nombres) {
        for (String n : nombres) {
            jugadores.add(new Jugador(n));
        }
    }

    private void inicializarTablero() {
        tablero = new Tablero();
    }

    public static Qytetet getInstance() {
        return instance;
    }

    public void siguienteJugador() {
        int jugadorSiguiente = (jugadores.indexOf(this.jugadorActual) + 1) % MAX_JUGADORES;
        jugadorActual = jugadores.get(jugadorSiguiente);
    }

    void salidaJugadores() {
        for (Jugador jugador : this.jugadores) {
            jugador.actualizarPosicion(tablero.obtenerCasillaNumero(0));
            jugador.modificarSaldo(7500);
        }
        jugadorActual = jugadores.get((int) (Math.random() * MAX_JUGADORES) + 1);

    }

    ArrayList propiedadesHipotecadasJugador(boolean hipotecadas) {
        ArrayList<TituloPropiedad> propiedades = new ArrayList();
        propiedades = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
        return propiedades;

    }

    public void inicicializarJuego(ArrayList<String> nombres) {
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
        inicializarTablero();
        salidaJugadores();
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public boolean comprarTituloPropiedad() {
        boolean puedoComprar = jugadorActual.comprarTitulo();
        return puedoComprar;
    }

    public boolean edificarCasa(Casilla casilla) {
        boolean puedoEdificar = false;
        boolean sePuedeEdificar = false;
        if (casilla.soyEdificable()) {
            sePuedeEdificar = casilla.sePuedeEdificarCasa();
            if (sePuedeEdificar) {
                puedoEdificar = jugadorActual.puedoEdificar(casilla);
                if (puedoEdificar) {
                    jugadorActual.modificarSaldo(-casilla.edificarCasa());
                }
            }
        }
        return puedoEdificar;
    }

    public boolean edificarHotel(Casilla casilla) {
        boolean puedoEdificar = false;
        boolean sePuedeEdificar = false;
        if (casilla.soyEdificable()) {
            sePuedeEdificar = casilla.sePuedeEdificarHotel();
            if (sePuedeEdificar) {
                puedoEdificar = jugadorActual.puedoEdificar(casilla);
                if (puedoEdificar) {
                    jugadorActual.modificarSaldo(-casilla.edificarHotel());
                }
            }
        }
        return puedoEdificar;
        
    }

    public boolean hipotecarPropiedad(Casilla casilla) {
        boolean puedoHipotecar = false;
        if (casilla.soyEdificable() && !casilla.estaHipotecada()) {
            puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
            if (puedoHipotecar) {
                jugadorActual.modificarSaldo(casilla.hipotecar());
            }
        }
        return puedoHipotecar;
    }
    
    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = false;
        if (casilla.soyEdificable()){
            puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            if (puedoVender){
                jugadorActual.venderPropiedad(casilla);
            }
        }
        return puedoVender;
    }
    
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        
        if (cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR)
            jugadorActual.modificarSaldo(cartaActual.getValor());
        else if (cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
            boolean esCarcel = tablero.esCasillaCarcel(cartaActual.getValor());
            if (esCarcel){
                encarcelarJugador();
            }
            else {
                Casilla nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.getValor());
                tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
            }
        }
        else if (cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
            jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
        }
        else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
            for (Jugador jugador : jugadores){
                if (jugador != jugadorActual){
                    jugador.modificarSaldo(cartaActual.getValor());
                    jugadorActual.modificarSaldo(-cartaActual.getValor());
                }
            }
        }
        
        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
        }
        else
            mazo.add(cartaActual);
        
        return tienePropietario;
    }
    
    private void encarcelarJugador(){
        if (!jugadorActual.tengoCartaLibertad()){
            jugadorActual.irACarcel(tablero.getCarcel());
        }
        else{
            mazo.add(jugadorActual.devolverCartaLibertad());
        }
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo)
    {
        boolean libre = false;
        if (metodo == MetodoSalirCarcel.TIRANDODADO){
            int valorDado = dado.tirar();
            libre = valorDado>4;
        }
        else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            libre = jugadorActual.pagarLibertad(-PRECIO_LIBERTAD);
        }
        if (libre){
            jugadorActual.setEncarcelado(false);
        }
        return libre;
    }
    public boolean jugar(){
        boolean tienePropietario = false;
        int valorDado = dado.tirar();
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        
        if (!nuevaCasilla.soyEdificable()){
            if (nuevaCasilla.getTipo() == TipoCasilla.JUEZ){
                encarcelarJugador();
            }
            else if (nuevaCasilla.getTipo() == TipoCasilla.SORPRESA){
                cartaActual = mazo.get(0);
                mazo.remove(0);
            }
        }
        return tienePropietario;
    }
    
    public Map obtenerRanking(){
        Map<Integer, String> ranking = new TreeMap(java.util.Collections.reverseOrder());
        for (Jugador jugador : jugadores){
            int capital = jugador.obtenerCapital();
            ranking.put(capital, jugador.getNombre());
        }
        return ranking;
    }
}
