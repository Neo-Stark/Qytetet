#encoding: UTF-8
module ModeloQytetet
  require_relative "sorpresa"
  require_relative "tipo_sorpresa"
  require_relative "tablero"
  require_relative "jugador"
  require_relative "inspector"
  require_relative "qytetet"
  class PruebaQytetet
    @@mazo = Array.new
    
    def self.inicializar_sorpresas
      @@mazo<< Sorpresa.new("Se han limpiado tus delitos de la base de datos de la policia. Sales de la cárcel",
        0, TipoSorpresa::SALIRCARCEL)
      @@mazo<< Sorpresa.new("Te hemos pillado hackeando los servidores de la UGR, vas directamente a la carcel",
        9, TipoSorpresa::IRACASILLA )
      @@mazo<< Sorpresa.new("Has ganado un viaje un viaje a Las Vegas, pero lo vendes porque prefieres seguir programando",
        800, TipoSorpresa::PAGARCOBRAR )
      @@mazo<< Sorpresa.new("Decides ir de compras, vas en metro a Recogidas", 19, TipoSorpresa::IRACASILLA)
    end
    private
    def self.get_sorpresa(tipo_sorpresa)
      m = Array.new
      for elemento in @@mazo
        if(elemento.tipo == tipo_sorpresa)
          m << elemento
        end
      end
      return m
    end
    def self.ir_a_casilla()
      m = Array.new
      for elemento in @@mazo
        if(elemento.tipo == TipoSorpresa::IRACASILLA)
          m << elemento
        end
      end
      return m
    end
    def self.mayor_que_0()
      s = Array.new
#      @@mazo.each { |m| s << m.valor>0 }
      #      m = Array.new
      #      for elemento in @@mazo
      #        if(elemento.valor > 0)
      #          m << elemento
      #        end
      #      end
      return s
    end
#EXAMEN-inicio
    public
    def self.main()
      juego = Qytetet.instance
      tablero = juego.tablero
      puts tablero
      inspector = Inspector.crear_inspector_codigo("Fran", 1)
      inspector.asignar_casilla(juego.tablero.obtener_casilla_numero(6))  #La casilla 5 no es de tipo calle
      inspector.asignar_casilla(juego.tablero.obtener_casilla_numero(8))
      inspector.inspeccionar
      Inspector.imprimir_manual
    end
  end
 PruebaQytetet.main
end
#EXAMEN-fin