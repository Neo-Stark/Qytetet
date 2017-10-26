package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;

public class Qytetet {
    
    public int MAX_JUGADORES = 4;
    int MAX_CARTAS = 10;
    int MAX_CASILLAS = 20;
    int PRECIO_LIBERTAD = 200;
    int SALDO_SALIDA = 1000;
//    private static final Qytetet instance = new Qytetet();
    private Dado dado;
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo = new ArrayList();
    private ArrayList<Jugador> jugadores = new ArrayList();
    private Jugador jugadorActual;
     Tablero tablero;
    
    Qytetet(String[] nombres){
        inicializarTablero();
        inicializarCartasSorpresa();
        inicializarJugadores(nombres);
    }

    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    
    private void inicializarCartasSorpresa(){
        mazo.add(new Sorpresa("Se han limpiado tus delitos de la base de datos de la policia. Sales de la cárcel",
        0, TipoSorpresa.SALIRCARCEL));
      mazo.add( new Sorpresa("Te hemos pillado hackeando los servidores de la UGR, vas directamente a la carcel",
        tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA ));
      mazo.add( new Sorpresa("Has ganado un viaje un viaje a Las Vegas, pero lo vendes porque prefieres seguir programando",
        800, TipoSorpresa.PAGARCOBRAR ));
      mazo.add( new Sorpresa("Decides ir de compras, vas en metro a Recogidas", 19, TipoSorpresa.IRACASILLA));
      mazo.add( new Sorpresa("Un huracán ha arrasado con todos tus hoteles, pagas 300 por cada casa y hotel.", 100, TipoSorpresa.PORCASAHOTEL));
      mazo.add( new Sorpresa("Un magnate del petroleo ha decidido invertir en tus propiedades, "
        +"obtienes 400 por cada casa y hotel", 400, TipoSorpresa.PORCASAHOTEL));
      mazo.add( new Sorpresa("Decides regalar un año de suscripción de Netflix a cada jugador, "
        +"pagas 150 por cada jugador", 150, TipoSorpresa.PORJUGADOR));
      mazo.add( new Sorpresa("Has aprobado la carrera, cada jugador te da 200 por"
        +"tu gran hazaña", 200, TipoSorpresa.PORJUGADOR));
      mazo.add( new Sorpresa("Tienes ganas de salir de fiesta, vas a Pedro Antonio", 8, TipoSorpresa.IRACASILLA));
      mazo.add( new Sorpresa("Anoche te pasaste con la juerga, te has dejado 750 en una noche", 750, TipoSorpresa.PAGARCOBRAR));
        Collections.shuffle(mazo);
    }
    
    private void inicializarJugadores(String[] nombres){
        for(String n:nombres)
            jugadores.add(new Jugador(n, tablero.obtenerCasillaNumero(0)));
    }
    
    private void inicializarTablero(){
        tablero = new Tablero();
    }
    
    public static Qytetet getInstance(){
        return instance;
    }
        
}
