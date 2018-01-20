# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  require_relative "tipo_casilla"
  class Casilla
    attr_reader :numero_casilla,
      :coste,
      :tipo
    
    def initialize(numero, coste, tipo)
      @numero_casilla = numero
      @coste  = coste
      @tipo = tipo
    end
    
    def to_s
      "Numero: #{@numero_casilla} \nTipo: #{@tipo}\n"
    end
    
    def soy_edificable
      false
    end
  end
end
