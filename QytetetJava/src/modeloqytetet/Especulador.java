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
public class Especulador extends Jugador {

    private int fianza;

    protected Especulador(Jugador jugador, int fianza) {
        super(jugador);
        this.fianza = fianza;
        factorEspeculador = 2;
    }

    @Override
    protected void pagarImpuestos(int cantidad) {
        super.pagarImpuestos(cantidad/2);
    }

    @Override
    protected void irACarcel(Casilla casilla) {
        if(!pagarFianza(fianza))
            super.irACarcel(casilla);
    }

    @Override
    protected Especulador convertirme(int fianza) {
        return this;
    }

    private boolean pagarFianza(int cantidad) {
                return pagarLibertad(cantidad);
    }
    
    @Override
    public String toString(){
        return super.toString() + " (Especulador)";
    }
    
    @Override
    public int getFactorEspeculador(){
        return factorEspeculador;
    }
}
