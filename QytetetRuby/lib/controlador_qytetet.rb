# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module InterfazTextualQytetet
  require_relative 'qytetet'
  require_relative 'vista_textual_qytetet.rb'
  
  class ControladorQytetet
    def initialize
      @juego = nil
      @jugador = nil
      @casilla = nil
      @vista   = VistaTextualQytetet.new
    end
    
    def inicializacion_juego
      @juego = ModeloQytetet::Qytetet.instance
      @juego.inicializar_juego(@vista.obtener_nombre_jugadores)
      @jugador = @juego.jugador_actual
      @casilla = @juego.jugador_actual.casilla_actual
      #EXAMEN-INICIO
      @jugador.carta_loteria = @juego.mazo.pop
      @jugador.propiedades << @juego.tablero.obtener_casilla_numero(1).titulo_propiedad
      @jugador.propiedades << @juego.tablero.obtener_casilla_numero(2).titulo_propiedad
      @jugador.propiedades << @juego.tablero.obtener_casilla_numero(4).titulo_propiedad
      @vista.mostrar("Carta loteria: #{@jugador.carta_loteria}")
      #EXAMEN-FIN
      @vista.mostrar("Tablero: #{@juego.tablero}" \
          "\nComienza el jugador: #{@jugador}"\
          + "\nCasilla actual: #{@casilla}")
      pausa
    end
    
    def estado_actual
      @vista.mostrar("**-JUGADOR-**"\
          "\nNombre: #{@jugador}" \
          "\nCasilla actual: #{@casilla.numero_casilla}" \
          "\nEncarcelado: #{@jugador.encarcelado}" \
          "\nCarta libertad: #{@jugador.tengo_carta_libertad}"\
          "\nCarta loteria: #{@jugador.tengo_carta_loteria}"\
          "\nSaldo actual: #{@jugador.saldo}" 
      );
      if bancarrota 
        @vista.mostrar("Jugador #{@jugador.nombre} en bancarrota.")
        game_over
      end
      pausa
    end
      
    def bancarrota
      @jugador.saldo < 0
    end
      
    def game_over
      @vista.mostrar("GAME OVER!!!.\nRanking:\n#{@juego.obtener_ranking}")
    end
      
    def desarrollo_juego
      loop do
        estado_actual
        if !intentar_salir_carcel
          @vista.mostrar('Sigues en la carcel, pasas turno.')
          pausa
        else
          @vista.mostrar("Tirando dado...")
          espera
          estado = @juego.jugar
          @casilla = @jugador.casilla_actual
          @vista.mostrar("\nValor del dado: #{estado[:dado]}")
          @vista.mostrar("Has caido en la casilla numero: #{@casilla.numero_casilla}"\
              "\nTipo: #{@casilla.tipo}")
          pausa
          estado_actual
          
          if !@jugador.encarcelado
            case @casilla.tipo
            when ModeloQytetet::TipoCasilla::SORPRESA
              @vista.mostrar("#{@juego.carta_actual}\nAplicando Sorpresa...")
              @juego.aplicar_sorpresa
              @jugador = @juego.jugador_actual #Si el jugador se ha convertido en especulador
              estado_actual
              if @juego.carta_actual == ModeloQytetet::TipoSorpresa::IRACASILLA
                @casilla = @jugador.casilla_actual
                @vista.mostrar "Nueva casilla: #{@casilla}"
              end
            when ModeloQytetet::TipoCasilla::CALLE
              @vista.mostrar("Has caido en la calle:\n#{@casilla}")
              if estado[:tiene_propietario]
                @vista.mostrar("\nPagando alquiler...")
                espera
              else
                elegido = @vista.elegir_comprar_propiedad 
                comprado = @juego.comprar_titulo_propiedad if elegido
                if comprado
                  @vista.mostrar("Has comprado el Titulo de propiedad: "\
                      "#{@casilla.titulo_propiedad.nombre}") 
                else
                  @vista.mostrar("\nNo puedes comprar la propiedad")
                end
              end
              estado_actual
            end
          else
            @vista.mostrar("Has caido en la casilla del juez,"\
                "vas directamente a la carcel.")
          end
        end
        if @jugador.tengo_propiedades && !@jugador.encarcelado
          gestion_inmobiliaria
        else
          @vista.mostrar("No tienes propiedades")
        end
        pasa_turno
      end
    end
    
    def pasa_turno
      @vista.mostrar("\n*****PASA DE TURNO*****\n")
      @vista.mostrar("\nTurno siguiente jugador...")
      @juego.siguiente_jugador
      @jugador = @juego.jugador_actual
      @casilla = @jugador.casilla_actual
      espera
    end
    
    def gestion_inmobiliaria
      begin
        opcion = @vista.menu_gestion_inmobiliaria
        if @jugador.tengo_propiedades || opcion == 0
          propiedades = @juego.propiedades_hipotecadas_jugador(opcion == 5)
          propiedades.each{|p| puts p}
          pausa
          prop = elegir_propiedad(propiedades) unless opcion == 0 || opcion == 6
          case opcion
#          when 0 then break
          when 1 then @juego.edificar_casa(prop)
          when 2 then @juego.edificar_hotel(prop)
          when 3 then @juego.vender_propiedad(prop)
          when 4 then @juego.hipotecar_propiedad(prop)
          when 5 then @juego.cancelar_hipoteca(prop)
          when 6 then @juego.irme_a_vivir_a_cancun
          end
          @vista.mostrar(prop)
        else
          @vista.mostrar('No te quedan propiedades')
        end
        
      end while opcion != 0
    end
    def intentar_salir_carcel
      return true if !@jugador.encarcelado
      metodo = case @vista.menu_salir_carcel
      when 0 then MetodoSalirCarcel::TIRANDODADO
      when 1 then MetodoSalirCarcel::PAGANDOLIBERTAD
      end
      @juego.intentar_salir_carcel(metodo)
    end
    
    def espera
      sleep(1)
    end
    
    def pausa
      puts 'Pulsa enter para continuar: '
      gets
    end
      
    def elegir_propiedad(propiedades) # lista de propiedades a elegir
      @vista.mostrar("\tCasilla\tTitulo")
      lista = propiedades.map { |p| "#{p.numero_casilla}  \t#{p.titulo_propiedad.nombre}" }
      
      propiedades.at(@vista.menu_elegir_propiedad(lista))
    end
    
    def main
      inicializacion_juego
      desarrollo_juego
      fin_juego
    end
  end
end
