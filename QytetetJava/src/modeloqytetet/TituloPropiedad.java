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
   private Calle calle;

   public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar) {
      this.nombre = nombre;
      this.hipotecada = false;
      this.alquilerBase = alquilerBase;
      this.factorRevalorizacion = factorRevalorizacion;
      this.hipotecaBase = hipotecaBase;
      this.precioEdificar = precioEdificar;
      this.propietario = null;
      this.calle = null;
   }

   public String getNombre() {
      return nombre;
   }

   public Jugador getPropietario() {
      return propietario;
   }

   Casilla getCasilla() {
      return this.calle;
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

   void setPropietario(Jugador propietario) {
      this.propietario = propietario;
   }

   void cobrarAlquiler(int coste) {
      propietario.modificarSaldo(coste);
   }

   boolean propietarioEncarcelado() {
      return propietario.isEncarcelado();
   }

   public boolean tengoPropietario() {
      return propietario != null;
   }

   void setCalle(Casilla casilla) {
      this.calle = (Calle) casilla;
   }

   @Override
   public String toString() {
      return "\n\tNombre: " + nombre
          + "\n\tHoteles: " + calle.getNumHoteles()
          + " Casas: " + calle.getNumCasas()
          + "\n\tHipotecada: " + hipotecada
          + "\n\tAlquiler base: " + alquilerBase
          + "\n\tFactor revalorizacion: " + factorRevalorizacion
          + "\n\tHipoteca base: " + hipotecaBase
          + "\n\tPrecio edificar: " + precioEdificar;
   }

}
