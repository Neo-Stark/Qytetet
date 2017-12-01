# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Jugador
    attr_accessor :casilla_actual, :encarcelado, :nombre, :carta_libertad, :saldo
    
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
      if casilla.numero_casilla < @casilla_actual.numero_casilla
        modificar_saldo(Qytetet::SALDO_SALIDA)
      end
      
      @casilla_actual = casilla
      if casilla.soy_edificable
        if casilla.tengo_propietario && !casilla.propietario_encarcelado
          modificar_saldo(-casilla.cobrar_alquiler)
          return true
        end
        modificar_saldo(-casilla.coste) if casilla.tipo == TipoCasilla::IMPUESTO
      end
      false
    end
    
    def comprar_titulo
      puedo_comprar = false
      if(@casilla_actual.soy_edificable)
        tengo_propietario = @casilla_actual.tengo_propietario
        if !tengo_propietario
          coste_compra= @casilla_actual.coste
          if tengo_saldo(coste_compra)
            titulo=@casilla_actual.asignar_propietario(self)
            @propiedades << titulo
            modificar_saldo(-coste_compra)
            puedo_comprar = true
          end
        end
      end
      puedo_comprar
    end
    
    def devolver_carta_libertad
      devolver_carta = @carta_libertad
      @carta_libertad = nil
      devolver_carta
    end
    
    def ir_a_carcel(carcel)
      @casilla_actual = carcel
      @encarcelado = true
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
      @propiedades.select { |p| p.hipotecada == hipotecada }
    end
    
    def pagar_cobrar_por_casa_y_hotel(cantidad)
      @propiedades.each do |propiedad|
        total += cantidad*propiedad.casilla.num_hoteles + cantidad*propiedad.casilla.num_casas 
      end
      @saldo += total
    end
    
    def pagar_libertad(cantidad)
      libre = false
      if tengo_saldo(cantidad)
        modificar_saldo(-cantidad)
        libre = true
      end
      libre
    end
    
    def puedo_edificar_casa(casilla)
      casilla.se_puede_edificar_casa && @saldo > casilla.titulo_propiedad.precio_edificar
    end
    
    def puedo_edificar_hotel(casilla)
      casilla.se_puede_edificar_hotel && @saldo > casilla.titulo_propiedad.precio_edificar
    end
    
    def puedo_hipotecar(casilla)
      es_de_mi_propiedad(casilla)
    end
    
    def puedo_pagar_hipoteca(casilla)
      tengo_saldo(casilla.coste_hipoteca)
    end
    
    def puedo_vender_propiedad(casilla)
      es_de_mi_propiedad(casilla) && !casilla.esta_hipotecada
    end
    
    def tengo_carta_libertad
      @carta_libertad != nil
    end
    
    def vender_propiedad(casilla)
      modificar_saldo(casilla.vender_titulo)
      puts @propiedades.include?(casilla)
      eliminar_de_mis_propiedades(casilla)
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
      @propiedades.delete(casilla.titulo_propiedad)
      casilla.titulo_propiedad.propietario = nil
    end
    
    def es_de_mi_propiedad(casilla)
      @propiedades.include?(casilla.titulo_propiedad)
    end
    
    def tengo_saldo(cantidad)
      @saldo >= cantidad
    end
    
    def to_s
      "#{@nombre}"
    end
  end
end
