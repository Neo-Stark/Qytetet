/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author fran
 */
public class Calle extends Casilla {

    private TituloPropiedad titulo;

    public Calle(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo) {
        super(numeroCasilla, coste, tipo);
        this.titulo = titulo;
        titulo.setCasilla(this);
    }

    public TituloPropiedad getTitulo() {
        return titulo;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public int getNumCasas() {
        return numCasas;
    }

}
