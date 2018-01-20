#encoding: UTF-8

module ModeloQytetet
#  require_relative "casilla"
#  require_relative "tipo_casilla"
#  require_relative "titulo_propiedad"
  class Tablero
    attr_reader :carcel
    def initialize
      inicializar
    end
    
    def to_s
      string = ""
      @casillas.each { |c| string += c.to_s }
      "Tablero: #{string}" \
        "\n\tNumero total de casillas= #{@casillas.size}"
    end
    private
    def inicializar
      @casillas = Array.new
      @casillas<< Casilla.new(0, 0, TipoCasilla::SALIDA)
      @casillas<< Calle.new(1, 450,  
        TituloPropiedad.new("Calle San Jerónimo", 50, 0.5, 150, 250))
      @casillas<< Calle.new(2, 500,  
        TituloPropiedad.new("Calle Periodistas", 55, 0.6, 200, 300))
      @casillas<< Casilla.new(3, 200, TipoCasilla::IMPUESTO)
      @casillas<< Calle.new(4, 600,  
        TituloPropiedad.new("Calle Ángel Ganivet", 55, 0.6, 200, 300))
      @casillas<< Casilla.new(5, 0, TipoCasilla::JUEZ)
      @casillas<< Calle.new(6, 600,  
        TituloPropiedad.new("Calle Cuesta Gomerez", 55, 0.6, 200, 300))
      @casillas<< Casilla.new(7, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(8, 600,  
        TituloPropiedad.new("Calle Pedro Antonio", 55, 0.6, 200, 300))
      @casillas<< Calle.new(9, 600,  
        TituloPropiedad.new("Calle Reyes Católicos", 55, 0.6, 200, 300))
      @casillas<< Casilla.new(10, 0, TipoCasilla::PARKING)
      @casillas<< Calle.new(11, 600,  
        TituloPropiedad.new("Calle Gran Vía", 55, 0.6, 200, 300))
      @casillas<< Casilla.new(12, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(13, 600,  
        TituloPropiedad.new("Calle Camino de Ronda", 55, 0.6, 200, 300))
      @casillas<< Calle.new(14, 600,  
        TituloPropiedad.new("Calle Elvira", 55, 0.6, 200, 300))
      @casillas<< Casilla.new(15, 0, TipoCasilla::CARCEL)
      @casillas<< Calle.new(16, 600,  
        TituloPropiedad.new("Calle Fuente Nueva", 55, 0.6, 200, 300))
      @casillas<< Casilla.new(17, 0, TipoCasilla::SORPRESA)
      @casillas<< Calle.new(18, 600,  
        TituloPropiedad.new("Calle Carrera del Darro", 55, 0.6, 200, 300))
      @casillas<< Calle.new(19, 600,  
        TituloPropiedad.new("Calle Recogidas", 55, 0.6, 200, 300))
      
      @carcel = @casillas[15]
    end
    public
    def es_casilla_carcel(casilla)
      casilla.eq(@carcel.numero_casilla)
    end
    def obtener_casilla_numero(casilla)
      @casillas.at(casilla)
    end
    def obtener_nueva_casilla(casilla, desplazamiento)
      index = (casilla.numero_casilla + desplazamiento) % 20
      @casillas.at(index)
    end
  end
end
