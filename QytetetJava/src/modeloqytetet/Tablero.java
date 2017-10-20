/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;



public class Tablero {
    private static ArrayList<Casilla>casillas =new ArrayList();
    private static Casilla carcel;
    public Tablero() {
        inicializar();
    }

    private static void inicializar(){
        casillas.add(new Casilla(0, 0, TipoCasilla.SALIDA));
        casillas.add(new Casilla(1, 300,1,1,TipoCasilla.CALLE, new TituloPropiedad("Calle Recogidas",
        false, 50,15.2,0,1000)));
        casillas.add(new Casilla(2, 350,1,3, TipoCasilla.CALLE, new TituloPropiedad("Calle Periodistas",
        true, 50,8.2, 400, 500)));
        casillas.add(new Casilla(3, 0, TipoCasilla.CARCEL));
        carcel=casillas.get(2);
        casillas.add(new Casilla(4,450,3,1, TipoCasilla.CALLE, new TituloPropiedad("Calle Gran vía",false, 87,8.7,0,250)));
        casillas.add(new Casilla(5,0,TipoCasilla.SORPRESA));
        casillas.add(new Casilla(6,550,0,4,TipoCasilla.CALLE, new TituloPropiedad("Calle pedroantonio", true, 55,10.2,300,500)));
        casillas.add(new Casilla(7,0, TipoCasilla.JUEZ));
        casillas.add(new Casilla (8,0, TipoCasilla.IMPUESTO));
        casillas.add(new Casilla(9,400,2,2,TipoCasilla.CALLE, new TituloPropiedad("Calle Reyes Catolicos", true,
            100,17.3,400,600)));
        casillas.add(new Casilla(10,0, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(11,400,2,3,TipoCasilla.CALLE, new TituloPropiedad("Calle Cuesta Gomerez",
                true, 60,10,600,700)));
        casillas.add(new Casilla(12,500,0,5,TipoCasilla.CALLE, new TituloPropiedad("Calle Camino de Ronda ",
                false, 55,10.1,0,500)));
        casillas.add(new Casilla(13,0, TipoCasilla.PARKING));
        casillas.add(new Casilla(14,600,1,4,TipoCasilla.CALLE, new TituloPropiedad("Calle Elvira",
                true, 100,12.2,350,700)));
        casillas.add(new Casilla(15,650,5,2,TipoCasilla.CALLE, new TituloPropiedad("Calle Fuente Nueva",
                true, 100,19.8,800,750)));
        casillas.add(new Casilla(16,0, TipoCasilla.SORPRESA));
        casillas.add(new Casilla(17,750,0,7,TipoCasilla.CALLE, new TituloPropiedad("Calle Carrera del Darro",
                false, 53,10.0,0,300)));
        casillas.add(new Casilla(18,650,1,1,TipoCasilla.CALLE, new TituloPropiedad("Calle Ángel Ganivet",
                true, 60,13.1,150,400)));
        casillas.add(new Casilla(19,450,4,4,TipoCasilla.CALLE, new TituloPropiedad("Calle San Jerónimo",
                false, 100,18.3,750,658)));
        
        
        
        
    }
    @Override
    public String toString() {
        String tablero;
         for (Casilla c: casillas){
           tablero= casillas.toString();
        }
        return "Tablero{" + "casillas=" + casillas + ", carcel=" + carcel + '}';
    }
    
    
}