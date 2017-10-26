# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Jugador
    attr_accessor :casilla_actual, :encarcelado
    attr_writer :carta_libertad
    def initialize (nombre, casilla)
      @encarcelado = false
      @nombre = nombre
      @saldo = 7500
      @casilla_actual = casilla
      @carta_libertad = nil
      @propiedades = Array.new
    end
    def tengo_propiedades
      @propiedades.any?
    end
    def actualizar_posicion(casilla)
      
    end
    def comprar_titulo
      
    end
    def devolver_carta_libertad
      
    end
    def ir_a_carcel(carcel)
      @casilla_actual = carcel
    end
    def modificar_saldo(cantidad)
      @saldo += cantidad
    end
    def obtener_capital
      
    end
    def obtener_propiedades_hipotecadas#(hipotecada) <== Para que lo queremos?
      propiedades_hipotecadas = Array.new
      @propiedades.each do |propiedad|
        if propiedad.hipotecada
          propiedades_hipotecadas << propiedad
        end
        return propiedades_hipotecadas
      end
    end
    def pagar_cobrar_por_casa_y_hotel(cantidad)
      @propiedades.each do |propiedad|
          total += cantidad*propiedad.casilla.num_hoteles + cantidad*propiedad.casilla.num_casas 
      end
      @saldo += total
    end
    def pagar_libertad(cantidad)
      #@ret boolean
    end
    def puedo_edificar_casa(casilla)
      casilla.se_puede_edificar_casa && @saldo > casilla.propiedad.precio_edificar
    end
    def puedo_edificar_hotel(casilla)
      casilla.se_puede_edificar_hotel && @saldo > casilla.propiedad.precio_edificar
    end
    def puedo_hipotecar(casilla)
      casilla.titulo.hipotecada && casilla.titulo.propietario == self
    end
    def puedo_pagar_hipoteca(casilla)
      
    end
    def puedo_vender_propiedad(casilla)
      
    end
    def tengo_carta_libertad
      
    end
    def vender_propiedad(casilla)
      
    end
    private
    def cuantas_casas_hoteles_tengo
      total = 0
      @propiedades.each do |p|
        total += p.casilla.num_casas + p.casilla.num_hoteles
      end
      return total
    end
    def eliminar_de_mis_propiedades(casilla)
      @propiedades.delete_at(casilla.numero_casilla)
      @propiedades[casilla.numero_casilla].propietario = nil
    end
    def es_de_mi_propiedad(casilla)
      
    end
    def tengo_saldo(cantidad)
      
    end
  end
end
