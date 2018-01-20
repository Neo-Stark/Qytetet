# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Especulador < Jugador
    attr_reader :fianza
    def initialize(jugador, fianza)
      @encarcelado = jugador.encarcelado
      @nombre = jugador.nombre
      @saldo = jugador.saldo
      @casilla_actual = jugador.casilla_actual
      @carta_libertad = jugador.carta_libertad
      @carta_loteria = jugador.carta_loteria
      @propiedades = jugador.propiedades
      @fianza = fianza
    end
    
    def convertirme(_)
      self 
    end
    
    def pagar_impuestos(cantidad)
      super(cantidad/2)
    end
    
    def ir_a_carcel(casilla)
      super if !pagar_fianza(@fianza)
    end
    
    def pagar_fianza(cantidad)
      pagar_libertad(cantidad)
    end
    
    def to_s
      super + " (Especulador)"
    end
    
    def factor_especulador
      2
    end
    
  end
end
