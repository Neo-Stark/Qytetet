#encoding: UTF-8

module ModeloQytetet
  require_relative"casilla"
  class TituloPropiedad
    attr_reader :alquiler_base, :factor_revalorizacion, :hipoteca_base,
      :hipotecada, :nombre, :precio_edificar, :casilla
    attr_writer :casilla, :hipotecada, :propietario
    def initialize (nombre, alquiler, factor, hipoteca, edificar )
      @alquiler_base = alquiler
      @factor_revalorizacion = factor
      @hipoteca_base = hipoteca
      @hipotecada = false
      @nombre = nombre
      @precio_edificar = edificar
      @propietario = nil
    end
    def to_s
      "Titulo propiedad {nombre= #{@nombre}, alquiler base= #{@alquiler_base}, "\
      +"factor revalorización= #{@factor_revalorizacion}, hipoteca base= "\
      +"#{@hipoteca_base},\n\t\t hipotecada= #{@hipotecada}, precio edificar= "\
      +"#{@precio_edificar}, casilla= #{@casilla.numero_casilla}, propietario= #{@propietario}}\n\n"
    end
    def cobrar_alquiler(coste)
      
    end
    def propietario_encarcelado
      @propietario.encarcelado
    end
    def tengo_propietario
      @propietario != nil
    end
    def set_casilla(casilla)
      @casilla = casilla
    end
  end
end
