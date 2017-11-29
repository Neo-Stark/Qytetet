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

    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    boolean actualizarPosicion(Casilla casilla) {
        boolean tengoPropietario = false;
        if (casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla()) {
            modificarSaldo(Qytetet.getInstance().SALDO_SALIDA);
        }

        setCasillaActual(casilla);

        if (casilla.soyEdificable()) {
            if (casilla.tengoPropietario()) {
                tengoPropietario = true;
                if (!casilla.propietarioEncarcelado()) {
                    modificarSaldo(-casilla.cobrarAlquiler());
                }
            }
        } else if (casilla.getTipo() == TipoCasilla.IMPUESTO) {
            modificarSaldo(-casilla.getCoste());
        }

        return tengoPropietario;
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

    public boolean tengoPropiedades() {
        return !propiedades.isEmpty();
    }

    boolean comprarTitulo() {
        boolean puedoComprar = false;
        if (casillaActual.soyEdificable()) {
            if (!casillaActual.tengoPropietario()) {
                if (casillaActual.getCoste() <= saldo) {
                    propiedades.add(casillaActual.asignarPropietario(this));
                    modificarSaldo(-casillaActual.getCoste());
                    puedoComprar = true;
                }
            }
        }
        return puedoComprar;
    }

    Sorpresa devolverCartaLibertad() {
        Sorpresa Libertad;
        Libertad = cartaLibertad;
        cartaLibertad = null;
        return Libertad;
    }

    void irACarcel(Casilla casilla) {
        setCasillaActual(casilla);
        setEncarcelado(true);
    }

    void modificarSaldo(int cantidad) {
        saldo += cantidad;
    }

    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada) {
        ArrayList<TituloPropiedad> misPropiedades = new ArrayList();

        for (TituloPropiedad propiedad : this.propiedades) {
            if (propiedad.getHipotecada() == hipotecada) {
                misPropiedades.add(propiedad);
            }
        }
        return misPropiedades;
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
        int numeroTotal = cuantasCasasHotelesTengo();
        modificarSaldo(cantidad * numeroTotal);
    }

    boolean pagarLibertad(int cantidad) {
        boolean tengoSaldo = tengoSaldo(cantidad);
        if (tengoSaldo) {
            modificarSaldo(cantidad);
        }
        return tengoSaldo;
    }

    boolean puedoEdificar/*casa*/(Casilla casilla) {
        boolean esMia = esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;

        if (esMia) {
            tengoSaldo = tengoSaldo(casilla.getPrecioEdificar());
        }

        return esMia && tengoSaldo;
    }

//    boolean puedoEdificarHotel(Casilla casilla) {
//        
//    }
    boolean puedoHipotecar(Casilla casilla) {
        return esDeMiPropiedad(casilla);
    }

    boolean puedoPagarHipoteca(Casilla casilla) {
        return tengoSaldo((int) (1.1*casilla.calcularValorHipoteca()));
    }

    boolean puedoVenderPropiedad(Casilla casilla) {
        boolean esMia = esDeMiPropiedad(casilla);
        boolean hipotecada = casilla.estaHipotecada();
        return esMia && !hipotecada;
    }

    boolean tengoCartaLibertad() {
        return cartaLibertad != null;
    }

    void venderPropiedad(Casilla casilla) {
        int precioVenta = casilla.venderTitulo();
        modificarSaldo(precioVenta);
        eliminarDeMisPropiedades(casilla);
    }

    int cuantasCasasHotelesTengo() {
        int inmuebles = 0;
        for (TituloPropiedad inmueble : propiedades) {
            inmuebles += inmueble.getCasilla().getNumCasas();
            inmuebles += inmueble.getCasilla().getNumHoteles();

        }
        return inmuebles;
    }

    void eliminarDeMisPropiedades(Casilla casilla) {
        int numero = casilla.getNumeroCasilla();
        propiedades.remove(numero);
    }

    boolean esDeMiPropiedad(Casilla casilla) {
        return propiedades.contains(casilla.getTitulo());
    }

    boolean tengoSaldo(int cantidad) {
        boolean miSaldo = false;
        if (saldo >= cantidad) {
            miSaldo = true;
        }
        return miSaldo;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre;
    }
}
