#encoding: UTF-8
module ModeloQytetet
  require_relative "sorpresa"
  require_relative "tipo_sorpresa"
  require_relative "tablero"
  require_relative "jugador"
  require_relative "inspector"
  require_relative "qytetet"
  require_relative "controlador_qytetet.rb"
  class PruebaQytetet
    def self.main()
      #      juego = Qytetet.instance
      #      nombres = %w(fran pepe)  
      #      juego.inicializar_juego(nombres)
      #      tablero = juego.tablero
      #      puts juego.jugador_actual.nombre
      #      inspector = Inspector.crear_inspector_codigo("Fran", 1)
      #      inspector.asignar_casilla(juego.tablero.obtener_casilla_numero(6))  #La casilla 5 no es de tipo calle
      #      inspector.asignar_casilla(juego.tablero.obtener_casilla_numero(8))
      #      inspector.inspeccionar
      #      Inspector.imprimir_manual

      InterfazTextualQytetet::ControladorQytetet.new.main
    end
  end
  PruebaQytetet.main
end
