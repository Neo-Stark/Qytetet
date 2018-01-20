/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

public abstract class Casilla {

    protected int numeroCasilla;
    protected int coste;
    protected TipoCasilla tipo;

    public Casilla(int numeroCasilla, int coste, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.tipo = tipo;
    }

    public TipoCasilla getTipo() {
        return this.tipo;
    }

    public int getCoste() {
        return this.coste;
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    abstract boolean soyEdificable();

    @Override
    public String toString() {
        String s = "Casilla:"
                + "\n\tnumeroCasilla=" + numeroCasilla
                + "\n\ttipo=" + tipo;
        return s;
    }
}
