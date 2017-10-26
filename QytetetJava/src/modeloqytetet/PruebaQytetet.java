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
    public static void main(String[] args) {
        String[] nombres = {"Fran","Jose"};
        Qytetet juego = new Qytetet(nombres);
        System.out.println(juego.tablero.toString());
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
