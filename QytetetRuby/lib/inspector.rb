# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#EXAMEN-inicio
module ModeloQytetet
  class Inspector
    @@manual = "Debo inspeccionar las casillas con cuidado y esmero"
    @@contador = 0
    attr_accessor :nombre, :codigo
    def initialize (nombre, codigo)
      @casillas = Array.new
      @nombre = nombre
      @codigo = codigo
      @@contador += 1
    end
    def self.imprimir_manual
      puts @@manual
    end
    def self.crear_inspector_codigo(nombre, codigo)
      self.new(nombre,codigo)
    end
    def self.crear_inspector(nombre)
      codigo = @@contador + 1
      self.new(nombre,codigo)
    end
    def asignar_casilla(una_casilla)
      @casillas << una_casilla
    end
    def inspeccionar
      @casillas.each { |casilla| 
      if casilla.titulo_propiedad != nil
        puts casilla.titulo_propiedad.nombre
      end
      }
    end
    private
    def desasignar_casillas()
      @casillas.clear
    end
    
  end
end
#EXAMEN-fin