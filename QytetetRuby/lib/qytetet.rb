#encoding: UTF-8

module ModeloQytetet
  require 'singleton'
  require_relative 'tablero'
  require_relative 'jugador'
  require_relative 'dado'
  class Qytetet
    include Singleton
    MAX_JUGADORES = 4
    MAX_CARTAS = 10
    PRECIO_LIBERTAD = 200
    SALDO_SALIDA = 1000
    attr_reader :carta_actual, :jugador_actual, :tablero, :jugadores, :mazo

    def initialize
      @carta_actual = nil
      @mazo = Array.new
      @jugador_actual = nil
      @jugadores = Array.new
      @dado = Dado.instance
      @tablero = nil
    end

    def aplicar_sorpresa
      tiene_propietario = false
      case @carta_actual.tipo
      when TipoSorpresa::PAGARCOBRAR
        @jugador_actual.modificar_saldo(@carta_actual.valor)
      when TipoSorpresa::IRACASILLA
        if @tablero.es_casilla_carcel(@carta_actual.valor)
          encarcelar_jugador
        else
          nueva_casilla = @tablero.obtener_casilla_numero(@carta_actual.valor)
          tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
        end
      when TipoSorpresa::PORCASAHOTEL
        @jugador_actual.pagar_cobrar_por_casa_y_hotel(@carta_actual.valor)
      when TipoSorpresa::PORJUGADOR
        @jugadores.reject { |j| j == @jugador_actual }.each do |j|
          j.modificar_saldo(@carta_actual.valor)
          @jugador_actual.modificar_saldo(-@carta_actual.valor)
        end
      end

      if @carta_actual.tipo == TipoSorpresa::SALIRCARCEL
        @jugador_actual.carta_libertad = @carta_actual
      else
        @mazo << @carta_actual
      end
      #EXAMEN-INICIO
      if @carta_actual.tipo TipoSorpresa::LOTERIA
        @jugador_actual.carta_loteria = @carta_actual
      else
        @mazo << @carta_actual
      end
      #EXAMEN-FIN
      tiene_propietario
    end

    def cancelar_hipoteca(casilla)
      return false if !casilla.esta_hipotecada
      @jugador_actual.modificar_saldo(-casilla.cancelar_hipoteca)
      true
    end

    def comprar_titulo_propiedad
      @jugador_actual.comprar_titulo
    end

    def edificar_casa(casilla)
      puedo_edificar = false
      if casilla.soy_edificable && casilla.se_puede_edificar_casa
        puedo_edificar = @jugador_actual.puedo_edificar_casa(casilla)
        if puedo_edificar
          coste_edificar_casa = casilla.edificar_casa
          @jugador_actual.modificar_saldo(-coste_edificar_casa)
        end
      end
      puedo_edificar
    end

    def edificar_hotel(casilla)
      puedo_edificar = false
      if casilla.soy_edificable && casilla.se_puede_edificar_hotel
        puedo_edificar = @jugador_actual.puedo_edificar_hotel(casilla)
        if puedo_edificar
          coste_edificar_hotel = casilla.edificar_hotel
          @jugador_actual.modificar_saldo(-coste_edificar_hotel)
        end
      end
      puedo_edificar
    end

    def hipotecar_propiedad(casilla)
      return if casilla.soy_edificable && !casilla.esta_hipotecada &&
        @jugador_actual.puedo_hipotecar(casilla)
      @jugador_actual.modificar_saldo(casilla.hipotecar)
    end

    def intentar_salir_carcel(metodo)
      libre = false
      if metodo == MetodoSalirCarcel::TIRANDODADO
        valor_dado = @dado.tirar
        libre = valor_dado > 4
      elsif metodo == MetodoSalirCarcel::PAGANDOLIBERTAD
        libre = @jugador_actual.pagar_libertad(PRECIO_LIBERTAD)
      end
      @jugador_actual.encarcelado = false if libre
      libre
    end

    def jugar
      valor_dado        = @dado.tirar
      casilla_posicion  = @jugador_actual.casilla_actual
      nueva_casilla     = @tablero.obtener_nueva_casilla(casilla_posicion, 2)
      tengo_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)

      if !nueva_casilla.soy_edificable
        if nueva_casilla.tipo == TipoCasilla::JUEZ
          encarcelar_jugador
        elsif nueva_casilla.tipo == TipoCasilla::SORPRESA
          @carta_actual = @mazo.shift # Saca la sorpresa
        end
      end

      { dado: valor_dado, tiene_propietario: tengo_propietario }
    end

    def obtener_ranking
      r = @jugadores.sort_by(&:obtener_capital)
      .map { |j| [j.nombre, j.obtener_capital] }
      Hash[r]
    end

    def propiedades_hipotecadas_jugador(hipotecadas)
      @jugador_actual.obtener_propiedades_hipotecadas(hipotecadas).map(&:casilla)
    end

    def siguiente_jugador
      @jugador_actual = @jugadores[
        (@jugadores.find_index(@jugador_actual) + 1) % @jugadores.size]
    end

    def vender_propiedad(casilla)
      puedo_vender = false
      if casilla.soy_edificable &&
          @jugador_actual.puedo_vender_propiedad(casilla)
        @jugador_actual.vender_propiedad(casilla)
        puedo_vender = true
      end
      puedo_vender
    end

    def encarcelar_jugador
      if @jugador_actual.tengo_carta_libertad
        carta = @jugador_actual.devolver_carta_libertad
        @mazo << carta
      else
        @jugador_actual.ir_a_carcel(@tablero.carcel)
      end
    end

    def siguiente_jugador
      num_jug_sig = (@jugadores.index(@jugador_actual) + 1) % @jugadores.size
      @jugador_actual = @jugadores.at(num_jug_sig)
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
      @mazo<< Sorpresa.new("Te ha tocado la lotería, disfruta de Coco Bomgo", 1000000, TipoSorpresa::LOTERIA)
      @mazo<< Sorpresa.new("Te ha llegado un sobre con una tarjeta negra, eres un nuevo especulador", 3000, TipoSorpresa::CONVERTIRME)
      @mazo<< Sorpresa.new("Bonita cuenta en Suiza, ahora eres un especulador", 5000, TipoSorpresa::CONVERTIRME)
    end

    def inicializar_jugadores(nombres)
      nombres.each { |nombre| @jugadores<<Jugador.new(nombre)  }
    end

    def inicializar_tablero
      @tablero = Tablero.new
    end

    def salida_jugadores
      for jugador in @jugadores
        jugador.casilla_actual = @tablero.obtener_casilla_numero(0)
        jugador.modificar_saldo(7500)
      end
      @jugador_actual = @jugadores.sample
    end

    def inicializar_juego (nombres)
      inicializar_jugadores(nombres)
      inicializar_tablero
      inicializar_cartas_sorpresa
      salida_jugadores
    end

    #EXAMEN-INICIO
    def irme_a_vivir_a_cancun
      return false unless @jugador_actual.tengo_carta_loteria
      ganancia_ventas = @jugador_actual.vender_todo
      ganancia_hipotecar = @jugador_actual.hipotecar_todo

      propiedades = @jugador_actual.obtener_propiedades_hipotecadas(false)
      if ganancia_ventas > ganancia_hipotecar
        propiedades.each { |prop|
          @jugador_actual.vender_propiedad(prop.casilla)}
      else
        propiedades.each { |prop|
          hipotecar_propiedad(prop.casilla)}
      end

      cantidad = @jugador_actual.carta_loteria.valor
      @jugador_actual.modificar_saldo(cantidad)
      @jugador_actual.devolver_carta_loteria
      true
    end

    private :encarcelar_jugador, :inicializar_cartas_sorpresa,
      :inicializar_jugadores, :inicializar_tablero,
      :salida_jugadores
  end
end
