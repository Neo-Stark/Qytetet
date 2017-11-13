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
    private int saldo = 7500;
    private Sorpresa cartaLibertad;
    private ArrayList<TituloPropiedad> propiedades;
    private Casilla casillaActual;

    public Jugador(String nombre, Casilla casillaActual) {
        this.nombre = nombre;
        this.cartaLibertad = null;
        this.casillaActual = casillaActual;
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

    }

    boolean actualizarPosicion(Casilla casilla) {

    }

    boolean comprarTitulo() {

    }

    Sorpresa devolverCartaLibertad() {

    }

    void irACarcel(Casilla casilla) {

    }
    void modificarSaldo(int cantidad) {
        
    }
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada) {
        
    }
    void pagarCobrarPorCasaYHotel(int cantidad) {
        
    }
    boolean pagarLibertad(int cantidad) {
        
    }
    boolean puedoEdificarCasa(Casilla casilla) {
        
    }
    boolean puedoEdificarHotel(Casilla casilla) {
        
    }
    boolean puedoHipotecar (Casilla casilla) {
        
    }
    boolean puedoPagarHipoteca(Casilla casilla) {
        
    }
    boolean puedoVenderPropiedad(Casilla casilla) {
        
    }
    boolean tengoCartaLibertad() {
        
    }
    void venderPropiedad(Casilla casilla) {
        
    }
    int cuantasCasasHotelesTengo() {
        
    }
    void eliminarDeMisPropiedades(Casilla casilla) {
        
    }
    boolean esDeMiPropiedad(Casilla casilla) {
        
    }
    boolean tengoSaldo(int cantidad) {
        
    }
}
