# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  require 'Singleton'
  require_relative 'tablero'
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
      inicializar_tablero
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
      @@mazo<< Sorpresa.new("Se han limpiado tus delitos de la base de datos de la policia. Sales de la cárcel",
        0, TipoSorpresa::SALIRCARCEL)
      @@mazo<< Sorpresa.new("Te hemos pillado hackeando los servidores de la UGR, vas directamente a la carcel",
        @tablero.carcel.numero_casilla, TipoSorpresa::IRACASILLA )
      @@mazo<< Sorpresa.new("Has ganado un viaje un viaje a Las Vegas, pero lo vendes porque prefieres seguir programando",
        800, TipoSorpresa::PAGARCOBRAR )
      @@mazo<< Sorpresa.new("Decides ir de compras, vas en metro a Recogidas", 19, TipoSorpresa::IRACASILLA)
      @@mazo<< Sorpresa.new("Un huracán ha arrasado con todos tus hoteles, pagas 300 por cada casa y hotel.", 100, TipoSorpresa::PORCASAHOTEL)
      @@mazo<< Sorpresa.new("Un magnate del petroleo ha decidido invertir en tus propiedades,\
        obtienes 400 por cada casa y hotel", 400, TipoSorpresa::PORCASAHOTEL)
      @@mazo<< Sorpresa.new("Decides regalar un año de suscripción de Netflix a cada jugador, \
        pagas 150 por cada jugador", 150, TipoSorpresa::PORJUGADOR)
      @@mazo<< Sorpresa.new("Has aprobado la carrera, cada jugador te da 200 por \
        tu gran hazaña", 200, TipoSorpresa::PORJUGADOR)
      @@mazo<< Sorpresa.new("Tienes ganas de salir de fiesta, vas a Pedro Antonio", 8, TipoSorpresa::IRACASILLA)
      @@mazo<< Sorpresa.new("Anoche te pasaste con la juerga, te has dejado 750 en una noche", 750, TipoSorpresa::PAGARCOBRAR)
    end
    def inicializar_jugadores(nombres)
      nombres.each { |nombre| @jugadores<<Jugador.new(nombre, @tablero.obtener_casilla_numero(0))  }
    end
    def inicializar_tablero
      @tablero.inicializar
    end
    def salida_jugadores
      
    end
  end
end
