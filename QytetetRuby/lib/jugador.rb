# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Jugador
    attr_accessor :casilla_actual, :encarcelado
    attr_writer :carta_libertad
    def initialize (nombre)
      @encarcelado = false
      @nombre = nombre
      @saldo = 0
      @casilla_actual = nil
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
      devolver_carta = @carta_libertad
      @carta_libertad = nil
      devolver_carta
    end
    def ir_a_carcel(carcel)
      @casilla_actual = carcel
    end
    def modificar_saldo(cantidad)
      @saldo += cantidad
    end
    def obtener_capital
      capital = saldo
      @propiedades.each { |propiedad| capital += propiedad.casilla.coste + 
          (propiedad.casilla.num_casas + propiedad.casilla.num_hoteles)*propiedad.precio_edificar
        if propiedad.hipotecada
          capital -= propiedad.hipoteca_base
        end
      }
      capital
    end
    def obtener_propiedades_hipotecadas(hipotecada)
      propiedades_hipotecadas = Array.new
      @propiedades.each do |propiedad|
        if hipotecada && propiedad.hipotecada
          propiedades_hipotecadas << propiedad
        elsif !hipotecada && !propiedad.hipotecada
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
      es_de_mi_propiedad(casilla) && !casilla.esta_hipotecada
    end
    
    def tengo_carta_libertad
      @carta_libertad != nil
    end
    
    def vender_propiedad(casilla)
      
    end
    
    private
    def cuantas_casas_hoteles_tengo
      total = 0
      @propiedades.each do |p|
        total += p.casilla.num_casas + p.casilla.num_hoteles
      end
      total
    end
    
    def eliminar_de_mis_propiedades(casilla)
      @propiedades.delete(casilla.titulo)
      casilla.titulo.propietario = nil
    end
    
    def es_de_mi_propiedad(casilla)
      @propiedades.include?(casilla.titulo)
    end
    
    def tengo_saldo(cantidad)
      @saldo >= cantidad
    end
  end
end
