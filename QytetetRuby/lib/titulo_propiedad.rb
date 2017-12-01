#encoding: UTF-8

module ModeloQytetet
  require_relative 'casilla'
  class TituloPropiedad
    attr_reader :alquiler_base, :factor_rev, :hipoteca_base,
      :hipotecada, :nombre, :precio_edificar, :casilla
    attr_writer :casilla, :hipotecada, :propietario
    def initialize (nombre, alquiler, factor, hipoteca, edificar )
      @alquiler_base = alquiler
      @factor_rev = factor
      @hipoteca_base = hipoteca
      @hipotecada = false
      @nombre = nombre
      @precio_edificar = edificar
      @propietario = nil
      @casilla = nil
    end
    def to_s
      "Titulo Propiedad: "\
        "\n\tNombre: #{@nombre}" \
        "\n\tHipotecado: #{@hipotecada}" \
        "\n\tAlquiler Base: #{@alquiler_base}" \
        "\n\tFactor Revalorización: #{@factor_rev}"\
        "\n\tHipoteca Base: #{@hipoteca_base}"\
        "\n\tPrecio Edificar: #{@precio_edificar}"\
        "\n\tpropietario: #{@propietario}"
    end
    def cobrar_alquiler(coste)
      @propietario.modificar_saldo(coste)
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
