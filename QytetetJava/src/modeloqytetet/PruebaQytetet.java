package modeloqytetet;

import java.util.ArrayList;
import InterfazTextualQytetet.*;

/**
 *
 * @author fran98
 */
public class PruebaQytetet {
    public static void main(String[] args) {
//        ArrayList<String> nombres = new ArrayList<>();
//        nombres.add("Fran");
//        nombres.add("Jose");
//        nombres.add("Juan");
//        nombres.add("Ivan");
//        nombres.add("Iv");
//        Qytetet juego = Qytetet.getInstance();
//        juego.inicicializarJuego(nombres);
//        System.out.println(juego.getTablero().toString());
//        System.out.println(juego.getJugadorActual().toString());
        ControladorQytetet juego = new ControladorQytetet();
        juego.inicializacionJuego();
        juego.desarrolloJuego();
        
    }
}