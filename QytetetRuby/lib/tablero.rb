#encoding: UTF-8

module ModeloQytetet
  require_relative "casilla"
  require_relative "tipo_casilla"
  require_relative "titulo_propiedad"
  class Tablero
    attr_reader :carcel
    def initialize
      inicializar
    end
    
    def to_s
      @casillas.each { |c| c.to_s }
    end
    private
    def inicializar
      @casillas = Array.new
      @casillas<< Casilla.crear_casilla(0, 0, TipoCasilla::SALIDA)
      @casillas<< Casilla.crear_calle(1, 450, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle San Jerónimo", 50, 0.5, 150, 250))
      @casillas<< Casilla.crear_calle(2, 500, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Periodistas", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_casilla(3, 200, TipoCasilla::IMPUESTO)
      @casillas<< Casilla.crear_calle(4, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Ángel Ganivet", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_casilla(5, 0, TipoCasilla::JUEZ)
      @casillas<< Casilla.crear_calle(6, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Cuesta Gomerez", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_casilla(7, 0, TipoCasilla::SORPRESA)
      @casillas<< Casilla.crear_calle(8, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Pedro Antonio", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_calle(9, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Reyes Católicos", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_casilla(10, 0, TipoCasilla::PARKING)
      @casillas<< Casilla.crear_calle(11, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Gran Vía", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_casilla(12, 0, TipoCasilla::SORPRESA)
      @casillas<< Casilla.crear_calle(13, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Camino de Ronda", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_calle(14, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Elvira", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_casilla(15, 0, TipoCasilla::CARCEL)
      @casillas<< Casilla.crear_calle(16, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Fuente Nueva", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_casilla(17, 0, TipoCasilla::SORPRESA)
      @casillas<< Casilla.crear_calle(18, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Carrera del Darro", 55, 0.6, 200, 300))
      @casillas<< Casilla.crear_calle(19, 600, TipoCasilla::CALLE, 
        TituloPropiedad.new("Calle Recogidas", 55, 0.6, 200, 300))
      
      @carcel = @casillas[15]
    end
    public
    def es_casilla_carcel(casilla)
      casilla.eq(@carcel.numero_casilla)
    end
    def obtener_casilla_numero(casilla)
      @casilla[casilla]
    end
    def obtener_nueva_casilla(casilla, desplazamiento)
      
    end
    def self.main
      t = Tablero.new
      puts t.carcel.to_s
      puts t.to_s
    end
  end
  Tablero.main
end
