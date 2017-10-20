    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

public class Casilla {
    private int numeroCasilla;
    private int coste;
    private int numHoteles;
    private int numCasas;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;

    public Casilla(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.tipo = tipo;
        this.titulo = titulo;
       
    }
    //El constructor para las casillas que no son de tipo calle
    public Casilla(int numeroCasilla, int coste, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.tipo = tipo;
    }

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }
        
    @Override
    public String toString() {
        String s = "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", tipo=" + tipo + " }";
        if(titulo != null){
            s += " titulo=" + titulo ;
        }
     return s;
    }
}
