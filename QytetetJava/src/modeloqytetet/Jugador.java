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

   private boolean encarcelado;
   private String nombre;
   private int saldo;
   private Sorpresa cartaLibertad;
   private ArrayList<TituloPropiedad> propiedades;
   private Casilla casillaActual;
   static int factorEspeculador = 1;

   public Jugador(String nombre) {
      this.encarcelado = false;
      propiedades = new ArrayList<>();
      this.nombre = nombre;
      this.cartaLibertad = null;
      this.casillaActual = null;
      this.saldo = 0;
   }

   protected Jugador(Jugador jugador) {
      this.encarcelado = false;
      this.nombre = jugador.nombre;
      this.propiedades = jugador.propiedades;
      this.cartaLibertad = jugador.cartaLibertad;
      this.casillaActual = jugador.casillaActual;
      this.saldo = jugador.saldo;
      this.encarcelado = jugador.encarcelado;
   }

   protected void pagarImpuestos(int cantidad) {
      modificarSaldo(-cantidad);
   }

   protected Especulador convertirme(int fianza) {
      System.out.println("me convierto en especulador");
      return new Especulador(this, fianza);
   }

   public String getNombre() {
      return nombre;
   }

   public ArrayList<TituloPropiedad> getPropiedades() {
      return propiedades;
   }

   public int getSaldo() {
      return saldo;
   }

   protected boolean actualizarPosicion(Casilla casilla) {
      boolean tengoPropietario = false;
      if (casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla()) {
         modificarSaldo(Qytetet.getInstance().SALDO_SALIDA);
      }

      setCasillaActual(casilla);

      if (casilla.soyEdificable()) {
         if (((Calle) casilla).tengoPropietario()) {
            tengoPropietario = true;
            if (!((Calle) casilla).propietarioEncarcelado()) {
               modificarSaldo(-((Calle) casilla).cobrarAlquiler());
            }
         }
      } else if (casilla.getTipo() == TipoCasilla.IMPUESTO) {
         pagarImpuestos(casilla.getCoste());
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
         if (!((Calle) casillaActual).tengoPropietario()) {
            if (tengoSaldo(casillaActual.getCoste())) {
               propiedades.add(((Calle) casillaActual).asignarPropietario(this));
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
         PrecioInmuebles += precio.getPrecioEdificar() * ((Calle) precio.getCasilla()).getNumCasas();
         PrecioInmuebles += precio.getPrecioEdificar() * ((Calle) precio.getCasilla()).getNumHoteles();
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
         modificarSaldo(-cantidad);
      }
      return tengoSaldo;
   }

   boolean puedoEdificar/*casa*/(Casilla casilla) {
      boolean esMia = esDeMiPropiedad(casilla);
      boolean tengoSaldo = false;

      if (esMia) {
         tengoSaldo = tengoSaldo(((Calle) casilla).getPrecioEdificar());
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
      return tengoSaldo((int) (1.1 * ((Calle) casilla).calcularValorHipoteca()));
   }

   boolean puedoVenderPropiedad(Casilla casilla) {
      boolean esMia = esDeMiPropiedad(casilla);
      boolean hipotecada = ((Calle) casilla).estaHipotecada();
      return esMia && !hipotecada;
   }

   public boolean tengoCartaLibertad() {
      return cartaLibertad != null;
   }

   void venderPropiedad(Casilla casilla) {
      int precioVenta = ((Calle) casilla).venderTitulo();
      modificarSaldo(precioVenta);
      eliminarDeMisPropiedades(casilla);
   }

   int cuantasCasasHotelesTengo() {
      int inmuebles = 0;
      for (TituloPropiedad inmueble : propiedades) {
         inmuebles += ((Calle) inmueble.getCasilla()).getNumCasas();
         inmuebles += ((Calle) inmueble.getCasilla()).getNumHoteles();

      }
      return inmuebles;
   }

   void eliminarDeMisPropiedades(Casilla casilla) {
      int numero = propiedades.indexOf(((Calle) casilla).getTitulo());
      propiedades.remove(numero);
   }

   boolean esDeMiPropiedad(Casilla casilla) {
      return propiedades.contains(((Calle) casilla).getTitulo());
   }

   boolean tengoSaldo(int cantidad) {
      boolean miSaldo = false;
      if (saldo >= cantidad) {
         miSaldo = true;
      }
      return miSaldo;
   }

   public int getFactorEspeculador() {
      return factorEspeculador;
   }

   @Override
   public String toString() {
      return "Nombre: " + nombre + "\nEncarcelado: " + encarcelado
          + "\nSaldo actual: " + saldo
          + "\n\n\tPropiedades obtenidas\n" + propiedades.toString();
   }
}
