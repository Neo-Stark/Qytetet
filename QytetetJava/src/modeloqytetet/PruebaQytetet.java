package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author fran98
 */
public class PruebaQytetet {
    public static void main(String[] args) {
        String[] nombres = {"Fran","Jose"};
        Qytetet juego = Qytetet.getInstance();
        System.out.println(juego.getTablero().toString());
    }
//    private static ArrayList<Sorpresa> mayorQue0()
//    {
//        ArrayList<Sorpresa> m = new ArrayList();
//        for ( Sorpresa s : mazo)
//            if (s.getValor() > 0 )
//                m.add(s);
//        
//        return m;
//    }
//    private static ArrayList<Sorpresa> irACasilla()
//    {
//        ArrayList<Sorpresa> m = new ArrayList();
//        for ( Sorpresa s : mazo)
//            if (s.getTipo() == TipoSorpresa.IRACASILLA )
//                m.add(s);
//        
//        return m;
//    }
//    private static ArrayList<Sorpresa> getSorpresa(TipoSorpresa sorpresa)
//    {
//        ArrayList<Sorpresa> m = new ArrayList();
//        for ( Sorpresa s : mazo)
//            if (s.getTipo() == sorpresa )
//                m.add(s);
//        
//        return m;
//    }   
}
