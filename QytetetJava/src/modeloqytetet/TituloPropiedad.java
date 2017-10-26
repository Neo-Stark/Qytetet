/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author fran98
 */
public class TituloPropiedad {
    private String nombre;
    private Boolean hipotecada;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private Jugador propietario;
    private Casilla casilla;

    public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar) {
        this.nombre = nombre;
        this.hipotecada = false;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
        this.propietario = null;
        this.casilla = null;
    }

     String getNombre() {
        return nombre;
    }

     Boolean getHipotecada() {
        return hipotecada;
    }

     void setHipotecada(Boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

     int getAlquilerBase() {
        return alquilerBase;
    }

     float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

     int getHipotecaBase() {
        return hipotecaBase;
    }

     int getPrecioEdificar() {
        return precioEdificar;
    }
     void setPropietario(Jugador propietario){
         
     }
     void cobrarAlquiler(int coste) {
         
     }
     boolean propietarioEncarcelado() {
         
     }
     boolean tengoPropietario() { 
         
     }
     
     void setCasilla(Casilla casilla) {
         this.casilla = casilla;
     }
    @Override
    public String toString()
    {
        return "Titulo propiedad{nombre=" + nombre + ", hipotecada=" + hipotecada 
                + ", alquiler base= " + alquilerBase + " factor revalorizado="
                + factorRevalorizacion + ", hipoteca base" + hipotecaBase
                + ", precio edificar=" + precioEdificar + "}";
    }
    
    
}