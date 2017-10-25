/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;


/**
 *
 * @author fran98
 */
public class PruebaQytetet {
    private static ArrayList<Sorpresa> mazo = new ArrayList();
    
    private static void inicializarSorpresas()
    {
        mazo.add(new Sorpresa ("Te hemos pillado hackeando los servidores de la UGR,"
                + " vas directamente a la carcel", 15, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Has ganado un viaje a un spa con todos los gastos pagados"
                + " en la calle Recogidas", 5, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Se han limpiado tus delitos de la base de datos de la policia."
                + " Sales de la cárcel", 0, TipoSorpresa.SALIRCARCEL));
    }
    public static void main(String[] args) {
        PruebaQytetet.inicializarSorpresas();
        for ( Sorpresa s : mazo)
        {
            System.out.println(s.toString());
            System.out.println();
        }
        for ( Sorpresa s : mayorQue0())
            System.out.println(s.toString());
        
        System.out.println();
        
        
        for ( Sorpresa s : irACasilla())
            System.out.println(s.toString());
            
        
    }
    private static ArrayList<Sorpresa> mayorQue0()
    {
        ArrayList<Sorpresa> m = new ArrayList();
        for ( Sorpresa s : mazo)
            if (s.getValor() > 0 )
                m.add(s);
        
        return m;
    }
    private static ArrayList<Sorpresa> irACasilla()
    {
        ArrayList<Sorpresa> m = new ArrayList();
        for ( Sorpresa s : mazo)
            if (s.getTipo() == TipoSorpresa.IRACASILLA )
                m.add(s);
        
        return m;
    }
    private static ArrayList<Sorpresa> getSorpresa(TipoSorpresa sorpresa)
    {
        ArrayList<Sorpresa> m = new ArrayList();
        for ( Sorpresa s : mazo)
            if (s.getTipo() == sorpresa )
                m.add(s);
        
        return m;
    }
    
    
}
