# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Casilla
    attr_reader :numero_casilla,
      :coste,
      :tipo,
      :titulo
    attr_accesor :num_hoteles, :num_casas, :titulo
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
      self.new(numero, coste, tipo, titulo)
    end
    def self.crear_casilla(numero, coste, tipo)
      self.new(numero, coste, tipo, nil)
    end
    
    def to_s
      if (@tipo == CALLE)
      "numero= #{@numero_casilla}, coste= #{@coste}, numero hoteles = #{@num_hoteles}\n"
      +"numero casas = #{@num_casas}, tipo = #{@tipo} "
      + @titulo.to_s
      else
      "numero= #{@numero_casilla}, coste= #{@coste}, numero hoteles = #{@num_hoteles}\n"
      +"numero casas = #{@num_casas}, tipo = #{@tipo} "        
      end
    end
  end
end
