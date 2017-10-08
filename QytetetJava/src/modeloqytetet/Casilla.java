/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author BinTrack Laptop
 */
public class Casilla {
    private int numeroCasilla, coste, numHoteles, numCasas;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    
    public Casilla ( TipoCasilla tipo, int numeroCasilla){  // Constructor tipo sorpresa.
        this.numeroCasilla= numeroCasilla;
        titulo = null;
        numHoteles = 0;
        numCasas = 0;
    }
    public Casilla (  TipoCasilla tipo, int numeroCasilla, int coste, TituloPropiedad titulo){  // Constructor tipo calle
        this.tipo=tipo;
        this.numeroCasilla= numeroCasilla;
        this.coste= coste;
        this.titulo= titulo;
        numHoteles = 0;
        numCasas = 0;
              
    }
    public int getnumeroCasilla (){     // Consultor
        
        return numeroCasilla;
    }
    public int getcoste (){     // Consultor
        
        return coste;
    }
    public int getnumHoteles (){     // Consultor
        
        return numHoteles;
    }
    public int getnumCasas (){     // Consultor
        
        return numCasas;
    }
    public TipoCasilla getTipoCasilla(){     // Consultor
        
        return tipo;
    }
    public TituloPropiedad getTituloPropiedad (){     // Consultor
        
        return titulo;
    }
    public void setnumHoteles (int x){     //Modificador
        numHoteles = x;
    }
    public void setnumCasas (int x){       //Modificador
        numCasas = x;
    }
    private void settitulo(TituloPropiedad x){
        titulo = x ;
        
    }

    public String toString (){
        if (tipo = TipoCasilla.CALLE )  {
            return "Numero de casilla" + numeroCasilla +", Coste del inmueble " + coste + 
                    ", numero de hoteles " + numHoteles + ", numero de casas " + numCasas
                    + titulo.toString() + "tipo de casilla" + tipo;
        }else {return "Numero de casilla" + numeroCasilla +", Coste del inmueble " + coste + 
                    ", numero de hoteles " + numHoteles + ", numero de casas " + numCasas
                    + titulo.toString() + "tipo de casilla" + tipo;
        }
    }   
}
   
