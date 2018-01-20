# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Calle < Casilla
    
    attr_accessor :num_hoteles, :num_casas, :titulo_propiedad
    
    def initialize(numero, coste, titulo)
      super(numero, coste, TipoCasilla::CALLE)
      @titulo_propiedad = titulo
      @num_hoteles = 0
      @num_casas = 0
      @titulo_propiedad.casilla = self
    end
    
    def to_s
      super + "Coste: #{@coste}\nNumero de Hoteles: #{@num_hoteles}" \
        "\nNumero de Casas: #{@num_casas} \n#{@titulo_propiedad}\n"
    end
    
    def asignar_propietario(jugador)
      @titulo_propiedad.propietario = jugador
      @titulo_propiedad
    end
    
    def calcular_valor_hipoteca
      hb = @titulo_propiedad.hipoteca_base
      (hb * (1 + num_casas * 0.5 + num_hoteles)).to_i
    end
    
    def cancelar_hipoteca
      @titulo_propiedad.hipotecada = false
      (calcular_valor_hipoteca * 1.1).to_i
    end
    
    def cobrar_alquiler
      coste_alquiler_base  = @titulo_propiedad.alquiler_base
      coste_alquiler_total = coste_alquiler_base +
        (@num_casas * 0.5 + @num_hoteles * 2).to_i

      @titulo_propiedad.cobrar_alquiler(coste_alquiler_total)
      coste_alquiler_total
    end
    
    def edificar_casa
      @num_casas += 1 
      get_precio_edificar
    end
    
    def edificar_hotel
      @num_hoteles += 1
      @num_casas = 0  
      get_precio_edificar
    end
    
    def esta_hipotecada
      @titulo_propiedad.hipotecada
    end
    
    def get_coste_hipoteca
      @titulo_propiedad.hipoteca_base
    end
    
    def get_precio_edificar
      @titulo_propiedad.precio_edificar
    end
    
    def hipotecar
      @titulo_propiedad.hipotecada = true
      calcular_valor_hipoteca
    end
    
    def precio_total_comprar
      precio_compra = @coste +
        (num_casas + num_hoteles) * @titulo_propiedad.precio_edificar
      (precio_compra + @titulo_propiedad.factor_rev * precio_compra).to_i
    end
    
    def propietario_encarcelado
      @titulo_propiedad.propietario_encarcelado
    end
    
    def se_puede_edificar_casa(factor_especulador)
      @num_casas < 4*factor_especulador
    end
    
    def se_puede_edificar_hotel(factor_especulador)
      @num_hoteles < 4*factor_especulador && @num_casas == 4*factor_especulador
    end
    
    
    def tengo_propietario
      @titulo_propiedad.tengo_propietario
    end
    
    def vender_titulo
      @titulo_propiedad.propietario = nil
      @num_hoteles = 0
      @num_casas = 0
      precio_total_comprar
    end
    
    def soy_edificable
      true
    end
  end
end
