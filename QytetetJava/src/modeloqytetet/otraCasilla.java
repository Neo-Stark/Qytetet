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
public class otraCasilla extends Casilla {

    public otraCasilla(int numeroCasilla, int coste, TipoCasilla tipo) {
        super(numeroCasilla, coste, tipo);
    }


    @Override
    public boolean soyEdificable(){
        return false;
    }
}
