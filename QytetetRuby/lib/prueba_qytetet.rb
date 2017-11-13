#encoding: UTF-8
module ModeloQytetet
  require_relative "sorpresa"
  require_relative "tipo_sorpresa"
  require_relative "tablero"
  require_relative "jugador"
  require_relative "inspector"
  require_relative "qytetet"
  class PruebaQytetet
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
      #@@mazo.each { |m|  m.valor?>0  s << m }
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
      nombres = %w(fran pepe)  
      juego.inicializar_juego(nombres)
      tablero = juego.tablero
      puts juego.jugador_actual.nombre
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