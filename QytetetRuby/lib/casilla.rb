# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  require_relative "tipo_casilla"
  require_relative "titulo_propiedad"
  class Casilla
    attr_reader :numero_casilla,
      :coste,
      :tipo,
      :titulo
    attr_accessor :num_hoteles, :num_casas, :titulo
    private :titulo=
    def initialize(numero, coste, tipo, titulo)
      @numero_casilla = numero
      @coste  = coste
      @num_hoteles = 0
      @num_casas = 0
      @tipo = tipo
      @titulo = titulo
    end
    def self.crear_calle(numero, coste, tipo, titulo)
      titulo.casilla = self.new(numero, coste, tipo, titulo)
    end
    def self.crear_casilla(numero, coste, tipo)
      self.new(numero, coste, tipo, nil)
    end
    
    def to_s
      if (@tipo == TipoCasilla::CALLE)
        "Casilla{ numero= #{@numero_casilla}, coste= #{@coste}, numero hoteles = #{@num_hoteles} "\
          +"numero casas = #{@num_casas}, tipo = #{@tipo}}\n "\
          + @titulo.to_s
      else
        "Casilla{ numero= #{@numero_casilla}, coste= #{@coste}, tipo = #{@tipo}}\n "        
      end
    end
    def asignar_propietario(jugador)
      @titulo.propietario = jugador
    end
    def calcular_valor_hipoteca
      
    end
    def cancelar_hipoteca
      
    end
    def cobrar_alquiler
      
    end
    def edificar_casa
      if se_puede_edificar_casa
        @num_casas += 1 
      end
    end
    def edificar_hotel
      if se_puede_edificar_hotel
        @num_hoteles += 1
        @num_casas = 0  
      end
    end
    def esta_hipotecada
      @titulo.hipotecada
    end
    def get_coste_hipoteca
      @titulo.hipoteca_base
    end
    def get_precio_edificar
      @titulo.precio_edificar
    end
    def hipotecar
      @titulo.hipotecada = true
    end
    def precio_total_comprar
      
    end
    def propietario_encarcelado
      @titulo.propietario_encarcelado
    end
    def se_puede_edificar_casa
      @num_casas < 4
    end
    def se_puede_edificar_hotel
      @num_hoteles < 4 && @num_casas == 4
    end
    def soy_edificable
      se_puede_edificar_casa && se_puede_edificar_hotel
    end
    def tengo_propietario
      @titulo.tengo_propietario
    end
    def vender_titulo
      
    end
    private
    def asignar_titulo_propiedad
      
    end
  end
end
