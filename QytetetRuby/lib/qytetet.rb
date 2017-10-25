# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  require 'Singleton'
  class Qytetet
    include Singleton
      @@MAX_JUGADORES = 4
      @@MAX_CARTAS = 10
      @@PRECIO_LIBERTAD = 200
      @@SALDO_SALIDA = 1000
      attr_reader :carta_actual, :jugador_actual
    def initialize
      @carta_actual = nil
      @mazo
      @jugador_actual = nil
      @jugadores = Array.new
      @tablero
      @dado
    end
    def aplicar_sorpresa
      
    end
    def cancelar_hipoteca(casilla)
      
    end
    def comprar_titulo_proiedad
      
    end
    def edificar_casa(casilla)
      
    end
    def edificar_hotel(casilla)
      
    end
    def hipotecar_propiedad(casilla)
      
    end
    def inicializar_juego(nombres)
      
    end
    def intentar_salir_carcel(metodo_salir_carcel)
      
    end
    def jugar
      
    end
    def obtener_ranking
      
    end
    def propiedades_hipotecadas_jugador(hipotecadas)
      
    end
    def siguiente_jugador
      
    end
    def vender_propiedad(casilla)
      
    end
    def encarcelar_jugador
      
    end
    def inicializar_cartas_sorpresa
      
    end
    def inicializar_jugadores(nombres)
      
    end
    def inicializar_tablero
      @tablero.inicializar
    end
    def salida_jugadores
      
    end
  end
end
