#encoding: UTF-8
module ModeloQytetet
  require_relative "sorpresa"
  require_relative "tipo_sorpresa"
  require_relative "tablero"
  require_relative "jugador"
  class PruebaQytetet
    private
    def self.get_sorpresa(tipo_sorpresa)
      m = Array.new
      for elemento in @@mazo
        if(elemento.tipo == tipo_sorpresa)
          m << elemento
        end
      end
      return m
    end
    def self.ir_a_casilla()
      m = Array.new
      for elemento in @@mazo
        if(elemento.tipo == TipoSorpresa::IRACASILLA)
          m << elemento
        end
      end
      return m
    end
    def self.mayor_que_0()
      s = Array.new
      @@mazo.each { |m|  m.valor?>0  s << m }
      #      m = Array.new
      #      for elemento in @@mazo
      #        if(elemento.valor > 0)
      #          m << elemento
      #        end
      #      end
      return s
    end
    public
    def self.main()
      m = Array.new
      #      m = ir_a_casilla
      #      m = get_sorpresa(TipoSorpresa::SALIRCARCEL)
      m = mayor_que_0
      puts m.size
      #      for elemento in m                   \#Forma 1 de imprimir por pantalla
      #        puts elemento
      #      end
      puts Tablero.new.to_s
      qytetet = Qytetet.instance
      
      puts"\n", m.shift.to_s until m.empty? #Forma 2 de imprimir por pantalla
      puts Jugador.new("Fran").encarcelado
    end
  end
# PruebaQytetet.main
end