/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author fjose
 */
public class Jugador {

    private boolean encarcelado = false;
    private String nombre;
    private int saldo;
    private Sorpresa cartaLibertad;
    private ArrayList<TituloPropiedad> propiedades;
    private Casilla casillaActual;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartaLibertad = null;
        this.casillaActual = null;
        this.saldo = 0;
    }

    public boolean isEncarcelado() {
        return encarcelado;
    }

    void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }

    public Casilla getCasillaActual() {
        return casillaActual;
    }

    void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }

    void setCartaLibertad(Sorpresa cartaLibertad) {
        this.cartaLibertad = cartaLibertad;
    }

    boolean tengoPropiedades() {
        return !propiedades.isEmpty();
    }

    boolean actualizarPosicion(Casilla casilla) {

    }

    boolean comprarTitulo() {

    }

    Sorpresa devolverCartaLibertad() {
        Sorpresa Libertad;
        Libertad = cartaLibertad;
        cartaLibertad = null;
        return Libertad;
    }

    void irACarcel(Casilla casilla) {

    }

    void modificarSaldo(int cantidad) {
        saldo += cantidad;
    }

    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada) {
        ArrayList <TituloPropiedad> propiedades = new ArrayList();
        
        for(TituloPropiedad propiedad:propiedades ){
                // RECORRE TODO ArrayList PROPIEDADES
                if(propiedad.getHipotecada() == hipotecada){
                    propiedades.add(propiedad);
                }
        }
        return propiedades;
    }

    int obtenerCapital() {
        int PrecioInmuebles = 0;
        for (TituloPropiedad precio : propiedades) {
            if (precio.getHipotecada() == true) {
                PrecioInmuebles -= precio.getHipotecaBase();
            }
            PrecioInmuebles += precio.getPrecioEdificar() * precio.getCasilla().getNumCasas();
            PrecioInmuebles += precio.getPrecioEdificar() * precio.getCasilla().getNumHoteles();
            PrecioInmuebles += precio.getCasilla().getCoste();
        }
        return saldo + PrecioInmuebles;
    }

    void pagarCobrarPorCasaYHotel(int cantidad) {

    }

    boolean pagarLibertad(int cantidad) {

    }

    boolean puedoEdificarCasa(Casilla casilla) {

    }

    boolean puedoEdificarHotel(Casilla casilla) {

    }

    boolean puedoHipotecar(Casilla casilla) {

    }

    boolean puedoPagarHipoteca(Casilla casilla) {

    }

    boolean puedoVenderPropiedad(Casilla casilla) {
        boolean propietario;
        propietario = false;

        if (esDeMiPropiedad(casilla) == true) {
        }
        return propietario;
    }

    boolean tengoCartaLibertad() {
        return cartaLibertad != null;
    }

    void venderPropiedad(Casilla casilla) {

    }

    int cuantasCasasHotelesTengo() {
        int inmuebles;
        inmuebles = 0;
        for (TituloPropiedad inmueble : propiedades) {
            inmuebles += inmueble.getCasilla().getNumCasas();
            inmuebles += inmueble.getCasilla().getNumHoteles();

        }
        return inmuebles;
    }

    void eliminarDeMisPropiedades(Casilla casilla) {
        int numero= casilla.getNumeroCasilla();
        propiedades.remove(numero);
    }

    boolean esDeMiPropiedad(Casilla casilla) {

    }

    boolean tengoSaldo(int cantidad) {
        boolean mi_saldo  = false;
        if(saldo >= cantidad)
            mi_saldo = true;
        return mi_saldo;
    }
}
