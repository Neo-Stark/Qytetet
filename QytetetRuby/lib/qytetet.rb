#encoding: UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  require 'singleton'
  require_relative 'tablero'
  require_relative 'jugador'
  class Qytetet
    include Singleton
    @@MAX_JUGADORES = 4
    @@MAX_CARTAS = 10
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
    attr_reader :carta_actual, :jugador_actual, :tablero, :jugadores
    def initialize
      @carta_actual = nil
      @mazo = Array.new
      @jugador_actual = nil
      @jugadores = Array.new
      @dado = nil
      @tablero = nil
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
      casillas = Array.new
      casillas << @jugador_actual.obtener_propiedades_hipotecadas(hipotecadas)
      casillas
    end

    def siguiente_jugador
      num_jug_sig = (@jugadores.index(@jugador_actual) + 1) % @@MAX_JUGADORES
      @jugador_actual = @jugadores.at(num_jug_sig)
    end
  
    def vender_propiedad(casilla)
      
    end
    def encarcelar_jugador
      
    end
    def inicializar_cartas_sorpresa
      @mazo<< Sorpresa.new("Se han limpiado tus delitos de la base de datos de la policia. Sales de la cárcel", 0, TipoSorpresa::SALIRCARCEL)
      @mazo<< Sorpresa.new("Te hemos pillado hackeando los servidores de la UGR, vas directamente a la carcel",
        @tablero.carcel.numero_casilla, TipoSorpresa::IRACASILLA )
      @mazo<< Sorpresa.new("Has ganado un viaje un viaje a Las Vegas, pero lo vendes porque prefieres seguir programando",
        800, TipoSorpresa::PAGARCOBRAR )
      @mazo<< Sorpresa.new("Decides ir de compras, vas en metro a Recogidas", 19, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Un huracán ha arrasado con todos tus hoteles, pagas 300 por cada casa y hotel.", 100, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Un magnate del petroleo ha decidido invertir en tus propiedades,\
        obtienes 400 por cada casa y hotel", 400, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Decides regalar un año de suscripción de Netflix a cada jugador, \
        pagas 150 por cada jugador", 150, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Has aprobado la carrera, cada jugador te da 200 por \
        tu gran hazaña", 200, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Tienes ganas de salir de fiesta, vas a Pedro Antonio", 8, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Anoche te pasaste con la juerga, te has dejado 750 en una noche", 750, TipoSorpresa::PAGARCOBRAR)
    end
    def inicializar_jugadores(nombres)
      
      nombres.each { |nombre| @jugadores<<Jugador.new(nombre)  }
    end
    def inicializar_tablero
      @tablero = Tablero.new
    end
    def salida_jugadores
      @jugadores.each {|jugador| jugador.actualizar_posicion(@tablero.obtener_casilla_numero(0)) 
        jugador.modicar_saldo(7500)
      }
      @jugador_actual = @jugadores.choice
    end
    
    def inicializar_juego (nombres)
      raise ArgumentError, 'Numero de jugadores incorrecto' unless nombres.count >= 2 \
                                                                && nombres.count <= 4
      inicializar_jugadores(nombres)
      inicializar_cartas_sorpresa
      inicializar_tablero
      salida_jugadores
    end
  end
end   
